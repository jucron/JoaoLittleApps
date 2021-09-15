package com.joao_renault.bills_burguers;

import com.joao_renault.little_apps.IEssentials;
import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

import java.util.Arrays;

public class BillsBurgers implements IEssentials {

        private Hamburger hamburger = new Hamburger();
        private DeluxeBurger deluxeBurger = new DeluxeBurger();
        private HealthyBurger healthyBurger = new HealthyBurger();
        private String meat;
        private double price;
        private boolean checkFormat = true;
        private String[] listOfMeats = new String[]{"Beef",  "Sausage", "Angus", "Pork", "Chicken", "Turkey"};
        private Double[] listOfMeatsPrice = new Double[]{4.10,  4.00, 5.50, 4.65, 3.51, 4.25};
        private String[] listOfAdditions = new String[]{"Chips", "Bacon", "Drinks", "Milkshake", "Nuggets"};
        private Double[] listOfAdditionsPrice = new Double[]{2.75, 1.55, 1.85, 3.00, 2.54};
        private final InputFromUser input = new InputFromUserImpl();

        @Override
        public void execute() {
                System.out.println(description());
                inputFromUser();
                output();
        }
        @Override
        public void inputFromUser() {
                price = 0;
                System.out.println("First I need to know your type of burger:\n" +
                        "1 - Customizable Burger\n" +
                        "2 - Deluxe Burger\n" +
                        "3 - Healthy Burger");
                int burgerChoice = input.tryIntInput();
                switch (burgerChoice) {
                        case 1:
                                requestMeat();
                                hamburger.setHamburger("Customizable",this.meat,this.price, "bun");
                                requestAdditions();
                                System.out.println("Nice choices! Here is what we have as your order:");
                                hamburger.itemizeHamburger();
                                break;
                        case 2:
                                deluxeBurger.setDeluxeBurger();
                                System.out.println("Ok, a Deluxe Burger it is! Here is what we have for you:");
                                deluxeBurger.itemizeHamburger();
                                break;
                        case 3:
                                healthyBurger.setHealthyBurger();
                                System.out.println("Let's get healthy! Here what you've ordered:");
                                healthyBurger.itemizeHamburger();
                                break;
                        default:
                                System.out.println("Please try again with an existing option.");
                                break;
                }
        }
        @Override
        public void output() {
                if (this.checkFormat) {
                        System.out.println("Bill's Burger appreciate your order, please come again!");
                        this.input.anyKeyToContinue();
                }
                System.out.println("----------------------------------------------------------------------------");
                System.out.println("Returning to main menu...");
        }
        @Override
        public String description() {
                System.out.println("----------------------------Bill's Burger------------------------------------");
                return ("Welcome to Bill's Burger! Can I take your order?.\n" +
                        "You can choose between our own exclusive combinations or a customizable burger. Our combos\n" +
                        "include the Deluxe Burger (our specialty) and the Healthy Burger (with exclusive side salad).\n");
        }
        private void requestMeat () {
                boolean check = false;
                System.out.println("Below is our list of meats available. Please write your option.");
                printListMeats();
                while (!check) {
                        String answer = input.tryStrInput();
                        if (answer.length()>=1) {
                                answer = answer.substring(0,1).toUpperCase()+answer.substring(1);
                        }
                        if (Arrays.asList(listOfMeats).contains(answer)) {
                                check = true;
                                int i = Arrays.asList(listOfMeats).indexOf(answer);
                                this.meat = listOfMeats[i]; this.price+= listOfMeatsPrice[i];
                                System.out.println(this.meat+" it is the choice!");
                        } else {
                                System.out.println("I don't seem to understand your order, please repeat.");
                        }
                }
        }
        private void requestAdditions () {
                boolean check = false;
                System.out.println("Below is our list of additions and prices.\n" +
                        "Please write your options one at a time, and when you're \n" +
                        "done just say 'done', 'ok', 'no' or 'stop'.");
                printListAdditions();
                while (!check) {
                        String answer = input.tryStrInput();
                        if (answer.length()>=1) {
                                answer = answer.substring(0,1).toUpperCase()+answer.substring(1);
                        }
                        switch (answer) {
                                case "Done":
                                case "Ok":
                                case "No":
                                case "Stop":
                                        check = true;
                                        System.out.println("Alright, we're done.");
                                        break;
                                default:
                                        if (Arrays.asList(listOfAdditions).contains(answer)) {
                                                int i = Arrays.asList(listOfAdditions).indexOf(answer);
                                                hamburger.addHamburgerAddition(answer,listOfAdditionsPrice[i]);
                                                System.out.println(answer+" added, what else can I add?");
                                        } else {
                                                System.out.println("I don't seem to understand your order, please repeat.");
                                        }
                                break;
                        }
                }
        }
        private void printListMeats (){
                System.out.print("Meats menu: ");
                for (int i =0; i<listOfMeats.length;i++) {
                        System.out.print(listOfMeats[i]+"[$"+listOfMeatsPrice[i]+"] ");
                }
                System.out.println();
        }
        private void printListAdditions (){
                System.out.print("Additions menu: ");
                for (int i =0; i<listOfAdditions.length;i++) {
                        System.out.print(listOfAdditions[i]+"[$"+listOfAdditionsPrice[i]+"] ");
                }
                System.out.println();
        }
}
