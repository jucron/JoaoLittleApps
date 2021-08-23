package com.joao_renault;

import com.joao_renault.little_apps.*;
import com.joao_renault.bank.BankTerminal;
import com.joao_renault.bills_burguers.BillsBurgers;
import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

public class Main {
    private static InputFromUser input = new InputFromUserImpl();
    private static MegaBytesConverter app1 = new MegaBytesConverter();
    private static MinutesCalculator app2 = new MinutesCalculator();
    private static AllFactors app3 = new AllFactors();
    private static DiagonalStar app4 = new DiagonalStar();
    private static GreatestCommonDivisor app5 = new GreatestCommonDivisor();
    private static BillsBurgers app6 = new BillsBurgers();
    private static BankTerminal app7 = new BankTerminal();


    public static void main(String[] args) {
        System.out.println("Starting application...\n" +
                banner()+"\n"+
                ("-".repeat(48))+"Welcome to Joao Little Apps!"+("-".repeat(48))+"\n"+
                ("-".repeat(50))+"Created by: João Renault"+("-".repeat(50))+"\n");
        boolean quit = false;
        int choice;

        while (!quit) {
            optionsMenu();
            choice = input.tryIntInput();
            switch (choice) {
                case 0:
                    System.out.println("Shutting down...\n" +
                            "Thank you for visiting my App!");
                    quit=true;
                    break;
                case 1:
                    menuOfApps();
                    break;
                case 2:
                    purpose();
                    break;
                case -1: //In case of input error (non-integer)
//                    System.out.println("Input format error, please try again");
                    break;
                default: //in case no valid numbers are selected
                    System.out.println("Please try again with an existing option.");
            }
        }
    }
    private static void optionsMenu () {
        System.out.println("Please choose one option:");
        System.out.println("0 - Quit application");
        System.out.println("1 - Run a Little App");
        System.out.println("2 - What is the purpose of this?");
    }
    private static void littleAppsMenu() {
        System.out.println("Choose a Little App to run:");
        System.out.println("0 - Go back to main menu\n" +
                "1 - Mega Bytes Converter\n" +
                "2 - Minutes Calculator\n" +
                "3 - All factors\n" +
                "4 - Diagonal Star\n" +
                "5 - Greatest Common Divisor\n" +
                "6 - Bill's Burger\n" +
                "7 - Bank");
    }
    private static void menuOfApps() {
        littleAppsMenu();
        int appChoice = input.tryIntInput();
        switch (appChoice) {
            case 0:
                // Goes back to main menu
                break;
            case 1:
                app1.execute();
                break;
            case 2:
                app2.execute();
                break;
            case 3:
                app3.execute();
                break;
            case 4:
                app4.execute();
                break;
            case 5:
                app5.execute();
                break;
            case 6:
                app6.execute();
                break;
            case 7:
                app7.execute();
                break;
            default:
                System.out.println("Please try again with an existing option.");
                break;
            case -1: //In case of input error (non-integer)
                System.out.println("Returning to main menu...");
                break;
        }
    }
    private static void purpose () {
        System.out.println("*".repeat(50));
        System.out.println("Welcome to Johnny Little Apps!\n" +
                "I've created this small application after going through several hours of training in Java Language.\n" +
                "All these Little Apps are part of Udemy's course Java Programming Masterclass (by Tim Buchalka),\n" +
                "but they were all modified to fit in this compact application. They represent my learning process\n" +
                "of CORE Java. You're welcome to play around and check what I've created. I'm always open for \n" +
                "suggestions and improvements, so feel free to give me any feedbacks =)\n" +
                "PS: A reminder that this app is simple and linear, meaning it doesn't follow \n" +
                "advance concepts that frameworks do (no dependency injection and such)." +
                "Created by: João Renault");
        System.out.println("*".repeat(50));

    }
    private static String banner() {
        return ("\n" +
                "     ___  _______  _______  _______   ___      ___  _______  _______  ___      _______   _______  _______  _______  _______ \n" +
                "    |   ||       ||   _   ||       | |   |    |   ||       ||       ||   |    |       | |   _   ||       ||       ||       |\n" +
                "    |   ||   _   ||  |_|  ||   _   | |   |    |   ||_     _||_     _||   |    |    ___| |  |_|  ||    _  ||    _  ||  _____|\n" +
                "    |   ||  | |  ||       ||  | |  | |   |    |   |  |   |    |   |  |   |    |   |___  |       ||   |_| ||   |_| || |_____ \n" +
                " ___|   ||  |_|  ||       ||  |_|  | |   |___ |   |  |   |    |   |  |   |___ |    ___| |       ||    ___||    ___||_____  |\n" +
                "|       ||       ||   _   ||       | |       ||   |  |   |    |   |  |       ||   |___  |   _   ||   |    |   |     _____| |\n" +
                "|_______||_______||__| |__||_______| |_______||___|  |___|    |___|  |_______||_______| |__| |__||___|    |___|    |_______|\n");
    }
}
