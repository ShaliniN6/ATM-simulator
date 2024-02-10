import java.util.Scanner;
class insufficientexception extends Exception{
	public insufficientexception(String s){
	      super(s);
	}
}
class ATM {
    private double balance;
	private static final int PIN=3638;

    public ATM(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
		if (amount <0)
			throw new IllegalArgumentException("Invalid amount");
        else{
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } 
    }

    public void withdraw(double amount) throws insufficientexception {
		if (amount <0)
			throw new IllegalArgumentException("Invalid amount");
		else if(balance<amount)
			throw new insufficientexception("Insufficient Balance");
        else{
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } 
    }
	
	public boolean verifyPIN(int PIN){
	    return this.PIN==PIN;
		}
}

public class ATMSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		int choice,PIN;
		
        // Initialize ATM with an initial balance
        ATM atm = new ATM(10000.0);
		
		//PIN Verification
		int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter PIN: ");
            PIN = scanner.nextInt();

            if (atm.verifyPIN(PIN)) {
                break;
            } else {
                attempts--;
                System.out.println("Incorrect PIN.\nRemaining attempts: " + attempts);
            }

            if (attempts == 0) {
                System.out.println("Too many incorrect attempts. Exiting...");
                System.exit(0);
            }
        }
		
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
		
        do{
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
			
			try{
            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + atm.getBalance());
                    break;

                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Exiting ATM Simulator. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }}
		catch(IllegalArgumentException e){
		    System.out.println("Error:"+e.getMessage());
			}
		catch(insufficientexception e){
		    System.out.println("Error:"+e.getMessage());
			}
        }while(choice!=4);
    }
}
