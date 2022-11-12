package myjunit4;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

import myjunit4.Statement;
import myjunit4.Counter;

public class AfterStatement extends Statement {

	private Statement next;
	private List<Method> afters = new ArrayList<Method>();
	private Object target;
	private Counter counter;

	public AfterStatement(Statement next, List<Method> afters, Object target, Counter counter) {
		this.next = next;
		this.afters = afters;
		this.target = target;
		this.counter = counter;
	}

	@Override
	public void evaluate() throws Throwable {
		List<Throwable> errors = new ArrayList<Throwable>();
		try {
			next.evaluate();
		} catch (Throwable e) {
			// counter.addFailure();
		} finally {
			for ( Method after : afters ) {
				after.invoke(target);
			}
		}
	}
}