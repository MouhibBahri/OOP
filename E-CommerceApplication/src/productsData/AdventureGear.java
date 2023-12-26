package productsData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdventureGear extends ProductModel {
    private String terrain;
    private boolean isDurable;

    public AdventureGear() {
        super();
        this.terrain = "";
        this.isDurable = false;
    }

    public void readSpecificAttributes(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        this.terrain = line.substring("Terrain: ".length());
        line = reader.readLine();
        this.isDurable = Boolean.parseBoolean(line.substring("Durable: ".length()));
    }

    public void writeSpecificAttributes(BufferedWriter writer) throws IOException {
        writer.write("Terrain: " + terrain + "\n");
        writer.write("Durable: " + isDurable + "\n");
    }

    @Override
    public String toString() {
        String res = "-----------------------------\n" + super.toString();
        res += "Terrain: " + terrain + "\n" + "Durable: " + (isDurable ? "Yes" : "No") + "\n";
        res += "-----------------------------";
        return res;

    }

    public void getProductFromInput() {
        Scanner sc = new Scanner(System.in);
        super.getProductFromInput();
        System.out.print("Terrain: ");
        this.terrain = sc.nextLine();
        System.out.print("Durable(Yes/No): ");
        this.isDurable = sc.nextLine().equals("Yes");
    }

}

