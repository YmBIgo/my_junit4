package myjunit4;

public class Assertion {
	public static void assertTrue(String message, boolean condition) {
		if (!condition) {
			fail(message);
		}
	}
	public static void assertEquals(String message, String expected, String actual) {
		if (expected == null) {
			if (actual != null) {
				fail(message);
				return;
			};
		}
		if(expected.equals(actual)) {
			return;
		}
		fail(message);
	}
	public static void assertEquals(String message, int expected, int actual) {
		if (expected == actual) {
			return;
		}
		fail(message);
	}
	public static void fail(String message) {
		if (message == null) {
			throw new AssertionError();
		}
		throw new AssertionError(message);
	}
}