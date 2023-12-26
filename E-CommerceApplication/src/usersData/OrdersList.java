package usersData;

import java.io.*;
import java.util.*;

public class OrdersList {
    private static final TreeMap<Integer, OrderModel> orders;

    private final static String filePath = "orders.txt";

    static {
        orders = new TreeMap<>();
        readOrdersFromFile();
    }

    public static int getNextOrderID() {
        return orders.isEmpty() ? 1 : orders.lastKey() + 1;
    }

    private static void readOrdersFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int orderID = Integer.parseInt(line.substring("Order ID: ".length()));
                line = reader.readLine();
                String username = line.substring("Customer: ".length());

                reader.readLine(); //"items: "
                line = reader.readLine();
                Map<String, Integer> items = new HashMap<>();
                while (line.length() < "Total Price: ".length() || !line.startsWith("Total Price: ")) {
                    String[] parts = line.split(" ");
                    items.put(parts[0], Integer.parseInt(parts[1]));
                    line = reader.readLine();
                }
                double totalPrice = Double.parseDouble(line.substring("Total price: ".length()));
                reader.readLine();//empty seperator line
                orders.put(orderID, new OrderModel(orderID, (Customer) UsersList.getUser(username), items, totalPrice));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeOrdersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Integer, OrderModel> entry : orders.entrySet()) {
                entry.getValue().saveOrder(writer);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<OrderModel> getOrdersList() {
        return new ArrayList<>(orders.values());
    }

    public static OrderModel getOrder(int orderId) {
        return orders.get(orderId);
    }

    public static Map<String, Integer> getProducts(String customerUsername) {

        Map<String, Integer> products = new HashMap<>();//productID,quantity

        for (OrderModel order : orders.values()) {
            if (order.getCustomer().getUsername().equals(customerUsername)) {
                for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
                    if (!products.containsKey(entry.getKey()))
                        products.put(entry.getKey(), entry.getValue());
                    else
                        products.put(entry.getKey(), products.get(entry.getKey()) + entry.getValue());
                }
            }
        }

        return products;
    }

    public static Map<String, Integer> getProducts() {
        Map<String, Integer> products = new HashMap<>();//productID,quantity

        for (OrderModel order : orders.values()) {
            for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
                if (!products.containsKey(entry.getKey()))
                    products.put(entry.getKey(), entry.getValue());
                else
                    products.put(entry.getKey(), products.get(entry.getKey()) + entry.getValue());
            }

        }

        return products;
    }

    public static void addOrder(OrderModel order) {
        orders.put(order.getOrderID(), order);
        writeOrdersToFile();

    }

    public static void removeOrder(int orderId) {
        orders.remove(orderId);
        writeOrdersToFile();
    }

    public static void updateOrder(OrderModel order) {
        orders.replace(order.getOrderID(), order);
        writeOrdersToFile();
    }

    public static String displayOrders() {
        StringBuilder allOrders = new StringBuilder();
        for (OrderModel order : orders.values()) {
            allOrders.append(order.toString());
            allOrders.append("\n");
        }
        return allOrders.toString();
    }
}
