package services;

import productsData.*;
import usersData.*;

import java.util.*;
import java.util.stream.Collectors;

public class AdminService implements ISharedFunctions, IService {

    public void entryScreen() {
        println("**********************************************");
        println("*     What area would you like to manage?    *");
        println("*  1. Accounts                               *");
        println("*  2. Products                               *");
        println("*  3. Orders                                 *");
        println("*  4. Generate Reports                       *");
        println("*  5. Log Out                                *");
        println("**********************************************");

        int choice = getIntInput("Almost there! \uD83C\uDFB5 How about a number that grooves between 1 and 6? \uD83D\uDD7A", 1, 6);
        IEntityManager manager;
        switch (choice) {
            case 1:
                manager = new ManageAccounts();
                manager.ManageEntity();
                break;
            case 2:
                manager = new ManageProducts();
                manager.ManageEntity();
                break;
            case 3:
                manager = new ManageOrders();
                manager.ManageEntity();
                break;
            case 4:
                ManageReports report = new ManageReports();
                report.show();
                break;
            case 5:
                WelcomeScreen startingScreen = new WelcomeScreen();
                startingScreen.landingScreen();
                return;

        }
        entryScreen();
    }


}

class ManageReports implements ISharedFunctions {
    public void show() {
        println("==============================================");
        println("==                REPORT                    ==");
        printWithoutSep(" ", "==");
        averageOrderValue();
        lowestOrder();
        highestOrder();
        printWithoutSep(" ", "==");
        bestSellingProducts();
        printWithoutSep(" ", "==");
        lowStockProducts();
        println("==============================================");

    }

    private void lowStockProducts() {
        printWithoutSep("Products low in stock:", "==");
        for (ProductModel product : ProductsList.getProductsList()) {
            if (product.getQuantityInStock() < 5) {
                printWithoutSep(product.getProductId() + "  " + ProductsList.getProductById(product.getProductId()).getProductName() + "  " + product.getQuantityInStock(), "==");
            }
        }
    }

    private void bestSellingProducts() {
        ArrayList<OrderModel> orders = OrdersList.getOrdersList();
        Map<String, Integer> products = new HashMap<>();

        for (OrderModel order : orders) {
            for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
                String productId = entry.getKey();
                int quantitySold = entry.getValue();

                products.put(productId, products.getOrDefault(productId, 0) + quantitySold);
            }
        }
        printWithoutSep("Best selling products:", "==");

        Map<String, Integer> sortedProducts = products.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));


        sortedProducts.forEach((productID, quantitySold) ->
                printWithoutSep(productID + "  " + ProductsList.getProductById(productID).getProductName() + "  " + quantitySold, "=="));

    }

    private void averageOrderValue() {
        ArrayList<OrderModel> orders = OrdersList.getOrdersList();
        double total = 0;
        for (OrderModel order : orders) {
            total += order.getTotalPrice();
        }
        total /= orders.size();
        printWithoutSep("Average order value: $" + roundPrice(total), "==");
    }

    private void lowestOrder() {
        ArrayList<OrderModel> orders = OrdersList.getOrdersList();
        if (orders.isEmpty()) {
            printWithoutSep("Lowest order value: $0.0", "==");
        }
        double minPrice = orders.getFirst().getTotalPrice();

        for (int i = 1; i < orders.size(); i++) {
            double totalPrice = orders.get(i).getTotalPrice();
            if (totalPrice < minPrice) {
                minPrice = totalPrice;
            }
        }
        printWithoutSep("Lowest order value: $" + minPrice, "==");
    }

    private void highestOrder() {
        ArrayList<OrderModel> orders = OrdersList.getOrdersList();

        if (orders.isEmpty()) {
            printWithoutSep("Highest order value: $0.0", "==");
        }
        double maxPrice = orders.getFirst().getTotalPrice();

        for (int i = 1; i < orders.size(); i++) {
            double totalPrice = orders.get(i).getTotalPrice();
            if (totalPrice > maxPrice) {
                maxPrice = totalPrice;
            }
        }
        printWithoutSep("Highest order value: $" + maxPrice, "==");
    }
}

class ManageOrders implements IEntityManager {
    private final Scanner sc;

    public ManageOrders() {
        sc = new Scanner(System.in);
    }

    public void ManageEntity() {
        showOptions("Order", "Rebel number! 🕵️‍♂️ Meet a chill 1-6 buddy. 🌍");
    }

    public String getEntityID(boolean exists) {
        print("Order ID: ");
        String orderId = sc.next();
        while (OrdersList.getOrder(Integer.parseInt(orderId)) == null) {
            println("Oops! Order ID not found. Please try again: ");
            orderId = sc.next();
        }
        return orderId;
    }

    private OrderModel readOrder() {
        print("Customer Username: ");
        String customer = sc.next();
        while (!UsersList.checkUsername(customer) || !(UsersList.getUser(customer) instanceof Customer)) {
            println("Oops! Username not found. Please try again: ");
            customer = sc.next();
        }
        double price = 0;
        Map<String, Integer> items = new HashMap<>();
        println("Products:(press 0 when done)");
        String productId = sc.next();
        while (!productId.equals("0")) {
            while (ProductsList.getProductById(productId) == null) {
                println("Oops! Product ID not found. Please try again: ");
                productId = sc.next();
            }
            print("Quantity: ");
            int quantity = sc.nextInt();
            while (quantity <= 0 || ProductsList.getProductById(productId).getQuantityInStock() < quantity) {
                println("Oops! Invalid quantity. Please try again: ");
                quantity = sc.nextInt();
            }
            items.put(productId, quantity);
            price += ProductsList.getProductById(productId).getProductPrice() * quantity;
            println("Products:(press 0 when done)");
            productId = sc.next();
        }
        return new OrderModel(OrdersList.getNextOrderID(), (Customer) UsersList.getUser(customer), items, roundPrice(price));
    }

