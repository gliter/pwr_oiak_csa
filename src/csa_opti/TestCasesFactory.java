package csa_opti;

import java.util.Random;

/**
 * Klasa-fabryka do tworzenia test case'ów
 *
 */

public class TestCasesFactory {
	/**
	 * Generuje TestCase o równej iloœci bitów na wagê
	 * @param width szerokoœæ (iloœæ wag) test case'u
	 * @param height wysokoœæ (iloœæ bitów na wagê) test case'u
	 * @return zadany TestCase
	 */
	static TestCase createEvenTestCase(int width, int height) {
		TestCase testCase = new TestCase();
		testCase.name = "Even";
		testCase.width = width;
		testCase.height = height;
		for(int i = 0; i < width; i++)
			testCase.csaLevel.add(height);
		return testCase;	
	}
	/**
	 * Generuje TestCase symuluj¹cy sumy czêœciowe mno¿enia
	 * @param width szerokoœæ (iloœæ wag) test case'u
	 * @param height wysokoœæ (maksymalna iloœæ bitów na wagê) test case'u
	 * @return zadany TestCase
	 */
	static TestCase createMulTestCase(int width, int height) {
		TestCase testCase = new TestCase();
		testCase.name = "Multiplication result";
		testCase.width = width;
		testCase.height = height;
		for(int i = 0; i < width; i++)
			testCase.csaLevel.add(0);
		
		for(int i = 0; i < width / 2 + width % 2; i++) {
			if(i + 1 < height) {
				testCase.csaLevel.set(i, i+1);
				testCase.csaLevel.set(width - 1 - i, i+1);
			}
			else {
				testCase.csaLevel.set(i, height);
				testCase.csaLevel.set(width - 1 - i, height);
			}
		}
		return testCase;
	}
	/**
	 * Generuje TestCase z losow¹ iloœci¹ bitów na danej wadze
	 * @param width szerokoœæ (iloœæ wag) test case'u
	 * @param height wysokoœæ (maksymalna iloœæ bitów na wagê) test case'u
	 * @return zadany TestCase
	 */
	static TestCase createRandTestCase(int width, int height) {
		TestCase testCase = new TestCase();
		testCase.name = "Random";
		testCase.width = width;
		testCase.height = height;
		
		Random rand = new Random(width*height-height); //Zapewnia powtarzalnoœæ test case'u dla tych samych parametrów
		
		for(int i = 0; i < width; i++)
			testCase.csaLevel.add(rand.nextInt(height+1));
		return testCase;
	}
	static TestCase createRandTestCase(int width, int height, int height2) {
		TestCase testCase = new TestCase();
		testCase.name = "Random";
		testCase.width = width;
		testCase.height = height;
		
		Random rand = new Random(width*height-height); //Zapewnia powtarzalnoœæ test case'u dla tych samych parametrów
		
		for(int i = 0; i < width; i++)
			testCase.csaLevel.add(rand.nextInt(height2 - height + 1) + height);
		return testCase;
	}
}
