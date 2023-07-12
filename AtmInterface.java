import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User 
{
    private String userId;
    private String pin;

    public User(String userId, String pin) 
    {
        this.userId = userId;
        this.pin = pin;
    }

    public String getUserId() 
    {
        return userId;
    }

    public String getPin() 
    {
        return pin;
    }
}

class Transaction 
{
    private String type;
    private double amount;

    public Transaction(String type, double amount) 
    {
        this.type = type;
        this.amount = amount;
    }

    public String getType() 
    {
        return type;
    }

    public double getAmount() 
    {
        return amount;
    }
}

class Account 
{
    private String userId;
    private double curr_balance;
    private List<Transaction> transactionHistory;

    public Account(String userId) 
    {
        this.userId = userId;
        this.curr_balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() 
    {
        return curr_balance;
    }

    public void deposit(double amount) 
    {
        curr_balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
        System.out.println(amount + "Successfully Deposited!!");
    }

    public void withdraw(double amount) 
    {
        if (curr_balance >= amount) 
        {

            curr_balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println(amount + " Withdrawaled!\n");
        } 
        else 
        {
            System.out.println("Insufficient balance!\n");
        }
    }

    public void transfer(Account destinationAccount, double amount) 
    {
        if (curr_balance >= amount) 
        {
            curr_balance -= amount;
            destinationAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
            System.out.println("Ammount Transfer Completed!!!");
        } 
        else 
        {
            System.out.println("Insufficient balance....\n");
        }
    }

    public void printTransactionHistory() 
    {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) 
        {
            System.out.println(transaction.getType() + ": " + transaction.getAmount());
        }
        System.out.println();
    }
}

class ATM 
{
    private List<User> users;
    private Account current_Account;

    public ATM() 
    {
        users = new ArrayList<>();
        // Add sample users for testing
        users.add(new User("Anjali", "1234"));
        users.add(new User("Anubhuti", "5678"));
    }

    public void start() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**Welcome to the ATM**");

        while (true) 
        {
            System.out.print("User ID: ");
            String userId = scanner.nextLine();
            System.out.print("PIN: ");
            String pin = scanner.nextLine();

            User user = authenticateUser(userId, pin);

            if (user != null) 
            {
                System.out.println("Authentication successful!");
                current_Account = new Account(userId);
                showMenu(scanner);
                break;
            } 
            else 
            {
                System.out.println("Invalid user ID or PIN. Please try again....");
            }
        }
    }

    private User authenticateUser(String userId, String pin) 
    {
        for (User user : users) 
        {
            if (user.getUserId().equals(userId) && user.getPin().equals(pin)) 
            {
                return user;
            }
        }
        return null;
    }

    private void showMenu(Scanner scanner) 
    {
        while (true) 
        {
            System.out.println("What do you want?");
            System.out.println("Please Enter 1 for Transaction history");
            System.out.println("Please Enter 2 for Withdraw");
            System.out.println("Please Enter 3 for Deposit");
            System.out.println("Please Enter 4 for Transfer");
            System.out.println("Please Enter 5 for Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) 
            {
                case 1:
                    current_Account.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    current_Account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    current_Account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter destination account user ID: ");
                    String destinationUserId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Account destinationAccount = findAccount(destinationUserId);
                    if (destinationAccount != null)
                    {
                        current_Account.transfer(destinationAccount, transferAmount);
                    } 
                    else 
                    {
                        System.out.println("Destination account not found");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM!!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again....");
            }
        }
    }

    private Account findAccount(String userId) 
    {
        for (User user : users) 
        {
            if (user.getUserId().equals(userId)) 
            {
                return new Account(userId);
            }
        }
        return null;
    }
}

public class AtmInterface 
{
    public static void main(String[] args) 
    {
        ATM my_atm = new ATM();
        my_atm.start();
    }
}