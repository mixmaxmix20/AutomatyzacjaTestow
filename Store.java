package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private final Map<Product, Integer> inventory;
    private final List<Client> clients;
    private double revenue;

    public Store() {
        this.inventory = new HashMap<>();
        this.clients = new ArrayList<>();
        this.revenue = 0;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addProduct(Product product, int quantity) {
        if (!inventory.containsKey(product)) {
            inventory.put(product, quantity);
        } else {
            inventory.put(product, inventory.get(product) + quantity);
        }
    }

    public void processOrder(Client client) {
        ShoppingCart cart = client.getShoppingCart();
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Pusty koszyk");
        }
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
             if(inventory.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                throw new IllegalArgumentException("Brak produktu: " + entry.getKey().getName());
            }
        }
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            inventory.put(entry.getKey(), inventory.get(entry.getKey()) - entry.getValue());
        }
        revenue += cart.calculateTotal();
        cart.clear();
    }

    public double getRevenue() {
        return revenue;
    }

    public List<Client> getClients() {
        return clients;
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }
    public void removeProduct(Product product) {
        if (!inventory.containsKey(product)) {
            throw new IllegalArgumentException("Brak produktu " + product.getName() + " w inwentarzu");
        }
        inventory.remove(product);
    }

    public void removeClient(Client client) {
        if (!clients.contains(client)) {
            throw new IllegalArgumentException("Brak klienta " + client.getId() + " na liscie klientow");
        }
        clients.remove(client);
    }
}