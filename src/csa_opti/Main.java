package csa_opti;

import java.util.ArrayList;

public class Main {
	Main() {
		
	}
	public static void main(String[] args) {
		//Tablica optymalizatorów
		ArrayList<AOptimizer> optiArr = new ArrayList<AOptimizer>();
		//optiArr.add(new OptiMax());
		//optiArr.add(new OptiWallace());
		//optiArr.add(new Opti1(5));
		optiArr.add(new Opti1(4));
		//optiArr.add(new Opti1(3));
		//optiArr.add(new Opti1(2));
		
		ArrayList< TestCase > testCases = new ArrayList< TestCase >();
		/*for(int i = 10; i <= 60; i++)
			testCases.add(TestCasesFactory.createEvenTestCase(i, 500));*/
		/*for(int j = 10; j <= 60; j+=10)
			for(int i = 10; i <= 500; i+=10)
				testCases.add(TestCasesFactory.createEvenTestCase(j, i));*/
		/*for(int j = 120; j <= 170; j+=10)
			for(int i = 10; i <= 59; i+=1)
				testCases.add(TestCasesFactory.createMulTestCase(j, i));*/
		/*for(int j = 10; j <= 60; j+=10)
			for(int i = 10; i <= 500; i+=10)
				testCases.add(TestCasesFactory.createRandTestCase(j, i));*/
		testCases.add(TestCasesFactory.createRandTestCase(20, 6, 16));
		
		
		for(AOptimizer op : optiArr) {	
			System.out.println(op.getName());
			System.out.println("Width\tHeight\tSum A\tSum T");
			 for(TestCase testCase : testCases) {
				System.out.println("Opt: " + op.getName());
				op.init(new ArrayList<Integer>(testCase.csaLevel), testCase.width);
				System.out.println("TC: name: " + testCase.name + ", width: " + testCase.width + ", height: " + testCase.height);
				op.runAndPrint();
				//op.run();
				System.out.println("A: " + op.getSumA() + ", T: " + op.getSumT());
				
				//System.out.print(testCase.width + "\t" + testCase.height + "\t");
				//System.out.println(op.getSumA() + "\t" + op.getSumT());
			}
		}
		
		//Adder ad = new Sklansky(null, null, 1, 4);
		//System.out.println(ad.getName() + " " + ad.getA() + " " + ad.getT());
	}
}
