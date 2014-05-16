package csa_opti;

import java.util.ArrayList;

class OptiMax extends AOptimizer{

	OptiMax(ArrayList<Integer> csaLevelIn, int modulo) {
		super(csaLevelIn, modulo);
	}

	@Override
	void pushAndRun() {
		boolean end;
		do {
			end = true;
			for(int i = 0; i < csaLevelIn.size(); i++) {
				Adder ad = new FullAdder(csaLevelIn, csaLevelOut, i);
				if(ad.isPossible()) {
					ad.run();
					adders.add(ad);
					end = false;
				}
				else
					ad = null;
			}
		}while(end!=true);
		for(int i = 0; i < csaLevelIn.size(); i++) {
			Adder ad = new HalfAdder(csaLevelIn, csaLevelOut, i);
			if(ad.isPossible()) {
				ad.run();
				adders.add(ad);
			}
			else
				ad = null;
		}
		for(int i = 0; i < csaLevelIn.size(); i++) {
			csaLevelOut.set(i, csaLevelOut.get(i)+csaLevelIn.get(i));
			csaLevelIn.set(i, 0);
		}
		
		
	}

}