package csa_opti;

import java.util.ArrayList;

class FullAdder extends Adder {
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 1L;
	FullAdder (ArrayList<Integer> csaLevelIn, ArrayList<Integer> csaLevelOut, int pos){
		super(csaLevelIn, csaLevelOut, pos);
		T = 4;
		A = 7;
		name = "FA";
	}
	@Override
	void run() {
		csaLevelIn.set(pos, csaLevelIn.get(pos)-3);
		csaLevelOut.set(pos, csaLevelOut.get(pos)+1);
		csaLevelOut.set(pos+1, csaLevelOut.get(pos+1)+1);
		
	}
	@Override
	boolean isPossible() {
		if(csaLevelIn.get(pos)>=3)
			return true;
		return false;
	}
}
