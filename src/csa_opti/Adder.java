package csa_opti;

import java.io.Serializable;
import java.util.ArrayList;

abstract class Adder implements Serializable{
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 1L;
	int A = 0;
	int T = 0;
	int pos = -1;
	ArrayList<Integer> csaLevelIn;
	ArrayList<Integer> csaLevelOut;
	String name = "";
	/**
	 * Konstruktor sumatora z podan� pozycj�
	 * @param csaLevelIn referencja do ArrayListy zawieraj�cej poziom wej�ciowy drzewa CSA
	 * @param csaLevelOut referencja do ArrayListy zawieraj�cej poziom wyj�ciowy drzewa CSA
	 * @param pos pozycja sumatora
	 */
	Adder(ArrayList<Integer> csaLevelIn, ArrayList<Integer> csaLevelOut, int pos){
		this.csaLevelIn = csaLevelIn;
		this.csaLevelOut = csaLevelOut;
		this.pos = pos;
	}
	/**
	 * Uruchom sumator
	 */
	abstract void run();
	/**
	 * @return pozycja sumatora
	 */
	int getPos() {
		return pos;
	}
	/**
	 * Umo�liwia zmian� pozycji
	 * @param pos nowa pozycja
	 */
	void setPos(int pos) {
		this.pos = pos;
	}
	/**
	 * @return �cie�ka krytyczna
	 */
	int getT() {
		return T;
	}
	/**
	 * @return powierzchnia sumatora
	 */
	int getA() {
		return A;
	}

	/**
	 * Sprawdza czy sumator mo�e zosta� ustawiony na danej pozycji
	 * @return zwraca true gdy jest mo�liwe wstawienie sumatora
	 */
	abstract boolean isPossible();
	/**
	 * Zwraca nazwe
	 * @return nazwa
	 */
	String getName() {
		return name;
	}
}
