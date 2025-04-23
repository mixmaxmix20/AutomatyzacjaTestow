package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private Product mockProduct1;
    private Product mockProduct2;

    @BeforeEach
    void setup() {
        cart = new ShoppingCart();
        mockProduct1 = mock(Product.class);
        mockProduct2 = mock(Product.class);

        when(mockProduct1.getPrice()).thenReturn(10.0);
        when(mockProduct2.getPrice()).thenReturn(20.0);
    }

    @Test
    void addItem_shouldAddProductToCart() {
        cart.addItem(mockProduct1, 2);
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().containsKey(mockProduct1));
        assertEquals(2, cart.getItems().get(mockProduct1));
    }

    @Test
    void addItem_shouldAddQuantityToExistingItem() {
        cart.addItem(mockProduct1, 1);
        cart.addItem(mockProduct1, 2);
        assertEquals(3, cart.getItems().get(mockProduct1));
    }

    @Test
    void addItem_shouldThrowExceptionWhenQuantityIsLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(mockProduct1, 0));
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(mockProduct1, -1));
    }

    @Test
    void removeItem_shouldRemoveProductFromCart() {
        cart.addItem(mockProduct1, 1);
        cart.removeItem(mockProduct1);
        assertFalse(cart.getItems().containsKey(mockProduct1));
    }

    @Test
    void removeItem_shouldThrowExceptionWhenProductIsNotInTheCart() {
        assertThrows(IllegalArgumentException.class, () -> cart.removeItem(mockProduct1));
    }

    @Test
    void calculateTotal_shouldReturnSumOfProducts() {
        cart.addItem(mockProduct1, 1);
        cart.addItem(mockProduct2, 2);

        assertEquals(50.0, cart.calculateTotal());
    }

    @Test
    void calculateTotal_shouldReturnZeroWhenCartIsEmpty() {
        assertEquals(0.0, cart.calculateTotal());
    }

    @Test
    void clear_shouldRemoveAllItems() {
        cart.addItem(mockProduct1, 1);
        cart.addItem(mockProduct2, 2);
        cart.clear();
        assertTrue(cart.getItems().isEmpty());
    }
}
