package productsData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExplorationTools extends ProductModel {
    private String toolType;
    private boolean isPortable;

    public ExplorationTools() {
        super();
        this.toolType = "";
        this.isPortable = false;
    }

    public void readSpecificAttributes(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        this.toolType = line.substring("Tool Type: ".length());
        line = reader.readLine();
        this.isPortable = Boolean.parseBoolean(line.substring("Portable: ".length()));
    }

    public void writeSpecificAttributes(BufferedWriter writer) throws IOException {
        writer.write("Tool Type: " + toolType + "\n");
        writer.write("Portable: " + isPortable + "\n");
    }

    @Override
    public String toString() {
        String res = "-----------------------------\n" + super.toString();
        res += "Tool Type: " + toolType + "\n" + "Portable: " + (isPortable ? "Yes" : "No") + "\n";
        res += "-----------------------------";
        return res;
    }

    public void getProductFromInput() {
        Scanner sc = new Scanner(System.in);
        super.getProductFromInput();
        System.out.print("Tool Type: ");
        this.toolType = sc.nextLine();
        System.out.print("Portable(Yes/No): ");
        this.isPortable = sc.nextLine().equals("Yes");
    }

}
