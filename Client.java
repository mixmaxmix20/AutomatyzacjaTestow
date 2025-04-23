package org.example;

public class Client {
    private final Integer id;
    private final ShoppingCart shoppingCart;

    public Client(Integer id) {
        this.id = id;
        this.shoppingCart = new ShoppingCart();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Integer getId() {
        return id;
    }
}
