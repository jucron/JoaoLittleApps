package com.joao_renault.little_apps;

import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

import java.util.ArrayList;
import java.util.List;

public class AllFactors implements IEssentials {
    private String output;
    private final InputFromUser input = new InputFromUserImpl();

    public void allFactors(int number) {
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
        System.out.println("Which number would you like to know its factors?\n" +
                "Remember, it must be larger than 0.");
        int number;
        while (true) {
            number = input.tryIntInput();
            if (number == -1) {
//                InputMismatch, automatic answer
            } else if (number > 0) {
                 break;
            } else {
                System.out.println("Please choose a valid number: larger than 0.");
            }
        }
        allFactors(number);
    }
    @Override
    public void output() {
        System.out.println(this.output);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Returning to main menu...");
    }
    @Override
    public String description() {
        System.out.println("------------------------------All Factors-----------------------------------");
        return ("Are you a math wizard? You can test yourself here!\n" +
                "A factor of a number is an integer which divides that number wholly.\n" +
                "Choose a number and, as a magic, you get all its factors back!" +
                "The number must be larger than 0.");
    }
}
