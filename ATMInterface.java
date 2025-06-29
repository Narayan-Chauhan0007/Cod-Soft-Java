import java.util.Scanner;

// Represents the user's bank account
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Deposit amount must be positive.");
        } else {
            balance += amount;
            System.out.println("✅ Deposited: ₹" + amount);
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance. Available: ₹" + balance);
        } else {
            balance -= amount;
            System.out.println("✅ Withdrawn: ₹" + amount);
        }
    }

    // Check balance method
    public double getBalance() {
        return balance;
    }
}

// Represents the ATM machine
public class ATMInterface {
    private BankAccount account;
    private Scanner scanner;

    public ATMInterface(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    // Show menu options
    public void start() {
        int choice;
        do {
            System.out.println("\n===== 🏧 ATM MENU =====");
            System.out.println("1. 💰 Deposit");
            System.out.println("2. 🏧 Withdraw");
            System.out.println("3. 📄 Check Balance");
            System.out.println("4. ❌ Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    // Handle deposit
    private void deposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // Handle withdrawal
    private void withdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    // Handle balance check
    private void checkBalance() {
        System.out.printf("💳 Current Balance: ₹%.2f\n", account.getBalance());
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("🏦 Welcome to Simple ATM Interface");
        System.out.print("Enter initial account balance: ₹");
        double initialBalance = sc.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATMInterface atm = new ATMInterface(userAccount);
        atm.start();
        sc.close();
    }
}
