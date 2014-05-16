package csa_opti;

import java.util.ArrayList;

public class OptiWallace extends AOptimizer{
	
	ArrayList<Integer> wallaceSeries = new ArrayList<Integer>();
	
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
		int maxHeight = 0;
		for(Integer i : csaLevelIn) {
			if(i > maxHeight)
				maxHeight = i;
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
		// TODO Auto-generated method stub
		
	}
	

}
