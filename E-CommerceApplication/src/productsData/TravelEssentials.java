package productsData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class TravelEssentials extends ProductModel {
    private String destination;
    private boolean isCompact;


    public TravelEssentials() {
        super();
        this.destination = "";
        this.isCompact = false;
    }

    public void readSpecificAttributes(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        this.destination = line.substring("Destination: ".length());
        line = reader.readLine();
        this.isCompact = Boolean.parseBoolean(line.substring("Compact: ".length()));
    }

    public void writeSpecificAttributes(BufferedWriter writer) throws IOException {
        writer.write("Destination: " + destination + "\n");
        writer.write("Compact: " + isCompact + "\n");
    }

    public String toString() {
        String res = "-----------------------------\n" + super.toString();
        res += "Destination: " + destination + "\n" + "Compact: " + (isCompact ? "Yes" : "No") + "\n";
        res += "-----------------------------";
        return res;

    }

    public void getProductFromInput() {
        Scanner sc = new Scanner(System.in);
        super.getProductFromInput();
        System.out.print("Destination: ");
        this.destination = sc.nextLine();
        System.out.print("Compact(Yes/No): ");
        this.isCompact = sc.nextLine().equals("Yes");
    }


}

