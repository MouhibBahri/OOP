package productsData;

import usersData.OrdersList;

import java.util.*;

public class RecommendationSystem {


    public static ArrayList<ProductModel> recommendProducts(String targetUser, Map<String, ProductModel> allProducts) {


        Map<String, Integer> targetUserPurchasedProducts = OrdersList.getProducts(targetUser);//productID,quantity bought

        Map<String, Integer> categoryCount = new HashMap<>();//category,quantity of products in that category
        for (Map.Entry<String, Integer> ID_Quantity : targetUserPurchasedProducts.entrySet()) {
            ProductModel product = ProductsList.getProductById(ID_Quantity.getKey());
            String category = product.getClass().getSimpleName();

            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + ID_Quantity.getValue());
        }

        Map<String, Integer> productsSold = OrdersList.getProducts();
        //add (0.02*quantity bought by target user) for each product in the same category
        for (Map.Entry<String, ProductModel> product : allProducts.entrySet()) {
            product.getValue().setScore(0);
            if (!targetUserPurchasedProducts.containsKey(product.getValue().getProductId()) && categoryCount.containsKey(product.getValue().getClass().getSimpleName())) {
                double q = categoryCount.get(product.getValue().getClass().getSimpleName()) * 0.02;
                product.getValue().setScore(q);
            }

        }
        //add 0.01*quantity sold for each product
        for (Map.Entry<String, Integer> ID_Quantity : productsSold.entrySet()) {
            if (!targetUserPurchasedProducts.containsKey(ID_Quantity.getKey())) {
                ProductModel product = allProducts.get(ID_Quantity.getKey());
                product.addScore(0.01 * ID_Quantity.getValue());
            }
        }

        ArrayList<ProductModel> sortedProducts = new ArrayList<>(allProducts.values());

        sortedProducts.sort((product1, product2) -> {
            // Compare based on the score
            return Double.compare(product2.getScore(), product1.getScore());
        });

        return sortedProducts;
    }


}
