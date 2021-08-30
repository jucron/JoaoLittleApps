package com.joao_renault.bank;

import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;
    static final String ABSENT_BRANCH_MESSAGE =  "Operation cancelled, can't find the branch.";

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }

    public boolean newBranch (String branchName) {
        // if branch is not there, create:
        if (findBranch(branchName) == null) {
            Branch newBranch = new Branch(branchName);
            this.branches.add(newBranch);
            return true;
        }
        // if branch already exists = false;
        return false;
    }
    private Branch findBranch (String nameOfTheBranch) {
        for (int i=0; i<this.branches.size();i++) {
            Branch checkedBranch = this.branches.get(i) ;
            if (checkedBranch.getBranchName().equals(nameOfTheBranch)) {
                return checkedBranch;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }
    public void listOfCustomers (String branchName) {
        Branch branch = findBranch(branchName);
        // if branch is not there, cancel operation:
        if (branch == null) {
            System.out.println(ABSENT_BRANCH_MESSAGE);
        } else {
            // if branch already exists = print list;
            System.out.println("List of customers of "+branchName);
            // List of customers:
            for (int i=0;i< branch.getCustomers().size();i++) {
                Customer customer = branch.getCustomers().get(i);
                double balance = 0;
                // Calculating this customer's balance:
                for (int a=0; a<customer.getTransactions().size();a++) {
                    balance += customer.getTransactions().get(a);
                }
                System.out.print(i+1+". Name: "+ customer.getName());
                System.out.println(", Balance: "+ balance);
            }
        }
    }
    public boolean listOfTransactions (String branchName, String customerName) {
        Branch branch = findBranch(branchName);
        // if branch is not there, cancel operation:
        if (branch == null) {
            System.out.println(ABSENT_BRANCH_MESSAGE);
            return false;
        }
            // if branch already exists = execute branch.listOfTransactions
            branch.listOfTransactions(customerName);
            return true;
    }

    public void addCustomer (String branchName, String customerName, double initialAmount){
        Branch branch = findBranch(branchName);
        // if branch is not there, cancel operation:
        if (branch == null) {
            System.out.println(ABSENT_BRANCH_MESSAGE);
        } else {
            // if branch already exists = addCustomer;
            if (branch.newCustomer(customerName,initialAmount)) {
                // nothing to do here, method already does it
            } else {
                System.out.println("Operation cancelled, customer already exists");
            }
        }
    }
    public void addTransaction (String branchName, String customerName, double amount){
        Branch branch = findBranch(branchName);
        // if branch is not there, cancel operation:
        if (branch == null) {
            System.out.println(ABSENT_BRANCH_MESSAGE);
        } else {
            // if branch already exists = addTransaction;
            branch.addCustomerTransaction(customerName, amount);
        }
    }
    public boolean removeBranch (String branchName) {
        // if branch is on the list, remove it:
        if (findBranch(branchName) != null) {
            Branch branch = findBranch(branchName);
            this.branches.remove(branch);
            return true;
        }
        // if branch not on list = false;
        return false;
    }
    public void removeCustomer (String branchName,String customerName) {
        // if branch is on the list, execute removeCustomer:
        if (findBranch(branchName) != null) {
            Branch branch = findBranch(branchName);
            branch.removeCustomer(customerName);

        } else {
            // if branch not on list = print error;
            System.out.println(ABSENT_BRANCH_MESSAGE);
        }
    }
    public void removeTransaction (String branchName,String customerName, int numberOfTransaction) {
        Branch branch = findBranch(branchName);
        branch.removeTransaction(customerName, numberOfTransaction);
    }


}
