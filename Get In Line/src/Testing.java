/**
 * This class contains a collection of methods that help with testing.
 * 
 * @author Chris Fernandes, Aaron Cass, Kristina Striegnitz
 * 
 */
public class Testing {

	private static boolean VERBOSE = false;

	/**
	 * Toggles between a lot of output and little output.
	 * 
	 * @param verbose
	 *            If verbose is true, then complete information is printed,
	 *            whether the tests passes or fails. If verbose is false, only
	 *            failures are printed.
	 */
	public static void setVerbose(boolean verbose) {
		VERBOSE = verbose;
	}

	/**
	 * Each of the assertEquals methods tests whether the actual result equals
	 * the expected result. If it does, then the test passes, otherwise it
	 * fails. x
	 * 
	 * The only difference between these methods is the types of the parameters.
	 * All take a String message and two values of the same type (either
	 * boolean, int, or String) to compare:
	 * 
	 * @param message
	 *            a message or description of the test
	 * @param expected
	 *            the correct, or expected, value
	 * @param actual
	 *            the actual value
	 */
	public static void assertEquals(String message, boolean expected,
			boolean actual) {
		printTestCaseInfo(message, "" + expected, "" + actual);
		if (expected == actual) {
			pass();
		} else {
			fail(message);
		}
	}

	public static void assertEquals(String message, int expected, int actual) {
		printTestCaseInfo(message, "" + expected, "" + actual);
		if (expected == actual) {
			pass();
		} else {
			fail(message);
		}
	}

	public static void assertEquals(String message, String expected,
			String actual) {
		printTestCaseInfo(message, expected, actual);

		if (expected == null) {
			if (actual == null) {
				pass();
			} else {
				fail(message);
			}
		} else if (expected.equals(actual)) {
			pass();
		} else {
			fail(message);
		}

	}

	private static void printTestCaseInfo(String message, String expected,
			String actual) {
		if (VERBOSE) {
			System.out.println(message + ":");
			System.out.println("expected: " + expected);
			System.out.println("actual:   " + actual);
		}
	}

	private static void pass() {
		if (VERBOSE) {
			System.out.println("PASS");
			System.out.println();
		}
	}

	private static void fail(String description) {
		if (!VERBOSE) {
			System.out.print(description + "  ");
		}
		System.out.println("*******########## FAIL");
		System.out.println();
	}

	/**
	 * Prints a header for a section of tests.
	 * 
	 * @param sectionTitle The header that should be printed.
	 */
	public static void testSection(String sectionTitle) {
		if (VERBOSE) {
			int dashCount = sectionTitle.length();
			System.out.println(sectionTitle);
			for (int i = 0; i < dashCount; i++) {
				System.out.print("-");
			}
			System.out.println();
			System.out.println();
		}
	}
}