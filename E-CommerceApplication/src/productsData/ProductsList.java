package productsData;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProductsList {
    private static final Map<String, ProductModel> products;
    private static ArrayList<ProductModel> productsList;
    private final static String filePath = "products.txt";

    static {
        products = new TreeMap<>();
        productsList = new ArrayList<>();
        readProductsFromFile();
    }

    private static void readProductsFromFile() {


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String category = line.substring("Category: ".length());
                ProductModel product = switch (category) {
                    case "AdventureGear" -> new AdventureGear();
                    case "TravelEssentials" -> new TravelEssentials();
                    case "ExplorationTools" -> new ExplorationTools();
                    case "HealthAndFitness" -> new HealthAndFitness();
                    default -> new Others();
                };
                product.readProduct(reader);

                products.put(product.getProductId(), product);
                productsList.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ProductModel product : products.values()) {
                writer.write("Category: " + product.getClass().getName().split("\\.")[1] + "\n");
                product.writeProduct(writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ProductModel> getProductsList() {
        return productsList;
    }

    public static void rankProducts(String username) {
        productsList = RecommendationSystem.recommendProducts(username, products);
    }

    public static ArrayList<ProductModel> filterProducts(double maxPrice, double minPrice, String category, String productName, boolean inStock) {
        ArrayList<ProductModel> _products = new ArrayList<>(products.values());

        return _products.stream()
                .filter(product -> category == null || product.getClass().getName().split("\\.")[1].equals(category))
                .filter(product -> (maxPrice == -1 || product.getProductPrice() <= maxPrice) && (minPrice == -1 || product.getProductPrice() >= minPrice))
                .filter(product -> productName == null || product.getProductName().toLowerCase().contains(productName.toLowerCase()))
                .filter(product -> !inStock || product.getQuantityInStock() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ProductModel getProductById(String productId) {
        return products.get(productId);
    }

    public static void addProduct(ProductModel product) {
        products.put(product.getProductId(), product);
        writeProductsToFile();

    }

    public static void removeProduct(String productId) {
        products.remove(productId);
        writeProductsToFile();
    }

    public static void updateProduct(ProductModel product) {
        products.replace(product.getProductId(), product);
        writeProductsToFile();
    }

    public static String displayProducts() {
        StringBuilder allProducts = new StringBuilder();
        for (ProductModel product : products.values()) {
            allProducts.append(product.toString());
            allProducts.append("\n");
        }
        return allProducts.toString();
    }
}
