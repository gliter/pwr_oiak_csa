package csa_opti;

import java.util.ArrayList;

public class Main {
	Main() {
		
	}
	public static void main(String[] args) {
		//Tablica optymalizatorów
		ArrayList<AOptimizer> optiArr = new ArrayList<AOptimizer>();
		optiArr.add(new OptiMax());		//Maksymalna
		optiArr.add(new OptiWallace()); //Wallace'a
		optiArr.add(new Opti1(5));		//Sklansky n: 5
		optiArr.add(new Opti1(4));		//Sklansky n: 4
		optiArr.add(new Opti1(3));		//Sklansky n: 3
		optiArr.add(new Opti1(2));		//Sklansky n: 2
		
		//Tablica z testami
		ArrayList< TestCase > testCases = new ArrayList< TestCase >();
		
		
		//Testy z uzyciem rownomiernie rozlozonych bitow
		for(int j = 10; j <= 60; j+=10)
			for(int i = 10; i <= 500; i+=10)
				testCases.add(TestCasesFactory.createEvenTestCase(j, i));
		
		//Testy z uzyciem symulowanych sum czesciowych bedacych wynikiem mnozenia
		/*for(int j = 120; j <= 170; j+=10)
			for(int i = 10; i <= 59; i+=1)
				testCases.add(TestCasesFactory.createMulTestCase(j, i));*/
		
		//Testy z uzyciem losowej ilosci bitow
		/*for(int j = 10; j <= 60; j+=10)
			for(int i = 10; i <= 500; i+=10)
				testCases.add(TestCasesFactory.createRandTestCase(j, i));*/
		
		//Test u¿yty do przyk³adu
		//testCases.add(TestCasesFactory.createRandTestCase(20, 6, 16));
		
		
		for(AOptimizer op : optiArr) {	
			System.out.println(op.getName());
			System.out.println("Width\tHeight\tSum A\tSum T");
			 for(TestCase testCase : testCases) {
				  //Inicjalizacja testu
				op.init(new ArrayList<Integer>(testCase.csaLevel), testCase.width);
				
				  //Drukuje nazwê testu i rozmiary przed testem
				//System.out.println("TC: name: " + testCase.name + ", width: " + testCase.width + ", height: " + testCase.height);
				
				  //Wykonaj test, rozpisujac kolejne poziomy na konsoli
				//op.runAndPrint();
				
				  //Wykonaj test, bez komunikatow
				op.run();
				
				  //Drukuje szerokosc i wysokosc testu			
				System.out.print(testCase.width + "\t" + testCase.height + "\t");
				
				  //Drukuje sume powierzchni i sume sciezek krytycznych
				System.out.println(op.getSumA() + "\t" + op.getSumT());
			}
		}
		
	}
}
