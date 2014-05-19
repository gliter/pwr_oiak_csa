package csa_opti;

import java.util.ArrayList;
/**
 * Optymalizacja "zbli¿ona" do optymalizcji Wallace'a, zbli¿enie jest spowodowane
 * u¿ywaniem arytmetyki modulo, gdzie trudno do koñca przewidzieæ iloœæ bitów na
 * wadze po przeliczeniu poziomu.
 * U¿ywa wy³¹cznie sumatorów FA i HA
 */
public class OptiWallace extends AOptimizer{
	
	ArrayList<Integer> wallaceSeries = new ArrayList<Integer>();
	int maxHeight = 0;
	int maxIndex = -1;
	
	OptiWallace() {
		this.name = "Wallace";
	}
	OptiWallace(ArrayList<Integer> csaLevelIn, int modulo) {
		super(csaLevelIn, modulo);
		this.name = "Wallace";
	}
	@Override
	void init(ArrayList<Integer> csaLevelIn, int modulo) {
		super.init(csaLevelIn, modulo);
		for(int i = 0; i < csaLevelIn.size(); i++) {
			if(i > maxHeight) {
				maxHeight = csaLevelIn.get(i);
				maxIndex = i;
			}
		}
		wallaceSeries.add(2);
		while(wallaceSeries.get(wallaceSeries.size() - 1) <= maxHeight) {
			int lastNum = wallaceSeries.get(wallaceSeries.size() - 1);
			int n = (lastNum / 2)*3 + lastNum % 2;
			wallaceSeries.add(n);
		}
	}
	@Override
	int pushAndRun() {
		int added = 0;
		int targetNum = 0;
		int maxHeight = 0;
		for(int i = 0; i < csaLevelIn.size(); i++) {
			if(csaLevelIn.get(i) > maxHeight) {
				maxHeight = csaLevelIn.get(i);
				maxIndex = i;
			}
		}
		//System.out.println("maxHeight " + maxHeight);
		for(int i = 0; i < wallaceSeries.size(); i++)
		{
			if(wallaceSeries.get(i) >= maxHeight) {
				targetNum = wallaceSeries.get(i - 1);
				break;
			}
		}
		for(int i = 0; i < csaLevelIn.size(); i++) {
			int index = (maxIndex + i) % csaLevelIn.size();
			if(index < 0)
				index += csaLevelIn.size();
			int bitSum = csaLevelIn.get(index) + csaLevelOut.get(index);
			
			int prevBitIndex = (index - 1) % csaLevelIn.size();
			if(prevBitIndex < 0)
				prevBitIndex += csaLevelIn.size();
			int prevBitSum = csaLevelIn.get(prevBitIndex) + csaLevelOut.get(prevBitIndex);
			int prevDiff = prevBitSum - targetNum;
			if(prevDiff < 0)
				prevDiff = 0;
			
			int diff = bitSum - targetNum + prevDiff / 2 + prevDiff % 2;
			
			if(diff > 0) {
				int faNum = diff / 2 + diff % 2;
				for(int j = 0; j < faNum; j++) {
					Adder ad = new FullAdder(csaLevelIn, csaLevelOut, index);
					if(ad.isPossible()) {
						ad.run();
						adders.add(ad);
						added++;
					}
					else
						ad = null;
				}
			}
			
		}
		for(int i = 0; i < csaLevelIn.size(); i++) {
			csaLevelOut.set(i, csaLevelOut.get(i)+csaLevelIn.get(i));
			csaLevelIn.set(i, 0);
		}
		return added;
		
	}
	

}
