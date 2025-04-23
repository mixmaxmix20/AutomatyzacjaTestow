package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class StoreTest {
    private Store store;
    private Client mockClient;
    private ShoppingCart mockCart;
    private Product mockProduct;

    @BeforeEach
    void setup() {
        store = new Store();
        mockClient = mock(Client.class);
        mockProduct = mock(Product.class);
        mockCart = mock(ShoppingCart.class);
    }

    @Test
    void addClient_shouldAddClientToStore() {
        store.addClient(mockClient);
        assertTrue(store.getClients().contains(mockClient));
    }

    @Test
    void addProduct_shouldAddStockToExistingProduct() {
        store.addProduct(mockProduct, 5);
        store.addProduct(mockProduct, 10);
        assertEquals(15, store.getInventory().get(mockProduct));
    }

    @Test
    void addProduct_shouldAddProductToInventory() {
        when(mockProduct.getId()).thenReturn(123);

        store.addProduct(mockProduct, 5);

        assertTrue(store.getInventory().containsKey(mockProduct));
        assertEquals(5, store.getInventory().get(mockProduct));
    }

    @Test
    void processOrder_shouldAddToRevenueAndClearCart() {
        when(mockClient.getShoppingCart()).thenReturn(mockCart);
        when(mockCart.calculateTotal()).thenReturn(100.0);
        when(mockProduct.getPrice()).thenReturn(100.0);

        HashMap<Product, Integer> cartItems = new HashMap<>();
        cartItems.put(mockProduct, 1);
        when(mockCart.getItems()).thenReturn(cartItems);
        when(mockProduct.getPrice()).thenReturn(100.0);

        store.addProduct(mockProduct, 1);
        store.processOrder(mockClient);

        assertEquals(100.0, store.getRevenue());
        verify(mockCart).clear();
    }

    @Test
    void processOrder_shouldThrowExceptionWhenProductNotInInventory() {
        when(mockProduct.getId()).thenReturn(123);
        when(mockClient.getShoppingCart()).thenReturn(mockCart);

        Map<Product, Integer> cartItems = new HashMap<>();
        cartItems.put(mockProduct, 3);
        when(mockCart.getItems()).thenReturn(cartItems);

        store.addProduct(mockProduct, 2);

        assertThrows(IllegalArgumentException.class, () -> store.processOrder(mockClient));
        assertEquals(0.0, store.getRevenue());
        verify(mockCart, never()).clear();
    }

    @Test
    void processOrder_shouldNotModifyInventoryWhenOrderFails() {
        when(mockProduct.getId()).thenReturn(123);
        when(mockClient.getShoppingCart()).thenReturn(mockCart);

        Map<Product, Integer> cartItems = new HashMap<>();
        cartItems.put(mockProduct, 5);
        when(mockCart.getItems()).thenReturn(cartItems);

        store.addProduct(mockProduct, 2);

        int initialInventory = store.getInventory().get(mockProduct);
        assertThrows(IllegalArgumentException.class, () -> store.processOrder(mockClient));
        assertEquals(initialInventory, store.getInventory().get(mockProduct));
    }

    @Test
    void processOrder_shouldThrowExceptionWhenCartIsEmpty() {
        when(mockClient.getShoppingCart()).thenReturn(mockCart);
        when(mockCart.getItems()).thenReturn(new HashMap<>());

        assertThrows(IllegalStateException.class, () -> store.processOrder(mockClient));
    }

    @Test
    void removeProduct_shouldRemoveProductFromInventory() {
        store.addProduct(mockProduct, 5);
        store.removeProduct(mockProduct);

        assertFalse(store.getInventory().containsKey(mockProduct));
    }

    @Test
    void removeProduct_shouldThrowExceptionWhenProductNotInInventory() {
        assertThrows(IllegalArgumentException.class, () -> store.removeProduct(mockProduct));
    }

    @Test
    void removeClient_shouldRemoveClientFromList() {
        store.addClient(mockClient);
        store.removeClient(mockClient);

        assertFalse(store.getClients().contains(mockClient));
    }

    @Test
    void removeClient_shouldThrowExceptionWhenClientNotOnClientList() {
         assertThrows(IllegalArgumentException.class, () -> store.removeClient(mockClient));
    }
}
