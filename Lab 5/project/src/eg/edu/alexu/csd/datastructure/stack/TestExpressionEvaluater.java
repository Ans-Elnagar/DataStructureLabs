package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestExpressionEvaluater {
	@Test
	void testInfixToPostfix() {
		ExpressionEvaluator evaluater = new ExpressionEvaluator();
		assertEquals("1 5 +", evaluater.infixToPostfix("  1 + 5   "));
		assertEquals("2 3 4 * +", evaluater.infixToPostfix("2 + 3 * 4"));
		assertEquals("1 2 + 7 *", evaluater.infixToPostfix("(1 + 2) * 7"));
		assertEquals("5 0 3 - *", evaluater.infixToPostfix("5*-3"));
		assertEquals("a b * 5 +", evaluater.infixToPostfix("a * b + 5 "));
		assertEquals("a b * c /", evaluater.infixToPostfix("a * b / c"));
		assertEquals("a b c - d + / e a - * c *", evaluater.infixToPostfix("(a / (b - c + d)) * (e - a) * c"));
		assertEquals("a b / c - d e * + a c * -", evaluater.infixToPostfix("a / b - c + d * e - a * c"));
		assertEquals("a 2 + b 5 * * c 6 / *", evaluater.infixToPostfix("(a+2)*(b*5)*(c/6)"));
	}
	@Test
	void testEvaluate() {
		ExpressionEvaluator evaluater = new ExpressionEvaluator();
		assertEquals(8, evaluater.evaluate("6 2 / 3 - 4 2 * +"));
		assertEquals(6, evaluater.evaluate("1 5 +"));
		assertEquals(14, evaluater.evaluate("2 3 4 * +"));
		assertEquals(21, evaluater.evaluate("1 2 + 7 *"));
		assertEquals(-15, evaluater.evaluate("5 0 3 - *"));
	}

}
