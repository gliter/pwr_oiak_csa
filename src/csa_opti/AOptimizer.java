package csa_opti;

import java.util.ArrayList;
import java.util.HashMap;

abstract class AOptimizer {
	ArrayList<Integer> csaLevelIn;
	ArrayList<Integer> csaLevelOut = new ArrayList<Integer>();
	ArrayList<Adder> adders = new ArrayList<Adder>();
	int A = 0;
	int T = 0;
	int n;
	/**
	 * Konstruktor optymalizera
	 * @param csaLevelIn referencja do poziomu drzewa CSA
	 */
	AOptimizer(ArrayList<Integer> csaLevelIn, int modulo) {
		this.csaLevelIn = csaLevelIn;
		this.n = modulo;
		for(int i = csaLevelIn.size(); i < n; i++)
			csaLevelIn.add(0);
		for(int i = 0; i <= csaLevelIn.size(); i++)
			csaLevelOut.add(0);
	}
	/**
	 * Wstawia i uruchamia sumatory 
	 */
	abstract void pushAndRun();
	/**
	 * @return referencja do ArrayListy sumator�w
	 */
	ArrayList<Adder> getAdders() {
		return adders;
	}
	/**
	 * @return referencja do ArrayListy poziomu wyj�ciowego drzewa CSA
	 */
	ArrayList<Integer> getOutTree() {
		return csaLevelOut;
	}
	/**
	 * Zapisuje poziom wyj�ciowy drzewa CSA na miejscu poziomu wej�ciowego, czy�ci poziom wyj�ciowy,
	 * czy�ci ArrayList� sumator�w
	 * @return maksymaln� ilo�� bit�w na jednej wadze, pozwala okre�li� warunek stopu optymalizacji
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
	 * @return suma powierzchni sumator�w
	 */
	int getA() {
		A = 0;
		for(Adder ad : adders)
			this.A += ad.getA();
		return A;
	}
	/**
	 * @return maksymalna �cie�ka krytyczna
	 */
	int getT() {
		T = 0;
		for(Adder ad : adders)
			if(ad.getT() > this.T)
				this.T= ad.getT();
		return T;
	}
	/**
	 * Obs�uga modulo 2^n - 1
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
}