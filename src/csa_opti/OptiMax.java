package csa_opti;

import java.util.ArrayList;
/**
 * Optymalizacja maksymalna, u¿yte jest ile jest tylko mo¿liwe sumatorów FA i HA
 */
class OptiMax extends AOptimizer{
	OptiMax() {
		this.name = "Maksymalna";
	}
	OptiMax(ArrayList<Integer> csaLevelIn, int modulo) {
		super(csaLevelIn, modulo);
		this.name = "Maksymalna";
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