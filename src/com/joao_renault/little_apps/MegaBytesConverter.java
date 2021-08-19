package com.joao_renault.little_apps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MegaBytesConverter implements IEssentials {
    private Scanner sc = new Scanner(System.in);
    private int kilobytes;
    private int megaBytes;
    private int remainKb;
    private boolean checkFormat = true;

    public void megaBytesAndKiloBytes(int kiloBytes) {
        if (kiloBytes<0) {
            System.out.println("Must be a positive number, please try again");
            this.checkFormat = false;
        }
        else {
            megaBytes = (kiloBytes/1024);
            remainKb = (kiloBytes%1024);
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
        System.out.println("Choose the quantity of Kilobytes to convert:");
            try {
                kilobytes = sc.nextInt();
//            kilobytes = Integer.parseInt(sc.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Input format error, please try again");
                checkFormat = false;
            }
        megaBytesAndKiloBytes(kilobytes);
    }
    @Override
    public void output() {
        if (this.checkFormat) {
            System.out.println("Conversion: "+this.kilobytes + " KB = " + megaBytes + " MB and " + remainKb + " KB");
        }
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
