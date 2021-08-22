package com.joao_renault.little_apps;

import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

public class DiagonalStar implements IEssentials {
    private InputFromUser input = new InputFromUserImpl();

    public void printSquareStar(int number){
            for (int row = 0; row <number; row++){
                for (int column=0; column<number; column++){
                    if(row==0 || row==number-1 || column==0 || column==number-1 || column==row || column==number-1-row) System.out.print("*");
                    else System.out.print(" ");
                }
                System.out.println("");
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
        int number;
        while (true) {
            number = input.tryIntInput();
            if (number == -1) {
//                InputMismatch, automatic answer
            } else if (number >= 5) {
                    break;
            } else {
                System.out.println("Please choose a valid number, starting from 5.");
            }
        }
        printSquareStar(number);
    }
    @Override
    public void output() {
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
