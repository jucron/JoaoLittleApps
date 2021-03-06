package com.joao_renault.general_store;

import com.joao_renault.little_apps.IEssentials;
import com.joao_renault.service.InputFromUser;
import com.joao_renault.service.InputFromUserImpl;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class GeneralStore implements IEssentials {

    private final InputFromUser input = new InputFromUserImpl();
    private static StockList stockList = new StockList();
    private Basket basket = new Basket("customer");
    private Random random = new Random();

    @Override
    public void execute() {
        System.out.println(description());
        // generate Store with random quantities
        generateStore();
        inputFromUser();
        output();
    }
    @Override
    public String description() {
        System.out.println("----------------------------General Store------------------------------------");
        return ("Welcome to my general store, stranger! What would you like to buy?.\n" +
                "Unfortunately this is small store, and our supplier is not really reliable. So you might see a few\n" +
                "variety of items, and the stock keep changing each time you come. But feel free to tell me what\n" +
                "you need and I'll put it in your basket. After we're done, we'll just count them. ");
    }
    @Override
    public void inputFromUser() {
        input.anyKeyToContinue();
        //ask for user which item should be added to basket
        boolean check = false;
        System.out.println("What would you like to add or remove in your basket?.\n" +
                    "Please write 'add' or 'remove' words followed by the quantity  \n"+
                    "and followed by the item's name (example: \"add 5 spoons\")\n"+
                    "when you're satisfied just say 'done', 'ok', 'no' or 'stop'.");
        while (!check) {
//            System.out.println("This is my stock, and your basket at this moment:");
            System.out.println(stockList);
            System.out.println(basket);

            String answer = input.tryStrInput();
            if (answer.length()>=1) {
                answer = answer.toLowerCase();
                String[] str = answer.split(" ");
                int quantity;
                switch (str[0]) {
                    case "done":
                    case "ok":
                    case "no":
                    case "stop":
                        check = true;
                        System.out.println("Alright, please pay the total value of your basket:");
                        System.out.println(basket);
                        break;
                    case "add":
                        try {
                            quantity=Integer.parseInt(str[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("I don't seem to understand your order, please repeat.");
                            break;
                        }
                        if (reserveItem(basket,checkPlural(str[2]),quantity) != 0) {
                            System.out.println("Added "+str[2]+" to the basket.");
                        }
                        break;
                    case "remove":
                        try {
                            quantity=Integer.parseInt(str[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("I don't seem to understand your order, please repeat.");
                            break;
                        }
                        if (removeItem(basket,checkPlural(str[2]),quantity) != 0) {
                            System.out.println("Removed from the basket.");
                        }
                        break;
                    default:
                        System.out.println("I don't seem to understand your order, please repeat.");
                        break;
                }
            }
        }
    }
    @Override
    public void output() {
        //wrap all items and checkout
        checkOut(basket);
        System.out.println("Would you like to buy more things?\n"+
                            "(the stock will remain the same)");
        String answer = input.tryStrInput().toLowerCase();
        if (Objects.equals(answer, "yes") || Objects.equals(answer, "y")) {
            inputFromUser();
        }
        System.out.println("Thank you for coming, next time well have more stock!");
        this.input.anyKeyToContinue();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Returning to main menu...");
    }
    private void generateStore () {
        stockList.addStock(new StockItem("bread", 0.86, random.nextInt(5)));
        stockList.addStock(new StockItem("cake", 2.10, random.nextInt(3)));
        stockList.addStock(new StockItem("towel", 15.40, random.nextInt(2)));
        stockList.addStock(new StockItem("chocolate", 1.50, random.nextInt(5)));
        stockList.addStock(new StockItem("meat", 5.23, random.nextInt(3)));
        stockList.addStock(new StockItem("cup", 4.50, random.nextInt(8)));
        stockList.addStock(new StockItem("pencil", 0.45,random.nextInt(10)));
        stockList.addStock(new StockItem("doorknob", 25.45, random.nextInt(3)));
        stockList.addStock(new StockItem("juice", 2.50, random.nextInt(10)));
        stockList.addStock(new StockItem("phone", 26.99, random.nextInt(2)));
        stockList.addStock(new StockItem("vase", 8.76, random.nextInt(5)));
        stockList.addStock(new StockItem("orange", 0.21, random.nextInt(25)));
        stockList.addStock(new StockItem("spoon", 1.25, random.nextInt(9)));
        stockList.addStock(new StockItem("broom", 1.25, random.nextInt(3)));
    }
    private int reserveItem(Basket basket, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell "+ item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem,quantity);
        }
        System.out.println("There is not enough "+stockItem.getName()+" in the stock.");
        return 0;
    }
    private int removeItem(Basket basket, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell "+ item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        System.out.println("There is no "+stockItem.getName()+" in the basket.");

        return 0;
    }
    private void checkOut (Basket basket) {
        for (Map.Entry<StockItem, Integer> item: basket.items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
    private String checkPlural (String str) {
        if (str.charAt(str.length() - 1) == 's') {
            return str.substring(0,str.length()-1);
        }
        return str;
    }
}