    public void addEntity() {
        OrdersList.addOrder(readOrder());
        println("Order added successfully!");
    }

    public void viewEntity() {
        String orderId = getEntityID(true);
        println(OrdersList.getOrder(Integer.parseInt(orderId)).toString());
    }

    public void updateEntity() {
        String orderId = getEntityID(true);
        OrderModel order = readOrder();
        order.setOrderId(Integer.parseInt(orderId));

        OrdersList.updateOrder(order);
        println("Order updated successfully!");
    }

    public void deleteEntity() {
        String orderId = getEntityID(true);
        OrdersList.removeOrder(Integer.parseInt(orderId));
        println("Order deleted successfully!");
    }

    public void viewAll() {
        print(OrdersList.displayOrders());
    }
}

class ManageProducts implements IEntityManager {
    private final Scanner sc;

    public ManageProducts() {
        sc = new Scanner(System.in);
    }

    public void ManageEntity() {
        showOptions("Product", "Nice mystery number! \uD83D\uDD75️\u200D♂️ Our game loves lively digits, shoot for 1-6! \uD83C\uDFB2");

    }

    public String getEntityID(boolean exists) {
        print("Product ID: ");
        String productId = sc.nextLine();
        while (exists ^ ProductsList.getProductById(productId) != null) {
            println(exists ? "Oops! Product ID not found. Please try again: " : "Wo-ha! The product ID is already in use. Please select a different one!");
            productId = sc.nextLine();
        }
        return productId;
    }

    public void addEntity() {
        println("""
                Category:
                1. Adventure Gear
                2. Travel Essentials
                3. Exploration Tools
                4. Health and Fitness
                5. Others""");
        int category = getIntInput("Whoa! 🚀 Cosmic journey! Let's bring it back for a cozy 1-5 hug on Earth! 🌍", 1, 5);
        ProductModel product = switch (category) {
            case 1 -> new AdventureGear();
            case 2 -> new TravelEssentials();
            case 3 -> new ExplorationTools();
            case 4 -> new HealthAndFitness();
            default -> new Others();
        };
        String productID = getEntityID(false);
        product.setProductId(productID);
        product.getProductFromInput();
        ProductsList.addProduct(product);
        println("Product added successfully!");
    }

    public void viewEntity() {
        String productId = getEntityID(true);
        println(ProductsList.getProductById(productId).toString());
    }

    public void updateEntity() {
        String productId = getEntityID(true);
        ProductModel product = ProductsList.getProductById(productId);
        product.getProductFromInput();
        ProductsList.updateProduct(product);
        println("Product updated successfully!");
    }

    public void deleteEntity() {
        String productId = getEntityID(true);
        ProductsList.removeProduct(productId);
        println("Product deleted successfully!");
    }

    public void viewAll() {
        print(ProductsList.displayProducts());
    }
}

class ManageAccounts implements IEntityManager {
    private static Scanner sc;

    public ManageAccounts() {
        sc = new Scanner(System.in);
    }

    public void ManageEntity() {
        showOptions("Account", "\"Error 404: Number not found! Please beam in a 1-6 from this galaxy! \\uD83C\\uDF0C\"");

    }

    public String getEntityID(boolean exists) {
        print("Username: ");
        String username = sc.nextLine();
        while (exists ^ UsersList.checkUsername(username)) {

            println(exists ? "Oops! Username not found. Please try again: " : "Wo-ha! Username already taken. Please pick another one!");
            username = sc.nextLine();
        }
        return username;
    }

    public void addEntity() {
        print("1. Admin / 2. Customer: ");
        int type = getIntInput("Hold the acrobatics! Your number's flipping. Let's land smoothly with a simple 1 or 2. \uD83D\uDEEC✨", 1, 2);
        print("Full Name: ");
        String fullname = sc.nextLine();
        print("Email: ");
        String email = sc.nextLine();
        String username = getEntityID(false);
        print("Password: ");
        String password = sc.nextLine();
        if (type == 1)
            UsersList.addUser(new Admin(fullname, email, username, password));
        else
            UsersList.addUser(new Customer(fullname, email, username, password));
        println("User added successfully!");
    }

    public void viewEntity() {
        String username = getEntityID(true);
        println(UsersList.getUser(username).toString());
    }

    public void updateEntity() {
        String username = getEntityID(true);
        print("Full Name: ");
        String fullname = sc.nextLine();
        print("Email: ");
        String email = sc.nextLine();
        print("Password: ");
        String password = sc.nextLine();
        UserModel user = (UsersList.getUser(username) instanceof Admin) ? new Admin(fullname, email, username, password) : new Customer(fullname, email, username, password);
        UsersList.updateUser(user);
        println("User updated successfully!");
    }

    public void deleteEntity() {
        String username = getEntityID(true);
        UsersList.deleteUser(username);
        println("User deleted successfully!\n");
    }

    public void viewAll() {
        print(UsersList.getUsers());
    }
}
