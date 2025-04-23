package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {
    private Product product;

    @BeforeEach
    void setup() {
        product = new Product(1, "testProduct", 5);
    }

    @Test
    void constructor_shouldThrowExceptionWhenPriceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "testProduct", 0));
    }

    @Test
    void constructor_shouldThrowExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "testProduct", -5));
    }

    @Test
    void constructor_shouldSetInitialValuesCorrectly() {
        assertEquals(1, product.getId());
        assertEquals("testProduct", product.getName());
        assertEquals(5, product.getPrice());
    }

    @Test
    void setPrice_shouldThrowExceptionWhenPriceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(0));
    }

    @Test
    void setPrice_shouldThrowExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(-5));
    }

    @Test
    void setPrice_shouldSetThePriceToTheCorrectAmount() {
        product.setPrice(15);
        assertEquals(15, product.getPrice());
    }
}
