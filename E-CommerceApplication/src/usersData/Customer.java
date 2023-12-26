package usersData;

import java.util.Map;

public class Customer extends UserModel {
    private ShoppingCart cart;

    public Customer(String fullname, String email, String username, String password) {
        super(fullname, email, username, password);
        cart = new ShoppingCart();
        cart.setHolderUsername(username);
    }

    public void resetCart() {
        cart.emptyCart();
        cart = new ShoppingCart();
        cart.setHolderUsername(super.getUsername());
    }

    public void addToCart(String productID, int quantity) {
        cart.addProduct(productID, quantity);
    }

    public void removeFromCart(String productID) {
        cart.removeProduct(productID);
    }

    public void updateProductQuantity(String productID, int quantity) {
        cart.updateProductQuantity(productID, quantity);
    }

    public double calculateTotalPrice(String productID) {
        return cart.calculateTotalPrice(productID);
    }

    public double calculateTotalPrice() {
        return cart.calculateTotalPrice();
    }

    public Map<String, Integer> getCartItems() {
        return cart.getItems();
    }

    public String getCartProductID() {
        return cart.getCartProductID();
    }

    public String toString() {
        return "Type: Customer\n" + super.toString();

    }
}
