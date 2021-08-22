package com.joao_renault.little_apps;

import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MegaBytesConverter implements IEssentials {
    private final InputFromUser input = new InputFromUserImpl();
    private int kilobytes;
    private int megaBytes;
    private int remainKb;

    public void megaBytesAndKiloBytes(int kiloBytes) {
            megaBytes = (kiloBytes/1024);
            remainKb = (kiloBytes%1024);
    }
    @Override
    public void execute() {
        System.out.println(description());
        inputFromUser();
        output();
    }
    @Override
    public void inputFromUser() {
        System.out.println("Choose the quantity of Kilobytes to convert:");

        int number;
        while (true) {
            number = input.tryIntInput();
            if (number == -1) {
//                InputMismatch, automatic answer
            } else if (number >= 0) {
                break;
            } else {
                System.out.println("Please choose a valid number: positive.");
            }
        }
        this.kilobytes = number;
        megaBytesAndKiloBytes(kilobytes);
    }
    @Override
    public void output() {
        System.out.println("Conversion: "+this.kilobytes + " KB = " + megaBytes + " MB and " + remainKb + " KB");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Returning to main menu...");
    }

    @Override
    public String description() {
        System.out.println("------------------------Mega Bytes Converter---------------------------------");
        return ("Welcome to my little converter App!\n" +
                "This one lets you convert kilobytes into megabytes.\n" +
                "Just remember that a megabyte is equivalent to 1024 kilobytes (tricky!)");
    }
}
