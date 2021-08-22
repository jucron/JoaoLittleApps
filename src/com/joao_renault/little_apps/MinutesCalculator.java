package com.joao_renault.little_apps;

import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MinutesCalculator implements IEssentials{
    private final InputFromUser input = new InputFromUserImpl();
    private String output;

    public void minutesCalculator (long minutes) {
            int years = (int) (minutes/(365*24*60));
            long remainderMinutes = (minutes - ((long) years *365*24*60));
            int days = (int) (remainderMinutes/(24*60));
            remainderMinutes -= ((long) days *24*60);

            this.output = (minutes+" minutes is equal to: "+years+" years, "+days+" days and "+remainderMinutes+" minutes.");

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

        long number;
        while (true) {
            number = input.tryLongInput();
            if (number == -1) {
//                InputMismatch, automatic answer
            } else if (number >= 0) {
                break;
            } else {
                System.out.println("Please choose a valid number: positive.");
            }
        }
        minutesCalculator(number);
    }
    @Override
    public void output() {
            System.out.println(this.output);
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
