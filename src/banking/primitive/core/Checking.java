package banking.primitive.core;

public class Checking extends Account {

	private static final long serialVersionUID = 11L;
	private int numWithdraws = 0;
	
	private Checking(String name) {
		super(name);
	}

    public static Checking createChecking(String name) {
        return new Checking(name);
    }

	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	 * Deposit. A deposit of positive values may be made unless the Checking account is closed
	 *
     * @param float is the positive deposit amount
     * @return boolean stating if the transaction was successful
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Withdraw. After 5 withdrawals a fee of $2 is charged per transaction. You may
	 * //SER316 TASK 1 CHECKSTYLE FIX
	 * continue to withdraw an overdrawn account until the balance
	 *  is below -$100. Withdrawing funds does not work on a closed account.
     *
     * @param float is the desired positive withdraw amount
     * @return boolean stating if the transaction was successful
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {
			//SER316 TASK 1 CHECKSTYLE FIX
			if (getState() == State.OPEN || (getState() == 
					State.OVERDRAWN && balance >= -1000.0f)) { 
				balance = balance - amount;
				numWithdraws++;
				//SER316 TASK 1 CHECKSTYLE FIX
				if (numWithdraws > 5) {
					balance = balance - 2.0f;
				}
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}
	//SER316 TASK 1 CHECKSTYLE FIX
	public String getType() { 
		return "Checking"; 
	}
	
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
}
