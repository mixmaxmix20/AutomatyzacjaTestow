package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Client client;

    @BeforeEach
    void setup() {
        client = new Client(123);
    }

    @Test
    void constructor_shouldCreateShoppingCart() {
        assertNotNull(client.getShoppingCart());
    }

    @Test
    void getId_shouldReturnCorrectId() {
        assertEquals(123, client.getId());
    }

    @Test
    void getShoppingCart_shouldReturnSameShoppingCartInstance() {
        ShoppingCart cart = client.getShoppingCart();
        assertSame(cart, client.getShoppingCart());
    }

}
