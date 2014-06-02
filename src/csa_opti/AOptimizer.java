package csa_opti;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstrakcyjna klasa, u¿ywana jako klasa bazowa dla wszystkich optymalizacji.
 */
abstract class AOptimizer {
	ArrayList<Integer> csaLevelIn;
	ArrayList<Integer> csaLevelOut = new ArrayList<Integer>();
	ArrayList<Adder> adders = new ArrayList<Adder>();
	int A = 0;
	int T = 0;
	int sumA = 0;
	int sumT = 0;
	int n;
	String name;
	/**
	 * Domyœlny konstruktor, nie inicjalizuje nic
	 */
	AOptimizer() {
	}
	/**
	 * Konstruktor optymalizera
	 * @param csaLevelIn referencja do poziomu drzewa CSA
	 */
	AOptimizer(ArrayList<Integer> csaLevelIn, int modulo) {
		init(csaLevelIn, modulo);
	}
	void init(ArrayList<Integer> csaLevelIn, int modulo) {
		this.A = 0;
		this.T= 0;
		this.sumA = 0;
		this.sumT = 0;
		csaLevelOut = new ArrayList<Integer>();
		adders = new ArrayList<Adder>();
		this.csaLevelIn = csaLevelIn;
		this.n = modulo;
		for(int i = csaLevelIn.size(); i < n; i++)
			csaLevelIn.add(0);
		for(int i = 0; i <= csaLevelIn.size(); i++)
			csaLevelOut.add(0);
	}
	/**
	 * Wstawia i uruchamia sumatory zwraca iloœæ dodanych sumatorów
	 */
	abstract int pushAndRun();
	/**
	 * @return referencja do ArrayListy sumatorów
	 */
	ArrayList<Adder> getAdders() {
		return adders;
	}
	/**
	 * @return referencja do ArrayListy poziomu wyjœciowego drzewa CSA
	 */
	ArrayList<Integer> getOutTree() {
		return csaLevelOut;
	}
	/**
	 * Zapisuje poziom wyjœciowy drzewa CSA na miejscu poziomu wejœciowego, czyœci poziom wyjœciowy,
	 * czyœci ArrayListê sumatorów
	 * @return maksymaln¹ iloœæ bitów na jednej wadze, pozwala okreœliæ warunek stopu optymalizacji
	 */
	int saveOutTree() {
		csaLevelIn.clear();
		modulo(n);
		for(Integer i : csaLevelOut)
			csaLevelIn.add(i);
		csaLevelOut.clear();
		for(int i = 0; i <= csaLevelIn.size(); i++)
			csaLevelOut.add(0);
		
		adders.clear();
		int max = 0;
		for(Integer i : csaLevelIn)
			if(i > max)
				max = i;
		return max;
	}
	/**
	 * @return suma powierzchni sumatorów
	 */
	int getA() {
		A = 0;
		for(Adder ad : adders)
			this.A += ad.getA();
		return A;
	}
	/**
	 * @return maksymalna œcie¿ka krytyczna
	 */
	int getT() {
		T = 0;
		for(Adder ad : adders)
			if(ad.getT() > this.T)
				this.T= ad.getT();
		return T;
	}
	/**
	 * @return suma powierzchni sumatorów na wszystkich poziomach
	 */
	int getSumA() {
		return sumA;
	}
	/**
	 * @return suma œcie¿ek krytycznych wszystkich poziomów
	 */
	int getSumT() {
		return sumT;
	}
	/**
	 * @return nazwa optymalizatora
	 */
	String getName() {
		return name;
	}
	/**
	 * Obs³uga modulo 2^n - 1
	 * @param n
	 */
	void modulo(int n) {
		csaLevelOut.set(0, csaLevelOut.get(n)+csaLevelOut.get(0));
		for(int i = csaLevelOut.size() - 1; i >= n; i--)
			csaLevelOut.remove(i);
		
	}
	String getAddersNum() {
		if(adders.size() > 0) {
			ArrayList<HashMap<String, Integer>> addersNum = new ArrayList<HashMap<String, Integer>>(n);
			for(int i = 0; i <= n; i++)
				addersNum.add(new HashMap<String, Integer>());
			for(Adder ad : adders) {
				int pos = ad.getPos();
				String name = ad.getName();
				if(addersNum.get(pos).containsKey(name)) {
					addersNum.get(pos).put(name, addersNum.get(pos).get(name)+1);
				}
				else
					addersNum.get(pos).put(name, 1);
			}
			String returnStr = "";
			for(int i = 0; i < addersNum.size(); i++) {
				HashMap<String, Integer> addersInI = addersNum.get(i);
				returnStr += "2^" + i + ": ";
				if(!addersInI.isEmpty())
					for(String key : addersInI.keySet()) {
						returnStr += key + "-" + addersInI.get(key) + " ";
					}
				else
					returnStr += "0 ";
				returnStr += "\t";
			}
			return returnStr;
		}
		else
			return "None";
	}
	void run() {
		int max;
		do {
			int added = this.pushAndRun();
			if(added == 0) {
				System.out.println("B£¥D: Nie uda³o siê dodaæ ¿adnego sumatora");
				return;
			}
			sumA += this.getA();
			sumT += this.getT();
			max = this.saveOutTree();
		}
		while(max > 2);
	}
	void runAndPrint() {
		int max;
		for(Integer i : csaLevelIn)
			System.out.print(i.toString() + "\t");
		System.out.println();
		do {
			int added = this.pushAndRun();
			if(added == 0) {
				System.out.println("B£¥D: Nie uda³o siê dodaæ ¿adnego sumatora");
				return;
			}
			System.out.println(this.getAddersNum());
			System.out.println("A: " + this.getA() + ", T: " + this.getT());
			sumA += this.getA();
			sumT += this.getT();
			max = this.saveOutTree();
		
			for(Integer i : csaLevelIn)
				System.out.print(i.toString() + "\t");
			System.out.println();
		}
		while(max > 2);
		System.out.println("SUM: A: " + sumA + ", SUM: T: " + sumT);
	}
}
