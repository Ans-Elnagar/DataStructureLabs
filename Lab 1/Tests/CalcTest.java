package calcPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalcTest {
	Calculator calc = new Calculator();
					///////////////////////////////////
					/////////// add Test //////////////
					//////////////////////////////////
	@Test
	void addNormalTests() {
		// Testing normal addition
		// test x=300,y=350
		assertEquals(650,calc.add(300,350));
		// test x=10000, y=110000
		assertEquals(120000,calc.add(10000,110000));
	}
	@Test
	void addZeroTests() {
		// Testing if one is zero or both
		// test x=0
		assertEquals(25,calc.add(0,25));
		// test y=0
		assertEquals(25,calc.add(25,0));
		// test x=y=0
		assertEquals(0,calc.add(0,0));
	}
	@Test
	void addNegativeTests() {
		// Testing negative values
			// both negative
		// test x=-5, y=-10;
		assertEquals(-15,calc.add(-5,-10));
			// y is negative
		// test x=25, y=-15
		assertEquals(5,calc.add(20,-15));
			// x is negative
		// test x = -15, y=30
		assertEquals(15,calc.add(-15,30));
	}
					///////////////////////////////////
					///////// divide Test /////////////
					//////////////////////////////////
	// Assuming epsilon to be 10^-7
	float epsilon = (float)1e-7;
	float testingVar = 0;
	@Test
	void divideNormalTests() {
		// Testing normal addition
		// test x=300,y=600
		testingVar = calc.divide(300,600);
		assertTrue(Math.abs(testingVar-0.5f)<= epsilon);
		// test x=110000, y=10000
		testingVar = calc.divide(110000,10000);
		assertTrue(Math.abs(testingVar-11f)<= epsilon);
	}
	@Test
	void divideZeroTests() {
		// Testing if one is zero or both
		// test x=0
		testingVar = calc.divide(0,10000);
		assertTrue(Math.abs(testingVar-0f)<= epsilon);
		// test y=0
		assertThrows(ArithmeticException.class,()-> calc.divide(25,0));
		// test x=y=0
		assertThrows(ArithmeticException.class,()-> calc.divide(0,0));
		
	}
	@Test
	void divideNegativeTests() {
		// Testing negative values
			// both negative
		// test x=-5, y=-10;
		testingVar = calc.divide(-5,-10);
		assertTrue(Math.abs(testingVar-0.5f)<= epsilon);
			// y is negative
		// test x=25, y=-10
		testingVar = calc.divide(25,-10);
		assertTrue(Math.abs(testingVar+2.5f)<= epsilon);
			// x is negative
		// test x = -15, y=30
		testingVar = calc.divide(-15,30);
		assertTrue(Math.abs(testingVar+0.5f)<= epsilon);
	}
}
