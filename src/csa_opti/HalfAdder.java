package csa_opti;

import java.util.ArrayList;

class HalfAdder extends Adder {
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 1L;
	HalfAdder(ArrayList<Integer> csaLevelIn, ArrayList<Integer> csaLevelOut, int pos) {
		super(csaLevelIn, csaLevelOut, pos);
		T = 2;
		A = 3;
		name  = "HA";
	}
	@Override
	void run() {
		csaLevelIn.set(pos, csaLevelIn.get(pos)-2);
		csaLevelOut.set(pos, csaLevelOut.get(pos)+1);
		csaLevelOut.set(pos+1, csaLevelOut.get(pos+1)+1);
		
	}
	@Override
	boolean isPossible() {
		if(csaLevelIn.get(pos)>=2)
			return true;
		return false;
	}

}
