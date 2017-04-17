package banking.primitive.core;

public class Savings extends Account {
	//SER316 TASK 2 FINDBUGS FIX
	private static final long serialVersionUID = 5685465468L;
	
	private int numWithdraws = 0;

	public Savings(String name) {
		super(name);
	}

	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount - 0.50F;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			numWithdraws++;
			//SER316 TASK 1 CHECKSTYLE FIX
			//SER316 TASK 2 FINDBUGS FIX
			if (numWithdraws > 3) {
				balance = balance - 1.0f;
			}
			//SER316 TASK 1 CHECKSTYLE FIX
			if (balance < 0.0f){
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}
	//SER316 TASK 1 CHECKSTYLE FIX
	public String getType() { 
		return "Checking"; 
	}

	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
}
