package org.example;

public class Product {
    private final Integer id;
    private final String name;
    private double price;

    public Product(Integer id, String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Nieprawidłowa cena");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Nieprawidłowa cena");
        }
        this.price = price;
    }
}
