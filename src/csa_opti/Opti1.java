package csa_opti;

import java.util.ArrayList;

public class Opti1 extends AOptimizer {
	int n;
	Opti1(int n) {
		this.name = "Opt. 1";
		this.n = n;
	}
	Opti1(ArrayList<Integer> csaLevelIn, int modulo, int n) {
		super(csaLevelIn, modulo);
		this.name = "Opt. 1";
		this.n = n;
	}
	@Override
	int pushAndRun() {
		int addedNum = 0;
		boolean added = false;
		//int i = 2;
		for(int i = n; i >= 2; i--) {
			do
			{
				added = false;
				int pos = 0;
				do {
					Adder ad = new Sklansky(csaLevelIn, csaLevelOut, pos, i);
					if(ad.isPossible()) {
						ad.run();
						adders.add(ad);
						added = true;
						pos += (int)Math.pow(2, i);
						addedNum++;
					}
					else
						pos += 1;
					
				}while(pos + (int)Math.pow(2, i) <= csaLevelIn.size());
			}while(added);
		}
		boolean end;
		do {
			end = true;
			for(int i = 0; i < csaLevelIn.size(); i++) {
				Adder ad = new FullAdder(csaLevelIn, csaLevelOut, i);
				if(ad.isPossible()) {
					ad.run();
					adders.add(ad);
					end = false;
					addedNum++;
				}
				else
					ad = null;
			}
		}while(end!=true);
		for(int j = 0; j < csaLevelIn.size(); j++) {
			csaLevelOut.set(j, csaLevelOut.get(j)+csaLevelIn.get(j));
			csaLevelIn.set(j, 0);
		}
		return addedNum;
	}

}
