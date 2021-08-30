package com.joao_renault.bank;

import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

public class BankTerminal {

    boolean quit = false;
    private final InputFromUser input = new InputFromUserImpl();


    private static Bank bank;

    public void execute() {
        System.out.println(description());
        startingBankApp();
    }
    private String description() {
        System.out.println("----------------------------Bill's Burger------------------------------------");
        return ("Are you going to the Bank? No need! I'm here to give you assistance with this LittleApp.\n" +
                "This is the Little Bank App! Feel free to create your own bank, with branches and clients. \n" +
                "Also you can add transactions and check balance of customers.");
    }
    private void startingBankApp() {
        System.out.println("Let's get started!");
//        System.out.println("Would you like to load the bank database?\n" + //todo
//                "(yes/no - y/n)");
        while (true) {
//            String answer = input.tryStrInput(); //todo
            String answer = "no";
            answer = answer.toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                loadDB();
                optionsMenu();
                break;
            } else if (answer.equals("no") || answer.equals("n")) {
                newBank();
                optionsMenu();
                break;
            } else {
                System.out.println("Please try again.");
            }
        }
    }
    private void optionsMenu() {
        while (!quit) {
            System.out.println("////////////////////////////////////");
            System.out.println("Choose the next action");
            printOptionsMenu();
            int choice = input.tryIntInput();
//            scanner.nextLine(); // (This consumes the \n character)
            switch (choice) {
                case 0:
                    System.out.println("Closing Bank Application");
                    saveBank();
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("Returning to main menu...");
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
                    saveBank();
                    break;
                default:
                    System.out.println("Please try again with an existing option.");
                    break;
                case 99: //In case of input error (non-integer)
//                    System.out.println("Returning to main menu...");
                    break;
            }
        }
    }
    private void printOptionsMenu() {
        System.out.println("Choose an action:");
        System.out.println("0 - Close Bank application");
        System.out.println("1 - Create new branch");
        System.out.println("2 - List of branches");
        System.out.println("3 - Add a customer");
        System.out.println("4 - Add a transaction");
        System.out.println("5 - List of customers and their balances");
        System.out.println("6 - Remove a branch");
        System.out.println("7 - Remove a customer of a branch");
        System.out.println("8 - Remove a transaction of a customer");
        System.out.println("9 - Save all data into a DataBase");
    }
    private void createBranch () {
        System.out.println("Enter name of branch");
        String branchName = input.tryStrInput();

        if (bank.newBranch(branchName)) {
            System.out.println("New branch "+branchName+" added to the bank");
        } else {
            System.out.println("Cannot execute, branch already exists");
        }
    }
    private void listOfBranches () {

        System.out.println("List of branches:");
        for (int i=0;i< bank.getBranches().size();i++) {
            System.out.println(i+1+": "+ bank.getBranches().get(i).getBranchName());
        }
    }
    private void listOfCustomers () {
        System.out.println("Enter name of branch you want the list");
        String branchName = input.tryStrInput();

        bank.listOfCustomers(branchName);

    }
    private void addCustomer () {
        System.out.println("Enter name of branch you want to access");
        String branchName = input.tryStrInput();

        System.out.println("Enter name of customer you want to create");
        String customerName = input.tryStrInput();

        System.out.println("Enter initial amount (use dot as decimal)");
        double initialAmount = (double) input.tryLongInput();

        bank.addCustomer(branchName,customerName,initialAmount);
    }
    private void addTransaction () {
        System.out.println("Enter name of branch you want to access");
        String branchName = input.tryStrInput();

        System.out.println("Enter name of customer you want to access");
        String customerName = input.tryStrInput();

        System.out.println("Enter amount (use dot as decimal)");
        double initialAmount = (double) input.tryLongInput();

        bank.addTransaction(branchName,customerName,initialAmount);
    }
    private void removeBranch () {
        System.out.println("Enter name of branch");
        String branchName = input.tryStrInput();

        if (bank.removeBranch(branchName)) {
            System.out.println("Branch "+branchName+" removed.");
        } else {
            System.out.println("Cannot execute, branch is not in the list");
        }
    }
    private void removeCustomer () {
        System.out.println("Enter name of branch you want to access");
        String branchName = input.tryStrInput();

        System.out.println("Enter name of customer you want to remove");
        String customerName = input.tryStrInput();

        bank.removeCustomer(branchName,customerName);
    }
    private void removeTransaction () {
        System.out.println("Enter name of branch you want to access");
        String branchName = input.tryStrInput();

        System.out.println("Enter name of customer you want to access");
        String customerName = input.tryStrInput();

        if (bank.listOfTransactions(branchName, customerName)) {
            System.out.println("Choose which transaction number you wish to remove");
            int numberOfTransaction = input.tryIntInput();

            bank.removeTransaction(branchName,customerName,numberOfTransaction);
        }
        // Else, it prints error
    }

    private void saveBank() {
//        TODO
    }
    private void loadDB () {
//        TODO
    }
    private void newBank() {
        System.out.println("Tell what your Bank will be called.");
        String nameOfBank = input.tryStrInput();
        while (true) {
            if (nameOfBank != null) {
                bank = new Bank(nameOfBank);
                System.out.println("The bank "+nameOfBank+" was created.");
                break;
            } else {
                System.out.println("Please try again");
            }
        }
    }
}
