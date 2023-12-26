package services;

import productsData.ProductModel;
import productsData.ProductsList;
import usersData.Customer;
import usersData.OrderModel;
import usersData.UserModel;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.min;

public class CustomerService implements IService, ISharedFunctions {
    private final Scanner sc = new Scanner(System.in);
    private final UserModel user;
    private ArrayList<ProductModel> products;
    private int startTablePage;
    private int endTablePage;

    public CustomerService(UserModel user) {
        this.user = user;
        ProductsList.rankProducts(user.getUsername());
        products = ProductsList.getProductsList();
        startTablePage = 0;
        endTablePage = min(products.size(), 5);
    }

    public void entryScreen() {
        println("**********************************************************************");
        println("*                         Featured Products                          *");
        println("**********************************************************************");
        println("|   Product ID   |              Product Name               |  Price  |");
        println("|----------------|-----------------------------------------|---------|");
        for (int i = startTablePage; i < endTablePage; i++) {
            ProductModel p = products.get(i);
            String row = "|     " + p.getProductId() + "      |  " + p.getProductName() + " ".repeat(Math.max(0, 39 - p.getProductName().length())) +
                    "| $" + p.getProductPrice() +
                    " ".repeat(Math.max(0, 7 - String.valueOf(p.getProductPrice()).length())) +
                    "|";
            println(row);
        }
        println("**********************************************************************\n");
        displayShoppingOptions();
    }

    private void displayShoppingOptions() {
        println("Options:");
        println("1. Log Out                 |  5. Apply Filters");
        println("2. Previous Page           |  6. Explore Product");
        println("3. Next Page               |  7. Main Page");
        println("4. Search By Product Name  |  8. View Cart");

        println("**********************************************");
        int choice = getIntInput("Invalid choice. Please try again.", 1, 8);
        switch (choice) {
            case 1:
                WelcomeScreen startingScreen = new WelcomeScreen();
                startingScreen.landingScreen();
            case 2:
                if (startTablePage >= 5) {
                    startTablePage -= 5;
                    endTablePage = startTablePage + 5;
                }
                entryScreen();
                break;
            case 3:
                if (startTablePage + 5 < products.size()) {
                    startTablePage += 5;
                    endTablePage = min(products.size(), startTablePage + 5);
                }
                entryScreen();
                break;
            case 4:
                // search by product name
                print("Enter product name: ");
                String productName = sc.next();
                products = ProductsList.filterProducts(-1, -1, null, productName, false);
                startTablePage = 0;
                endTablePage = min(products.size(), 5);
                entryScreen();
                break;
            case 5:
                applyFilters();
                startTablePage = 0;
                endTablePage = min(products.size(), 5);
                entryScreen();
                break;
            case 6:
                exploreProduct();
                break;
            case 7:
                products = ProductsList.getProductsList();
                startTablePage = 0;
                endTablePage = min(products.size(), 5);
                entryScreen();
                break;
            case 8:
                viewCart();
                break;
            default:
                println("Invalid choice. Please try again.");
                displayShoppingOptions();
        }
    }

    private void applyFilters() {
        println("**********************************************************************");
        println("*                         Apply Filters                              *");
        println("**********************************************************************");

        String[] categories = {null, "AdventureGear", "TravelEssentials", "ExplorationTools", "HealthAndFitness", "Others"};
        print("Filter by category: ");
        for (int i = 1; i < categories.length; i++)
            print((i) + ". " + categories[i] + "  |  ");
        println("0. Skip");
        int category = getIntInput("Oops! The entered category is not valid. Please choose a valid category or enter 0 to skip.", 0, categories.length);

        println("Filter by price range: ");
        print("Enter minimum price (Enter 0 to skip): ");
        double minPrice = sc.nextDouble();
        if (minPrice == 0)
            minPrice = -1;
        print("Enter maximum price (Enter 0 to skip): ");
        double maxPrice = sc.nextDouble();
        if (maxPrice == 0)
            maxPrice = -1;

        print("Filter by product availability (Enter 0 to skip): ");
        boolean inStock = getIntInput("Invalid choice. Please enter 1 to filter in stock products or 0 to skip.", 0, 1) == 1;

        products = ProductsList.filterProducts(maxPrice, minPrice, categories[category], null, inStock);
    }

    private void exploreProduct() {
        print("Enter product ID (or press 0 to go back): ");
        String productId = sc.next();
        if (productId.equals("0")) {
            displayShoppingOptions();
            return;
        }
        ProductModel product = ProductsList.getProductById(productId);
        if (product == null) {
            println("Whoops! It seems like the product ID you entered is incorrect. Please double-check and try again.");
            exploreProduct();

        } else {
            println(product.toString());
            println("Options:");
            println("1. Add to Cart");
            println("2. Go Back");
            int choice = getIntInput("Invalid choice. Please try again.", 1, 2);
            if (choice == 1) {
                print("Enter quantity: ");
                int quantity = getIntInput("Oops! The adventure pack is running low on stock for the selected quantity. Adjust your journey plans!"
                        , 1, product.getQuantityInStock());

                ((Customer) user).addToCart(productId, quantity);
                println("Product added to cart successfully!\n");
                entryScreen();
            } else
                entryScreen();

        }
    }

    private void cartOptions() {
        println("Options:");
        println("1. Remove from Cart");
        println("2. Update Quantity");
        println("3. Proceed to Checkout");
        println("4. Go Back");
        String productId;
        int choice = getIntInput("Invalid choice. Please try again.", 1, 4);
        switch (choice) {
            case 1:
                productId = ((Customer) user).getCartProductID();
                if (productId.equals("0")) {
                    cartOptions();
                    break;
                }
                ((Customer) user).removeFromCart(productId);
                viewCart();
                break;
            case 2:
                productId = ((Customer) user).getCartProductID();
                if (productId.equals("0")) {
                    cartOptions();
                    break;
                }
                print("Enter quantity: ");
                int quantity = getIntInput("Oops! The adventure pack is running low on stock for the selected quantity!\nEnter quantity: "
                        , 0, Objects.requireNonNull(ProductsList.getProductById(productId)).getQuantityInStock());
                if (quantity == 0)
                    ((Customer) user).removeFromCart(productId);
                else
                    ((Customer) user).updateProductQuantity(productId, quantity);
                viewCart();
                break;
            case 3:
                OrderModel order = new OrderModel((Customer) user);
                order.completeOrder();
                entryScreen();
                break;
            case 4:
                entryScreen();
                break;
            default:
                println("Invalid choice. Please try again.");
                cartOptions();
        }
    }

    private void viewCart() {
        println("*****************************************************************************************");
        println("*                                  My Shopping Cart                                     *");
        println("*****************************************************************************************");
        println("|   Product ID   |              Product Name               |  Total Price  |  Quantity  |");
        println("|----------------|-----------------------------------------|---------------|------------|");
        Map<String, Integer> cartItems = ((Customer) user).getCartItems();
        for (String productId : cartItems.keySet()) {
            double totalPrice = ((Customer) user).calculateTotalPrice(productId);
            ProductModel p = ProductsList.getProductById(productId);
            assert p != null;
            String row = "|     " + p.getProductId() + "      |  " + p.getProductName() + " ".repeat(Math.max(0, 39 - p.getProductName().length())) +
                    "|    $" + totalPrice +
                    " ".repeat(Math.max(0, 10 - Double.toString(totalPrice).length())) +
                    "|     " + cartItems.get(productId) +
                    " ".repeat(Math.max(0, 7 - String.valueOf(cartItems.get(productId)).length())) + "|";
            println(row);
        }
        println("*****************************************************************************************");
        cartOptions();
    }
}
