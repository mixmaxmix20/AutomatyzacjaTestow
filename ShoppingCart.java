package org.example;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Nieprawidłowa ilość");
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeItem(Product product) {
        if (!items.containsKey(product)) {
            throw new IllegalArgumentException("Produktu nie ma w koszyku");
        }
        items.remove(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

}
