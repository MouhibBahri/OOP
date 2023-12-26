package usersData;

import productsData.*;
import services.ISharedFunctions;

import java.io.*;
import java.util.*;

public class ShoppingCart implements ISharedFunctions {
    private final Map<String, Map<String, Integer>> carts;//username,<productID,quantity>
    private static final String filePath = "shopping_carts.txt";
    private String holderUsername;
    private Map<String, Integer> items;

    public ShoppingCart() {
        carts = new HashMap<>();
        readCartsFromFile();
        holderUsername = "";
    }

    public void setHolderUsername(String holderUsername) {
        this.holderUsername = holderUsername;
        items = carts.computeIfAbsent(holderUsername, k -> new HashMap<>());

    }


    public String getCartProductID() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        String productId = sc.next();
        while (!items.containsKey(productId) && !productId.equals("0")) {
            System.out.println("Product not found in cart. Please try again.");
            System.out.print("Enter product ID (press 0 to go back): ");
            productId = sc.next();
        }
        return productId;
    }

    public void emptyCart() {

        carts.get(holderUsername).clear();
        writeCartsToFile();
    }

    public void addProduct(String productID, int quantity) {
        items.put(productID, quantity);
        writeCartsToFile();
    }

    public void removeProduct(String productID) {
        items.remove(productID);
        writeCartsToFile();
    }

    public void updateProductQuantity(String productID, int quantity) {
        items.replace(productID, quantity);
        writeCartsToFile();
    }

    public double calculateTotalPrice(String productID) {
        return roundPrice(ProductsList.getProductById(productID).getProductPrice() * items.get(productID));
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (String productID : items.keySet()) {
            totalPrice += ProductsList.getProductById(productID).getProductPrice() * items.get(productID);
        }
        return roundPrice(totalPrice);
    }

    public Map<String, Integer> getItems() {
        return items;
    }


    private void writeCartsToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String username : carts.keySet()) {
                for (String productID : carts.get(username).keySet()) {
                    writer.write(username + "," + productID + "," + carts.get(username).get(productID));
                    writer.newLine();

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readCartsFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(",");

                if (!carts.containsKey(parts[0])) //holder username
                    carts.put(parts[0], new HashMap<>());
                carts.get(parts[0]).put(parts[1], Integer.parseInt(parts[2]));//productID,quantity

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
