package com.joao_renault.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputFromUserImpl implements InputFromUser {
    private Scanner scanner = new Scanner(System.in);

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
    public long tryLongInput(){
        try {
            return scanner.nextLong();
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
}
