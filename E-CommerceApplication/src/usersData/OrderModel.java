package usersData;

import productsData.ProductsList;

import java.io.*;
import java.util.*;

public class OrderModel {
    private int orderId;
    private Customer customer;
    private Map<String, Integer> items;//productID, quantity

    private double totalPrice;

    public OrderModel(Customer customer) {//in case of new order
        this.orderId = OrdersList.getNextOrderID();
        this.customer = customer;
        this.items = new TreeMap<>(customer.getCartItems());
        totalPrice = customer.calculateTotalPrice();
    }

    public OrderModel(int orderId, Customer customer, Map<String, Integer> items, double totalPrice) {//in case of reading

        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;

    }

    //getters
    public int getOrderID() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + "\n" + "Customer: " + customer.getUsername() + "\n" + "Items: " + items + "\n" + "Total Price: " + totalPrice + "\n";
    }


    public void saveOrder(BufferedWriter writer) throws IOException {

        writer.write("Order ID: " + orderId);
        writer.newLine();
        writer.write("Customer: " + customer.getUsername());
        writer.newLine();
        writer.write("Items: ");
        writer.newLine();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue());
            writer.newLine();
        }
        writer.write("Total Price: " + totalPrice);
        writer.newLine();

    }

    public void completeOrder() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Please add items to cart before proceeding to checkout.");
            return;
        }
        System.out.println("Proceeding to checkout for Order ID: " + orderId);
        System.out.println("Customer: " + customer.getUsername());
        System.out.println("Total price: " + customer.calculateTotalPrice());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter credit card number: ");
        String creditCardNumber = scanner.nextLine();

        System.out.print("Enter expiration date (MM/YYYY): ");
        String expirationDate = scanner.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();

        System.out.println("Processing payment...");

        try {
            OrdersList.addOrder(this);
            System.out.println("Payment successful!");
            updateInventory();
            customer.resetCart();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Order completed successfully.");
    }

    private void updateInventory() {
        List<String> productIDs = new ArrayList<>(items.keySet());
        for (String productID : productIDs) {
            ProductsList.getProductById(productID).setQuantityInStock(ProductsList.getProductById(productID).getQuantityInStock() - items.get(productID));
        }
        ProductsList.writeProductsToFile();
    }
}

