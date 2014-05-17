package csa_opti;

import java.util.ArrayList;

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
	void pushAndRun() {
		int targetNum = 0;
		int maxHeight = 0;
		for(int i = 0; i < csaLevelIn.size(); i++) {
			if(i > maxHeight) {
				maxHeight = csaLevelIn.get(i);
				maxIndex = i;
			}
		}
		for(int i = 0; i < wallaceSeries.size(); i++)
		{
			if(wallaceSeries.get(i) >= maxHeight) {
				targetNum = wallaceSeries.get(i - 1);
				break;
			}
		}
		//System.out.println(maxHeight + " " + targetNum);
		//System.out.println(targetNum);
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
			//System.out.println(bitSum);
			int diff = bitSum - targetNum + prevDiff / 2 + prevDiff % 2;
			//System.out.println(diff);
			if(diff > 0) {
				int faNum = diff / 2 + diff % 2;
				//int haNum = diff % 2;
				for(int j = 0; j < faNum; j++) {
					Adder ad = new FullAdder(csaLevelIn, csaLevelOut, index);
					ad.run();
					adders.add(ad);
				}
				/*if(haNum == 1) {
					Adder ad = new HalfAdder(csaLevelIn, csaLevelOut, index);
					ad.run();
					adders.add(ad);
				}*/
			}
			
		}
		for(int i = 0; i < csaLevelIn.size(); i++) {
			csaLevelOut.set(i, csaLevelOut.get(i)+csaLevelIn.get(i));
			csaLevelIn.set(i, 0);
		}
		
	}
	

}
