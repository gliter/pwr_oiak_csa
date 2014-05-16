package csa_opti;

/**
 * Klasa-fabryka do tworzenia test case'ów
 *
 */

public class TestCasesFactory {
	static TestCase createEvenTestCase(int width, int height) {
		TestCase testCase = new TestCase();
		testCase.name = "Even";
		testCase.width = width;
		testCase.height = height;
		for(int i = 0; i < width; i++)
			testCase.csaLevel.add(height);
		return testCase;
		
	}
}
