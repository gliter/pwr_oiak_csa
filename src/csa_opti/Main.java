package csa_opti;

import java.util.ArrayList;

public class Main {
	Main() {
		
	}
	public static void main(String[] args) {
		ArrayList<AOptimizer> optiArr = new ArrayList<AOptimizer>();
		ArrayList<Integer> csaLevel = new ArrayList<Integer>();
		csaLevel.add(1230);
		csaLevel.add(3232);
		csaLevel.add(4433);
		csaLevel.add(3322);
		csaLevel.add(6011);
		csaLevel.add(7033);
		csaLevel.add(3111);
		
		for(Integer i : csaLevel)
			System.out.print(i.toString() + "\t");
		System.out.println();
		
		AOptimizer op = new OptiMax(csaLevel, 7);
		int a = 0, t = 0;
		int max;
		do {
			op.pushAndRun();
			System.out.println(op.getAddersNum());
			System.out.println("A: " + op.getA() + ", T: " + op.getT());
			a += op.getA();
			t += op.getT();
			max = op.saveOutTree();
		
			for(Integer i : csaLevel)
				System.out.print(i.toString() + "\t");
			System.out.println();
		}
		while(max > 2);
		System.out.println("SUM: A: " + a + ", SUM: T: " + t);
		
	}
}
