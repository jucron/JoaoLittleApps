package com.joao_renault.little_apps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MinutesCalculator implements IEssentials{
    private Scanner sc = new Scanner(System.in);
    private long minutes =0;
    private String output;
    private boolean checkFormat = true;

    public void minutesCalculator (long minutes) {
        if (minutes < 0) {
            System.out.println("Must be a positive number, please try again");
            this.checkFormat = false;
        } else {
            int years = (int) (minutes/(365*24*60));
            long remainderMinutes = (minutes - ((long) years *365*24*60));
            int days = (int) (remainderMinutes/(24*60));
            remainderMinutes -= ((long) days *24*60);

            this.output = (minutes+" minutes is equal to: "+years+" years, "+days+" days and "+remainderMinutes+" minutes.");
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
        System.out.println("How many minutes would you like to calculate?");
        try {
            minutes = sc.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Input format error, please try again");
            checkFormat = false;
        }
        minutesCalculator(minutes);
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
        System.out.println("-------------------------Minutes Calculator---------------------------------");
        return ("What about a little calculator App?\n" +
                "This one allows you to calculate minutes into years and days.\n" +
                "Hint: Do not calculate the time studying programming.. trust me! T_T");
    }
}
