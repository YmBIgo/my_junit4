package myjunit4;

public class Counter {
	private int failure;
	private int error;
	private int count;
	public Counter() {
		this.failure = 0;
		this.error = 0;
		this.count = 0;
	}
	public void addFailure() {
		this.failure += 1;
	}
	public void addError() {
		this.error += 1;
	}
	public void addCount() {
		this.count += 1;
	}
	public int getFailure() {
		return failure;
	}
	public int getError() {
		return error;
	}
	public int getCount() {
		return count;
	}
}