package com.joao_renault.bank;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class BankTerminal {

    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    private static Bank bank = new Bank("Bank of Australia");

    boolean quit = false;
    int choice = 0;

    optionsMenu();

    while (!quit) {
        System.out.println("Enter action (9 for Menu)");
        choice = scanner.nextInt();
        scanner.nextLine(); // (This consumes the \n character)
        switch (choice) {
            case 0:
                System.out.println("Shutting down...");
                quit=true;
                break;
            case 1:
                createBranch();
                break;
            case 2:
                listOfBranches();
                break;
            case 3:
                addCustomer();
                break;
            case 4:
                addTransaction();
                break;
            case 5:
                listOfCustomers();
                break;
            case 6:
                removeBranch();
                break;
            case 7:
                removeCustomer();
                break;
            case 8:
                removeTransaction();
                break;
            case 9:
                optionsMenu();
                break;
        }
    }
    public static void optionsMenu () {
        System.out.println("Choose an action:");
        System.out.println("0 - Quit application");
        System.out.println("1 - Create new branch");
        System.out.println("2 - List of branches");
        System.out.println("3 - Add a customer");
        System.out.println("4 - Add a transaction");
        System.out.println("5 - List of customers and their balances");
        System.out.println("6 - Remove a branch");
        System.out.println("7 - Remove a customer of a branch");
        System.out.println("8 - Remove a transaction of a customer");
        System.out.println("9 - Menu of actions");
    }
    public static void createBranch () {
        System.out.println("Enter name of branch");
        String branchName = scanner.nextLine();

        if (bank.newBranch(branchName)) {
            System.out.println("New branch "+branchName+" added to the bank");
        } else {
            System.out.println("Cannot execute, branch already exists");
        }
    }

    public static void listOfBranches () {

        System.out.println("List of branches:");
        for (int i=0;i< bank.getBranches().size();i++) {
            System.out.println(i+1+": "+ bank.getBranches().get(i).getBranchName());
        }
    }
    public static void listOfCustomers () {
        System.out.println("Enter name of branch you want the list");
        String branchName = scanner.nextLine();

        bank.listOfCustomers(branchName);

    }
    public static void addCustomer () {
        System.out.println("Enter name of branch you want to access");
        String branchName = scanner.nextLine();

        System.out.println("Enter name of customer you want to create");
        String customerName = scanner.nextLine();

        System.out.println("Enter initial amount (use dot as decimal)");
        double initialAmount = scanner.nextDouble();

        bank.addCustomer(branchName,customerName,initialAmount);
    }
    public static void addTransaction () {
        System.out.println("Enter name of branch you want to access");
        String branchName = scanner.nextLine();

        System.out.println("Enter name of customer you want to access");
        String customerName = scanner.nextLine();

        System.out.println("Enter amount (use dot as decimal)");
        double initialAmount = scanner.nextDouble();

        bank.addTransaction(branchName,customerName,initialAmount);
    }
    public static void removeBranch () {
        System.out.println("Enter name of branch");
        String branchName = scanner.nextLine();

        if (bank.removeBranch(branchName)) {
            System.out.println("Branch "+branchName+" removed.");
        } else {
            System.out.println("Cannot execute, branch is not in the list");
        }
    }
    public static void removeCustomer () {
        System.out.println("Enter name of branch you want to access");
        String branchName = scanner.nextLine();

        System.out.println("Enter name of customer you want to remove");
        String customerName = scanner.nextLine();

        bank.removeCustomer(branchName,customerName);
    }
    public static void removeTransaction () {
        System.out.println("Enter name of branch you want to access");
        String branchName = scanner.nextLine();

        System.out.println("Enter name of customer you want to access");
        String customerName = scanner.nextLine();

        if (bank.listOfTransactions(branchName, customerName)) {
            System.out.println("Choose which transaction number you wish to remove");
            int numberOfTransaction = scanner.nextInt();

            bank.removeTransaction(branchName,customerName,numberOfTransaction);
        }
        // Else, it prints error
    }
    public void startingBankApp() {
            System.out.println("Welcome to Bank Little App!\n" +
                    "Would you like to load the bank database?\n" +
                    "(yes/no - y/n)");
            while (true) {
                String choice = tryStrInput();
                if (choice == "yes" || choice =="y") {
                    loadDB();
                    break;
                } else if choice == "no" || choice =="n") {
                    newBank();
                    break;
                }
                    System.out.println("Please try again.");

            }
        }
    private String tryStrInput(){
        try {
            String strInput = scanner.nextLine();
            return strInput;
        } catch (InputMismatchException e) {
            System.out.println("Input format error, please try again");
        }
        return null;
    }
    public void execute() {
        System.out.println(description());
        startingBankApp();
    }
    public String description() {
        System.out.println("----------------------------Bill's Burger------------------------------------");
        return ("Are you going to the Bank? No need! I'm here to give you assistance.\n" +
                "This is the Little Bank App! Feel free to create your own bank, make transactions and clients.\n" +
                "TODO.\n");
    }
}
