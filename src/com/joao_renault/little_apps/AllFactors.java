package com.joao_renault.little_apps;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AllFactors implements IEssentials {
    private Scanner sc = new Scanner(System.in);
    private int number =1;
    private String output;
    private boolean checkFormat = true;

    public void allFactors(int number) {
        if (number<1) {
            System.out.println("Invalid Value");
            this.checkFormat=false;
        }
        int numberDivider = 1;
        List factors = new ArrayList();
        while (numberDivider<=number) {
            if (number%numberDivider==0) {
                factors.add(numberDivider);
            }
            numberDivider++;
        }
        this.output=("The factors of "+number+" are: "+ factors.toString());
    }

    @Override
    public void execute() {
        System.out.println(description());
        inputFromUser();
        output();
    }
    @Override
    public void inputFromUser() {
        System.out.println("Which number would you like to know its factors?");
        try {
            number = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input format error, please try again");
            checkFormat = false;
        }
        allFactors(number);
    }
    @Override
    public void output() {
        if (this.checkFormat) {
            System.out.println(this.output);
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Returning to main menu...");
    }

    @Override
    public String description() {
        System.out.println("------------------------------All Factors-----------------------------------");
        return ("Are you a math wizard? You can test yourself here!\n" +
                "A factor of a number is an integer which divides that number wholly.\n" +
                "Choose a number and, as a magic, you get all its factors back!");
    }
}
