package com.joao_renault.little_apps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GreatestCommonDivisor implements IEssentials {
    private Scanner sc = new Scanner(System.in);
    private int firstNumber;
    private int secondNumber;
    private boolean checkFormat = true;

    public static int getGreatestCommonDivisor(int first, int second) {
        if (first < 1 || second < 1) {
            return -1;
        }
        int firstDivisor = (first>second ? first : second);
        int secondDivisor = (first<second ? first : second);
        first=firstDivisor;
        second=secondDivisor;
        while (firstDivisor >= 1) {
            secondDivisor=second;
            if ((first % firstDivisor) == 0) {
                while (secondDivisor >= 1) {
                    if ((second % secondDivisor) == 0) {
                        if (firstDivisor == secondDivisor) {
                            return firstDivisor;
                        }
                    }
                    secondDivisor--;
                }
            }
            firstDivisor--;
        }
        return -1;
    }
    @Override
    public void execute() {
        System.out.println(description());
        inputFromUser();
        output();
    }
    @Override
    public void inputFromUser() {
        System.out.println("Enter the FIRST number:");
        try {
            this.firstNumber = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input format error, please try again");
            checkFormat = false;
        }
        if (checkFormat) {
            System.out.println("Enter the SECOND number:");
            try {
                this.secondNumber = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input format error, please try again");
                checkFormat = false;
            }
        }
    }
    @Override
    public void output() {
        if (this.checkFormat) {
            int solution = getGreatestCommonDivisor(firstNumber, secondNumber);
            if (solution == -1) {
                System.out.println("Something went wrong, the number must be higher than 0");
            } else {
                System.out.println("The greatest common divisor of "+firstNumber+" and " +
                        ""+ secondNumber +" is the number: "+ solution+".");
            }
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Returning to main menu...");
    }

    @Override
    public String description() {
        System.out.println("-----------------------Greatest Common Divisor------------------------------");
        return ("This incredible app let you know who's the greatest common divisor of two numbers.\n" +
                "That means it returns the largest positive integer that can fully divide each of the numbers.\n" +
                "I know, it's so much fun I can't hold myself, let's do this already!");
    }

}
