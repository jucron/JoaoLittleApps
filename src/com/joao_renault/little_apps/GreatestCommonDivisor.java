package com.joao_renault.little_apps;

import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

public class GreatestCommonDivisor implements IEssentials {
    private final InputFromUser input = new InputFromUserImpl();
    private int firstNumber;
    private int secondNumber;

    public static int getGreatestCommonDivisor(int first, int second) {
//        if (first < 1 || second < 1) {
//            return -1;
//        }
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
        firstNumber = getValidNumber("FIRST");
        secondNumber = getValidNumber("SECOND");
    }
    @Override
    public void output() {
            int solution = getGreatestCommonDivisor(firstNumber, secondNumber);
                System.out.println("The greatest common divisor of "+firstNumber+" and " +
                        ""+ secondNumber +" is the number: "+ solution+".");
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
    private int getValidNumber (String position) {
        int number;
        System.out.println("Enter the "+position+" number:\n" +
                "Remember, it must be larger than 0.");
        while (true) {
            number = input.tryIntInput();
            if (number == -1) {
//                InputMismatch, automatic answer
            } else if (number >= 1) {
                break;
            } else {
                System.out.println("Please choose a valid number, starting from 1.");
            }
        }
        return number;
    }
}
