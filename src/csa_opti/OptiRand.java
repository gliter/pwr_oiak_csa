package csa_opti;

import java.util.ArrayList;
import java.util.Random;

class OptiRand extends AOptimizer{
	double chance;
	Random rand = new Random();
	OptiRand(double chance) {
		this.name = "Losowa";
		this.chance = chance;
	}
	OptiRand(ArrayList<Integer> csaLevelIn, int modulo, double chance) {
		super(csaLevelIn, modulo);
		this.chance = chance;
		this.name = "Losowa";
	}

	@Override
	void pushAndRun() {
		boolean end;
		do {
			end = true;
			for(int i = 0; i < csaLevelIn.size(); i++) {
				Adder ad = new FullAdder(csaLevelIn, csaLevelOut, i);
				if((ad.isPossible())&&(rand.nextDouble() <= chance)) {
					ad.run();
					adders.add(ad);
					end = false;
				}
				else
					ad = null;
			}
		}while(end!=true);
		for(int i = 0; i < csaLevelIn.size(); i++) {
			if(csaLevelIn.get(i) >= 2) {
				Adder ad = new HalfAdder(csaLevelIn, csaLevelOut, i);
				if((ad.isPossible())&&(rand.nextDouble() <= chance)) {
					ad.run();
					adders.add(ad);
				}
				else
					ad = null;
			}
		}
		for(int i = 0; i < csaLevelIn.size(); i++) {
			csaLevelOut.set(i, csaLevelOut.get(i)+csaLevelIn.get(i));
			csaLevelIn.set(i, 0);
		}
		
		
	}

}