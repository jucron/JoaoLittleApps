package com.joao_renault.general_store;

import java.util.Map;
import java.util.Random;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        Random random = new Random();

        stockList.addStock(new StockItem("bread", 0.86, random.nextInt(10)));
        stockList.addStock(new StockItem("cake", 2.10, random.nextInt(10)));
        stockList.addStock(new StockItem("towel", 15.40, random.nextInt(8)));
        stockList.addStock(new StockItem("chocolate", 1.50, random.nextInt(15)));
        stockList.addStock(new StockItem("meat", 5.0, random.nextInt(3)));
        stockList.addStock(new StockItem("cup", 4.50, random.nextInt(20)));
        stockList.addStock(new StockItem("pencil", 0.45,random.nextInt(25)));
        stockList.addStock(new StockItem("doorknob", 25.45, random.nextInt(4)));
        stockList.addStock(new StockItem("juice", 2.50, random.nextInt(10)));
        stockList.addStock(new StockItem("phone", 96.99, random.nextInt(2)));
        stockList.addStock(new StockItem("vase", 8.76, random.nextInt(5)));
        stockList.addStock(new StockItem("orange", 0.21, random.nextInt(40)));

        System.out.println(stockList);

//        for(String s: stockList.Items().keySet()) {
//            System.out.println(s);
//        }
        Basket timsBasket = new Basket("Tim");
        reserveItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        reserveItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        reserveItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        reserveItem(timsBasket, "spanner", 5);
        System.out.println(timsBasket);

        reserveItem(timsBasket, "juice", 4);
        reserveItem(timsBasket, "cup", 12);
        reserveItem(timsBasket, "bread", 1);
        System.out.println(timsBasket);

        Basket basket = new Basket("customer");
        reserveItem(basket,"cup", 100);
        reserveItem(basket,"juice", 5);
        removeItem(basket,"cup", 1);
        System.out.println(basket);

        removeItem(timsBasket, "car",1);
        removeItem(timsBasket, "cup",9);
        removeItem(timsBasket, "car",1);
        System.out.println("cars removed: "+removeItem(timsBasket,"car",1)); //should not remove
        System.out.println(timsBasket);

        //remove all items from timsBasket:
        removeItem(timsBasket,"bread",1);
        removeItem(timsBasket,"cup",3);
        removeItem(timsBasket,"juice",4);
        removeItem(timsBasket,"cup",3);
        System.out.println(timsBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);


        //        temp = new StockItem("pen", 1.12);
//        stockList.Items().put(temp.getName(),temp); We can't do that, because it is unmodifiable Map
        stockList.Items().get("car").adjustStock(2000);
        stockList.get("car").adjustStock(-1000);
        System.out.println(stockList);

//        for (Map.Entry<String, Double> price: stockList.PriceList().entrySet()) {
//            System.out.println(price.getKey() + " costs "+price.getValue());
//        }

        checkOut(timsBasket);
        System.out.println(timsBasket);
    }
    public static int reserveItem(Basket basket, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell "+ item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem,quantity);
        }
        System.out.println("There is no more "+stockItem.getName()+" in the stock.");
        return 0;
    }
    public static int removeItem(Basket basket, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell "+ item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        System.out.println("There is no more "+stockItem.getName()+" in the basket.");

        return 0;
    }
    public static void checkOut (Basket basket) {
        for (Map.Entry<StockItem, Integer> item: basket.items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }

}
