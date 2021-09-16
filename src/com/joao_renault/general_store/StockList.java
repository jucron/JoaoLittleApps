package com.joao_renault.general_store;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
        //Note that LinkedHashMaps are sorted based on element entry input.
    }
    public int addStock (StockItem item) {
        if (item != null) {
            /*check if already have quantities of this item. 'inStock' will be the item from the list,
            But if there isn't one there with that key, 'inStock' will be default('item') */
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // If there are already stocks on this item, adjust the quantity
            if (inStock != item) {
                item.adjustStock(inStock.availableQuantity());
            }
            list.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }
    /*
    The getOrDefault(Object key, V defaultValue) method of Map interface, implemented by HashMap class
    is used to get the value mapped with specified key. If no value is mapped with the provided key then
     the default value is returned.
     */
    public int sellStock (String item, int quantity) {
        //refactoring with the 'reserve' implementation
        StockItem inStock = list.get(item);
        if ((inStock != null) && (quantity>0)) {
            return inStock.finaliseStock(quantity);
        }
//        StockItem inStock = list.getOrDefault(item, null);
//        if ((inStock != null) && (inStock.availableQuantity() >= quantity) && (quantity >0)) {
//            inStock.adjustStock(-quantity);
//            return quantity;
//        }
        return 0; //nothing was processed
    }
    public int reserveStock (String item, int quantity) {
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity>0)) {
            return inStock.reserveStock(quantity);
        }
        return 0;
    }
    public int unreserveStock (String item, int quantity) {
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity>0)) {
            return inStock.unreserveStock(quantity);
        }
        return 0;
    }
    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item: list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }
    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
        /*this returns a read-only map and prevents other codes to add or remove elements.
        NOTE: Only the Collections methods comply with this unmodifiable contract, meaning that
        if you create other methods that modify the map elements, it will bypass this configuration.*/
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item: list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice()*stockItem.availableQuantity();

            if (stockItem.availableQuantity()>0) {
                s = s+" -"+stockItem+". There are "+stockItem.availableQuantity()+" in stock. Value of items: $";
                s = s+String.format("%.2f",itemValue)+"\n";
            } else {
                s = s + " -"+stockItem+". Out of stock.\n";
            }
            totalCost+= itemValue;
        }
        return s+"Total stock value $"+String.format("%.2f",totalCost);
    }
}
