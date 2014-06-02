package csa_opti;

import java.util.Random;

/**
 * Klasa-fabryka do tworzenia test case'�w
 *
 */

public class TestCasesFactory {
	/**
	 * Generuje TestCase o r�wnej ilo�ci bit�w na wag�
	 * @param width szeroko�� (ilo�� wag) test case'u
	 * @param height wysoko�� (ilo�� bit�w na wag�) test case'u
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
	 * Generuje TestCase symuluj�cy sumy cz�ciowe mno�enia
	 * @param width szeroko�� (ilo�� wag) test case'u
	 * @param height wysoko�� (maksymalna ilo�� bit�w na wag�) test case'u
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
	 * Generuje TestCase z losow� ilo�ci� bit�w na danej wadze
	 * @param width szeroko�� (ilo�� wag) test case'u
	 * @param height wysoko�� (maksymalna ilo�� bit�w na wag�) test case'u
	 * @return zadany TestCase
	 */
	static TestCase createRandTestCase(int width, int height) {
		TestCase testCase = new TestCase();
		testCase.name = "Random";
		testCase.width = width;
		testCase.height = height;
		
		Random rand = new Random(width*height-height); //Zapewnia powtarzalno�� test case'u dla tych samych parametr�w
		
		for(int i = 0; i < width; i++)
			testCase.csaLevel.add(rand.nextInt(height+1));
		return testCase;
	}
	static TestCase createRandTestCase(int width, int height, int height2) {
		TestCase testCase = new TestCase();
		testCase.name = "Random";
		testCase.width = width;
		testCase.height = height;
		
		Random rand = new Random(width*height-height); //Zapewnia powtarzalno�� test case'u dla tych samych parametr�w
		
		for(int i = 0; i < width; i++)
			testCase.csaLevel.add(rand.nextInt(height2 - height + 1) + height);
		return testCase;
	}
}
