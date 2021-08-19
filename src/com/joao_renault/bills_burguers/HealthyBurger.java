package com.joao_renault.bills_burguers;

public class HealthyBurger extends Hamburger {
    private String healthyExtra1Name;
    private double healthyExtra1Price;
    private String healthyExtra2Name;
    private double healthyExtra2Price;

    // Setter with some parameters in default
    public void setHealthyBurger() {
        super.setHamburger("Healthy", "Soy Meat", 5.15, "Brown rye");
        // Additions exclusive for Healthy Burgers
        super.addHamburgerAddition("Caesar Salad", 3.15);
        super.addHamburgerAddition("Orange Juice", 4.50);
    }
    @Override
    public void addHamburgerAddition(String additionName, double additionPrice) {
        System.out.println("Cannot add additional items to Healthy Burger");
    }
}
