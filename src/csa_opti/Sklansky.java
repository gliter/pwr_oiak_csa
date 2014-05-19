package csa_opti;

import java.util.ArrayList;

/**
 * Klasa symuluj¹ca sumowanie przy pomocy drzewa prefiksowego Sklansky / Ladner - Fischer
 */
public class Sklansky extends Adder {
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 1L;
	int n;
	int positions;
	/**
	 * Konstruktor sumatora z podan¹ pozycj¹
	 * @param csaLevelIn referencja do ArrayListy zawieraj¹cej poziom wejœciowy drzewa CSA
	 * @param csaLevelOut referencja do ArrayListy zawieraj¹cej poziom wyjœciowy drzewa CSA
	 * @param pos pozycja sumatora
	 * @param n stopieñ sumatora (pobiera bity na 2^n pozycjach + bit przeniesienia)
	 */
	Sklansky(ArrayList<Integer> csaLevelIn, ArrayList<Integer> csaLevelOut, int pos, int n) {
		super(csaLevelIn, csaLevelOut, pos);
		name = "Sklansky 2x" + (int)Math.pow(2, n) + " + 1";
		positions = (int)Math.pow(2, n);
		this.n = n;
		A = n * (int)Math.pow(2, n-1) * 4 + 4 * positions + 2 * positions;
		T = n * 2 + 1 + 2;
		
		
	}

	@Override
	void run() {
		csaLevelIn.set(pos, csaLevelIn.get(pos) - 3);
		csaLevelOut.set(pos, csaLevelOut.get(pos) + 1);
		for(int i = pos + 1; i < positions; i++) {
			csaLevelIn.set(i, csaLevelIn.get(i) - 2);
			csaLevelOut.set(i, csaLevelOut.get(i) + 1);
		}
		csaLevelOut.set(pos+positions, csaLevelOut.get(pos+positions) + 1);
	}

	@Override
	boolean isPossible() {
		if(csaLevelIn.get(pos) < 3)
			return false;
		for(int i = pos + 1; i < positions - 1; i++) {
			if(i >= csaLevelIn.size())
				return false;
			if(csaLevelIn.get(i) < 2)
				return false;
		}
		//if(pos+positions >= csaLevelIn.size())
		//	return false;
		return true;
	}

}
