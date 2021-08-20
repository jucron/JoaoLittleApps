package com.joao_renault.bank;

import java.util.ArrayList;

public class Branch {
    private String branchName;
    private ArrayList<Customer> customers;

    public Branch(String branchName) {
        this.branchName = branchName;
        this.customers = new ArrayList<Customer>();
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer (String customerName, double initialAmount) {
        // if customer is not there, create:
        if (findCustomer(customerName) == null) {
            Customer newCustomer = new Customer(customerName, initialAmount);
            this.customers.add(newCustomer);
            System.out.println("Customer "+customerName+" created with initial amount of "+
                    initialAmount);
            return true;
        }
        // if customer already exists = false;
        return false;
    }
    public void addCustomerTransaction (String name, double amount) {
        Customer existingCustomer = findCustomer(name);

        if (existingCustomer != null) { // If customer exists, execute operation and return true
            double balance = amount;
            for (int i=0; i<existingCustomer.getTransactions().size();i++) {
                balance += existingCustomer.getTransactions().get(i);
            }
            if (balance<0) {
                System.out.println("Operation cancelled, don't have enough funds");

            } else {
                existingCustomer.addTransaction(amount);
                System.out.println("Transaction of "+amount+ ", added to customer "+name+", of branch "+this.branchName);
                System.out.println("Total balance of "+ name+" is "+balance);
            }
        } else {
            // if customer is not there, operation not possible: return false
            System.out.println("Operation cancelled, Customer is not on the list");
        }
    }
    // Input name and returns the Customer class (null for not finding it)
    private Customer findCustomer (String name) {
        for (int i=0; i<this.customers.size();i++) {
            Customer checkedCostumer = this.customers.get(i) ;
            if (checkedCostumer.getName().equals(name)) {
                return this.customers.get(i);
            }
        }
        return null;
    }
    public void removeCustomer (String customerName) {
        // if customer is there, remove:
        if (findCustomer(customerName) != null) {
            Customer customer = findCustomer(customerName);
            this.customers.remove(customer);
            System.out.println("Customer "+customerName+" removed.");

        } else {
            // if customer don't exists = false;
            System.out.println("Customer is not on the list");
        }
    }
    public void listOfTransactions (String customerName) {
        if (findCustomer(customerName) != null) {
            System.out.println("List of transactions of "+customerName);
            Customer customerSelected = findCustomer(customerName);
            for (int i=0;i< customerSelected.getTransactions().size();i++) {
                System.out.println(i+1+": "+ customerSelected.getTransactions().get(i));
            }
        } else {
            System.out.println("Customer not found");
        }
    }
    public void removeTransaction (String customerName, int numberOfTransaction) {
        Customer customer = findCustomer(customerName);
        customer.getTransactions().remove(numberOfTransaction-1);
        System.out.println("Transaction number "+ numberOfTransaction+ " removed.");
    }



}
