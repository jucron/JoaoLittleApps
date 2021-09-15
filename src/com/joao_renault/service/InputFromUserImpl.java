package com.joao_renault.service;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InputFromUserImpl implements InputFromUser {
    private Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public InputFromUserImpl() {
    }

    public int tryIntInput(){
        try {
            int answer = scanner.nextInt();
            scanner.nextLine();
            return answer;
        } catch (InputMismatchException e) {
            System.out.println("Input format error, please try again");
            scanner.nextLine();
        }
        return -1;
    }
    public double tryDoubleInput(){
        try {
            double answer = scanner.nextDouble();
            scanner.nextLine();
            return answer;
        } catch (InputMismatchException e) {
            System.out.println("Input format error, please try again");
            scanner.nextLine();
        }
        return -1;
    }
    public String tryStrInput(){
        try {
            return scanner.nextLine();
        } catch (InputMismatchException | NullPointerException e) {
            System.out.println("Input format error, please try again");
        }
        return null;
    }
    public void anyKeyToContinue () {
        try {
            System.out.println("Press any key to continue..");
            scanner.nextLine();
        } catch (InputMismatchException | NullPointerException e) {
            System.out.println("Try again.");
        }
    }
}
