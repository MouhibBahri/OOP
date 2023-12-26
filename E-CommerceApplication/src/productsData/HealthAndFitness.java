package productsData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class HealthAndFitness extends ProductModel {
    private String category;
    private boolean isOrganic;

    public HealthAndFitness() {
        super();
        this.category = "";
        this.isOrganic = false;
    }

    public void readSpecificAttributes(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        this.category = line.substring("Subcategory: ".length());
        line = reader.readLine();
        this.isOrganic = Boolean.parseBoolean(line.substring("Organic: ".length()));
    }

    public void writeSpecificAttributes(BufferedWriter writer) throws IOException {
        writer.write("Subcategory: " + category + "\n");
        writer.write("Organic: " + isOrganic + "\n");
    }

    @Override
    public String toString() {
        String res = "-----------------------------\n" + super.toString();
        res += "Subcategory: " + category + "\n" + "Organic: " + (isOrganic ? "Yes" : "No") + "\n";
        res += "-----------------------------";
        return res;

    }

    public void getProductFromInput() {
        Scanner sc = new Scanner(System.in);
        super.getProductFromInput();
        System.out.print("Subcategory: ");
        this.category = sc.nextLine();
        System.out.print("Organic(Yes/No): ");
        this.isOrganic = sc.nextLine().equals("Yes");
    }
}
