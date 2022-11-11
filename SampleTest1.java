import myjunit4.JUnitCore;
import myjunit4.Test;
import myjunit4.Before;
import myjunit4.After;
import myjunit4.Assertion;

public class SampleTest1 {
	private int hoge;
	public static void main (String[] args) {
		JUnitCore.main(SampleTest1.class.getName());
	}
	@Before
	public void beforeTest() {
		System.out.println("Test Start...");
		hoge = 0;
	}
	@After
	public void afterTest() {
		System.out.println("Test Finished...\n");
	}
	@Test
	public void test1() {
		Assertion.assertEquals("Should be equal.", 0, hoge);
	}
	@Test
	public void test2() {
		Assertion.assertEquals("Should not be equal.", 1, hoge);
	}
}