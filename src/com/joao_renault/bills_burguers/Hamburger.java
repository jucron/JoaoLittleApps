package com.joao_renault.bills_burguers;

import java.util.ArrayList;

public class Hamburger {
    // Base burger components
    private String name;
    private double price;
    private String breadRollType;
    private String meat;
    // Additions
    private ArrayList<Additions> additions = new ArrayList<>();

    // Setter of base burgers
    public void setHamburger(String name, String meat, double price, String breadRollType) {
        this.name = name;
        this.price = price;
        this.breadRollType = breadRollType;
        this.meat = meat;
    }

    // Methods to make additions to burger
    private class Additions {
        private String additionName;
        private double additionPrice;

        public String getAdditionName() {
            return additionName;
        }

        public double getAdditionPrice() {
            return additionPrice;
        }

        public Additions(String additionName, double additionPrice) {
            this.additionName = additionName;
            this.additionPrice = additionPrice;

        }

    }
    public void addHamburgerAddition(String additionName, double additionPrice) {
        additions.add(new Additions(additionName, additionPrice));
    }
    // Method to return the total cost of Hamburger
    public void itemizeHamburger() {
        double hamburgerPrice = this.price;
        System.out.println(this.name+ " hamburger "+"on a "+this.breadRollType+
                " roll with "+this.meat +", price is "+this.price);
        for (Additions addition: this.additions) {
            hamburgerPrice+=addition.getAdditionPrice();
            System.out.println("Added "+addition.getAdditionName()+" for an extra "+addition.getAdditionPrice());
        }
        System.out.println("The total price of your order will be: "+hamburgerPrice+".");

    }
}
