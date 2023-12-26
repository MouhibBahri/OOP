package productsData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class ProductModel {
    private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int quantityInStock;
    private double score;

    public ProductModel() {
        this.productId = "";
        this.productName = "";
        this.productDescription = "";
        this.productPrice = 0.0;
        this.quantityInStock = 0;
        this.score = 0;
    }

    //Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public double getScore() {
        return score;
    }

    //Setters
    public void setScore(double score) {
        this.score = score;
    }

    //setters
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void addScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return
                "Product: " + productName + "\n" +
                        "Product ID: " + productId + "\n" +
                        "Description: " + productDescription + "\n" +
                        "Category: " + this.getClass().getName().split("\\.")[1] + "\n" +
                        "Quantity in Stock: " + quantityInStock + "\n" +
                        "Price: $" + productPrice + "\n";
    }

    public void readProduct(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        this.productId = line.substring("Product ID: ".length());
        line = reader.readLine();
        this.productName = line.substring("Product Name: ".length());
        line = reader.readLine();
        this.productDescription = line.substring("Description: ".length());
        line = reader.readLine();
        this.productPrice = Double.parseDouble(line.substring("Price: ".length()));
        line = reader.readLine();
        this.quantityInStock = Integer.parseInt(line.substring("Quantity in Stock: ".length()));
        readSpecificAttributes(reader);
        line = reader.readLine();
    }

    public abstract void readSpecificAttributes(BufferedReader reader) throws IOException;

    public void writeProduct(BufferedWriter writer) throws IOException {
        writer.write("Product ID: " + productId);
        writer.newLine();
        writer.write("Product Name: " + productName);
        writer.newLine();
        writer.write("Description: " + productDescription);
        writer.newLine();
        writer.write("Price: " + productPrice);
        writer.newLine();
        writer.write("Quantity in Stock: " + quantityInStock);
        writer.newLine();
        writeSpecificAttributes(writer);
        writer.newLine();
    }

    public abstract void writeSpecificAttributes(BufferedWriter writer) throws IOException;

    public void getProductFromInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Product Name: ");
        this.productName = sc.nextLine();
        System.out.print("Description: ");
        this.productDescription = sc.nextLine();
        System.out.print("Product Price: ");
        this.productPrice = sc.nextDouble();
        System.out.print("Quantity in Stock: ");
        this.quantityInStock = sc.nextInt();
    }


}
