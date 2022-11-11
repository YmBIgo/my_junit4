package myjunit4;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

import myjunit4.Statement;

public class BeforeStatement extends Statement {

	private Statement next;
	private List<Method> befores = new ArrayList<Method>();
	private Object target;

	public BeforeStatement(Statement next, List<Method> befores, Object target) {
		this.next = next;
		this.befores = befores;
		this.target = target;
	}

	@Override
	public void evaluate() throws Throwable {
		for ( Method before : befores ) {
			before.invoke(target);
		}
		next.evaluate();
	}
}