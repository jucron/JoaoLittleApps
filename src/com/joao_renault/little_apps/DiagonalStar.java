package com.joao_renault.little_apps;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

public class DiagonalStar implements IEssentials {
    private Scanner sc = new Scanner(System.in);
    private int number =0;
    private boolean checkFormat = true;

    public void printSquareStar(int number){
        if(number < 5) {
            System.out.println("Must be a number starting from 5");
            checkFormat = false;
        } else{
            for (int row = 0; row <number; row++){
                for (int column=0; column<number; column++){
                    if(row==0 || row==number-1 || column==0 || column==number-1 || column==row || column==number-1-row) System.out.print("*");
                    else System.out.print(" ");
                }
                System.out.println("");
            }
        }
    }
    @Override
    public void execute() {
        System.out.println(description());
        inputFromUser();
        output();
    }
    @Override
    public void inputFromUser() {
        System.out.println("Enter a number, starting from 5:");
        try {
            this.number = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input format error, please try again");
            checkFormat = false;
        }
    }
    @Override
    public void output() {
        if (this.checkFormat) {
            printSquareStar(this.number);
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Returning to main menu...");
    }

    @Override
    public String description() {
        System.out.println("----------------------------Diagonal Star-----------------------------------");
        return ("A cool Little App that draws on your screen.\n" +
                "Maybe we can really reach the stars!\n");
    }
}
