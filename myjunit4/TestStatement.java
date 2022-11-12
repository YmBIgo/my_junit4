package myjunit4;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

import myjunit4.Statement;
import myjunit4.Counter;

public class TestStatement extends Statement {
	private List<Method> testMethods = new ArrayList<Method>();
	private Object target;
	private Counter counter;

	public TestStatement(List<Method> testMethods, Object target, Counter counter) {
		this.testMethods = testMethods;
		this.target = target;
		this.counter = counter;
	}

	@Override
	public void evaluate() throws Throwable {
		for ( Method method : testMethods ) {
			counter.addCount();
			try {
				method.invoke(target);
			} catch (Throwable e) {
				counter.addFailure();
			}
		}
	}
}