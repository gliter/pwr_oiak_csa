package csa_opti;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	Main() {
		
	}
	public static void main(String[] args) {
		//Tablica optymalizatorów
		ArrayList<AOptimizer> optiArr = new ArrayList<AOptimizer>();
		optiArr.add(new OptiMax());
		//optiArr.add(new OptiWallace());
		optiArr.add(new Opti1(2));
		
		ArrayList< TestCase > testCases = new ArrayList< TestCase >();
		testCases.add(TestCasesFactory.createEvenTestCase(40, 20));
		testCases.add(TestCasesFactory.createMulTestCase(40, 20));
		testCases.add(TestCasesFactory.createRandTestCase(40, 20));
		testCases.add(TestCasesFactory.createEvenTestCase(60, 20));
		testCases.add(TestCasesFactory.createMulTestCase(60, 20));
		testCases.add(TestCasesFactory.createRandTestCase(60, 20));
		testCases.add(TestCasesFactory.createEvenTestCase(20, 10));
		testCases.add(TestCasesFactory.createMulTestCase(20, 10));
		testCases.add(TestCasesFactory.createRandTestCase(20, 10));
		
		for(TestCase testCase : testCases) {	
			 for(AOptimizer op : optiArr) {
				System.out.println("Opt: " + op.getName());
				op.init(new ArrayList<Integer>(testCase.csaLevel), testCase.width);
				System.out.println("TC: name: " + testCase.name + ", width: " + testCase.width + ", height: " + testCase.height);
				//op.runAndPrint();
				op.run();
				System.out.println("A: " + op.getSumA() + ", T: " + op.getSumT());
			}
		}
		
		Adder ad = new Sklansky(null, null, 1, 4);
		System.out.println(ad.getName() + " " + ad.getA() + " " + ad.getT());
	}
}
