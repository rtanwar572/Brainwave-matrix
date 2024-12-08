package brainwave;

import java.util.ArrayList;
import java.util.Scanner;

import leetcode_problem.prime;

public class Atm {
    ArrayList<BankAccount> users;
    public static Scanner input;
    static BankAccount userAcc;

    Atm(ArrayList<BankAccount> userList) {
        this.users = userList;
        input = new Scanner(System.in);
    }

    private static void showMenu() {
        System.out.println("*********** WELCOME **********");
        System.out.println("Enter 1 for Balance Enquiry *****");
        System.out.println("Enter 2 for Changing Pin *****");
        System.out.println("Enter 3 for Depoite Amount *****");
        System.out.println("Enter 4 for Withral *****");
        System.out.println("Enter 5 for Exit *****");

    }

    private static boolean getVerified(ArrayList<BankAccount> userList, String account) {
        for (BankAccount user : userList) {
            String UserAccount = user.getAccount();
            if (account.equals(UserAccount)) {
                userAcc = user;
                return true;
            }
        }
        return false;
    }

    private static ArrayList<BankAccount> getListOfUsers() {
        ArrayList<BankAccount> UserList = new ArrayList<>();
        BankAccount user1 = new BankAccount("Rohit Tanwar", 7740, "4421 5632 8821 4487", 1000);
        BankAccount user2 = new BankAccount("Vikash Tanwar", 8840, "4421 5032 8821 4487", 500);
        BankAccount user3 = new BankAccount("Ajay Saini", 5640, "4420 5032 8821 4487", 1000);
        BankAccount user4 = new BankAccount("Akash Tiwari", 8840, "4421 5032 8855 4487", 1000);
        UserList.add(user1);
        UserList.add(user2);
        UserList.add(user3);
        UserList.add(user4);
        return UserList;
    }

    private static void getBalance() {
        double balance = userAcc.getBalance();
        System.out.println();
        System.out.println("**** Available Balance Is : " + balance + " ****");
    }

    public static boolean validatePin(int pin) {
        if (pin != userAcc.getUserPin()) {
            return false;
        }
        return true;
    }

    private static void changePin(int count) {
        
        System.out.println("**************************");
        System.out.println();
        if(count>3) return;
        System.out.println("Entere Your Current PIN : ");
        int current = input.nextInt();
        if (current!=userAcc.getUserPin()) {
            System.out.println("Invalid Credentials !!");
            System.out.println();
            System.out.println("****** Please Enter Correct Pin or Press 1 for Try Again  *****");
            int key=input.nextInt();
            count++;
            if(key==1) changePin(count+1);
            System.out.println();
            return;
        }
        System.out.println("Enter Yout New PIN : ");
        int newPin = input.nextInt();
        userAcc.updatePin(newPin);
        System.out.println("***** PIN Updated Successfully *****");
        System.out.println();
        System.out.println("**************************");
    }

    private static void depositAmount() {
        System.out.println("**************************");
        System.out.println("Enter Deposite Amount : ");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("**** Sorry !*****");
            return;
        }
        double UpdatedBalance = userAcc.getBalance() + amount;
        userAcc.setBalance(UpdatedBalance);
        System.out.println("Available balance : " +userAcc.getBalance());
    }

    private static void moneyWithrawal() {
        System.out.println("******************************");
        System.out.println();
        int pin;
        int count=0;
        do {
            System.out.println("Enter PIN : ");
            pin = input.nextInt();
            count++;
        } while (!validatePin(pin) && count<3);
        if(count>=3) {
            System.out.println("******************************");
            System.out.println();
            System.out.println("**** You Have Entered Wrong Pin Too Many Times ***** ");
            System.out.println(" !! Your Card May be Block !!");
            System.out.println();
            System.out.println(" ***** Sorry For Inconvenience, Please Try Again After Some Time *****");
            System.out.println();
            // System.out.println("******************************");
            greetUser();
            return;
        }
        if (validatePin(pin)) {
            System.out.println("Enter Withrawal Amount : ");
            double withralMoney = input.nextDouble();
            double currentBal = userAcc.getBalance();
            if (withralMoney > currentBal || withralMoney < 0) {
                System.out.println("****** Insufficient Balance ******");
                return;
            }
            double availableBalance = currentBal - withralMoney;
            System.out.println("**** Please Wait Transaction Is Being Processed ****");
            try {
                Thread.sleep(5000); // Pause for 2 seconds (2000 milliseconds)
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + e.getMessage());
            }
            System.out.println();
            System.out.println("Transaction Completed Successfully !");
            System.out.println();
            System.out.println("!! Please Collect Your Cash !!");

            System.out.println("**********************************");
            System.out.println();
            userAcc.setBalance(availableBalance);
            System.out.println("***** Available Balance : " + userAcc.getBalance() + " *****");
            System.out.println();
            System.out.println("***********************************");

        }
        else{
            System.out.println("**********************************");
            System.out.println();
            System.out.println("Entered Wrong Pin Number ! Sorry for Inconvenience");
            System.out.println();
            System.out.println("**********************************");
            System.out.println();
            System.out.println("!!! Please Try Again later !!!");
        }


    }

    private static void greetUser() {
        System.out.println("********************");
        System.out.println(" Thanyou For Visiting " + userAcc.getUserName());
        System.out.println();

    }

    private void start() {
        int choice;
        do {
            showMenu();
            System.out.println("*****************************");
            System.out.println();
            System.out.println("Please Select Your Choice : ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    getBalance();
                    break;
                case 2:
                    changePin(0);
                    break;
                case 3:
                    depositAmount();
                    break;
                case 4:
                    moneyWithrawal();
                    break;
                case 5:
                    greetUser();
                    System.exit(0);
                    break;
                default:
                    System.out.println(" **** Invalid Option Is Selected ! ****");
                    break;
            }
        } while (choice != 5);
    }

    public static void main(String[] args) {
        ArrayList<BankAccount> UserList = getListOfUsers();
        System.out.println("Please Insert Your Card ......!");
        try {
            Thread.sleep(3000); // Pause for 2 seconds (2000 milliseconds)
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        System.out.println("***** Be On Hold While We Are Processing  ****** ");
        try {
            Thread.sleep(5000); // Pause for 2 seconds (2000 milliseconds)
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        // object creation means we have inserted the card oon atm machine..
        Atm atm = new Atm(UserList);
        // lets verify the card is valid or not . verifying through account
        if (getVerified(UserList, "4421 5632 8821 4487")) {
            atm.start();
        } else {
            System.out.println("***** Soryy For The Inconvenience *****");
        }
        System.out.println();
        System.out.println("*********************************");

    }
}

class BankAccount {
    private String userName;
    private int userPin;
    private String userAccount;
    private double balance;

    // make getter and setter for that;
    BankAccount(String name, int pin, String account, double balance) {
        this.userName = name;
        this.userPin = pin;
        this.userAccount = account;
        this.balance = balance;
    }

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    String getAccount() {
        return userAccount;
    }

    double getBalance() {
        return this.balance;
    }

    int getUserPin() {
        return userPin;
    }

    String getUserName() {
        return this.userName;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public void updatePin(int pin) {
        this.userPin = pin;
    }

}
