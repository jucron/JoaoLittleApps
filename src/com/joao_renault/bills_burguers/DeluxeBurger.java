package com.joao_renault.bills_burguers;

public class DeluxeBurger extends Hamburger {

     public void setDeluxeBurger() {
        super.setHamburger("Deluxe", "Buffalo", 7.50,"White" );

         // Additions exclusive for Healthy Burgers
        super.addHamburgerAddition("Chips", 2.75);
        super.addHamburgerAddition("Drinks", 1.85);
         super.addHamburgerAddition("Bacon", 1.05);
    }
    // Overriding additions methods from parent class, in order to execute them as expected in DeluxeBurger
    @Override
    public void addHamburgerAddition(String additionName, double additionPrice) {
        System.out.println("Cannot add additional items to Deluxe Burger");
    }
}
