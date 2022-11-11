package myjunit4;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import myjunit4.Test;
import myjunit4.Before;
import myjunit4.After;
import myjunit4.TestStatement;
import myjunit4.BeforeStatement;
import myjunit4.AfterStatement;
import myjunit4.Statement;

public class JUnitCore {

	private static Class<?> currentClass;
	private static List<Method> beforeMethods = new ArrayList<Method>();
	private static List<Method> afterMethods = new ArrayList<Method>();
	private static List<Method> testMethods = new ArrayList<Method>();
	private static Counter counter = new Counter();

	public static void main(String... args) {
		getClass(args);
		List<Method> annotatedMethod = getAnnotationMethods();
		Statement statement = getStatement();
		try {
			statement.evaluate();
		} catch (Throwable e) {
			//
		}
		System.out.println("Total Tests " + counter.getCount());
		System.out.println("Total Failures " + counter.getFailure());
		System.out.println("Total Errors " + counter.getError());
	}
	private static void getClass(String[] args) {
		try {
			for (int i = 0; i < args.length; i++ ) {
				Class<?> currentClass1 = Class.forName(args[i]);
				// classes.add(currentClass1);
				currentClass = currentClass1;
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	private static List<Method> getAnnotationMethods() {
		List<Method> resultMethods = new ArrayList<Method>();
		Class<?> eachClass = currentClass;
		// for ( Class<?> eachClass : classes.get(0) ) {
		Method[] methods = eachClass.getDeclaredMethods();
		for ( Method method : methods ) {
			Test testAnnotation = method.getAnnotation(Test.class);
			Before beforeAnnotation = method.getAnnotation(Before.class);
			After afterAnnotation = method.getAnnotation(After.class);
			if ( testAnnotation == null && beforeAnnotation == null && afterAnnotation == null ) continue;
			if ( testAnnotation != null ) testMethods.add(method);
			if ( beforeAnnotation != null ) beforeMethods.add(method);
			if ( afterAnnotation != null ) afterMethods.add(method);
			resultMethods.add(method);
		}
		// }
		return resultMethods;
	}
	private static Statement getStatement() {
		try {
			Object currentConstructor = currentClass.getConstructors()[0].newInstance();
			Statement statement = new TestStatement(testMethods, currentConstructor, counter);
			statement = beforeMethods.isEmpty() ? statement : new BeforeStatement(statement, beforeMethods, currentConstructor);
			statement = afterMethods.isEmpty() ? statement : new AfterStatement(statement, afterMethods, currentConstructor, counter);
			return statement;
		} catch ( InstantiationException e ) {
			System.out.println(e);
		} catch ( IllegalAccessException e) {
			System.out.println(e);
		} catch ( InvocationTargetException e) {
			System.out.println(e);
		}
		return new Statement();
	}
}