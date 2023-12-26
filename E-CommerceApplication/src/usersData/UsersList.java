package usersData;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public abstract class UsersList {
    private static final Map<String, UserModel> users;
    private static final String filePath = "accounts.txt";

    static {
        users = new TreeMap<>();
        readAccountsFromFile();
    }

    public static boolean checkUsername(String username) {
        return users.containsKey(username);
    }

    public static void addUser(UserModel user) {
        users.put(user.getUsername(), user);
        saveAccountsToFile();
    }

    public static void updateUser(UserModel user) {
        users.replace(user.getUsername(), user);
        saveAccountsToFile();
    }

    private static void saveAccountsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (UserModel user : users.values()) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readAccountsFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null && !line.isEmpty()) {

                UserModel currentAccount;
                String type = line.substring("Type: ".length());
                line = reader.readLine();
                String fullname = line.substring("Full Name: ".length());
                line = reader.readLine();
                String email = line.substring("Email: ".length());
                line = reader.readLine();
                String username = line.substring("Username: ".length());
                line = reader.readLine();
                String password = line.substring("Password: ".length());
                reader.readLine();
                if (type.equals("Admin"))
                    currentAccount = new Admin(fullname, email, username, password);
                else
                    currentAccount = new Customer(fullname, email, username, password);

                users.put(currentAccount.getUsername(), currentAccount);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static UserModel getUser(String username) {
        return users.get(username);
    }

    public static String getUsers() {
        StringBuilder allUsers = new StringBuilder();
        for (UserModel user : users.values()) {
            allUsers.append(user.toString());
            allUsers.append("\n");
        }
        return allUsers.toString();
    }

    public static UserModel login(String username, String password, int type) //type 1 => admin; type 2 => customer
    {
        if (!checkUsername(username) || (type == 1 && users.get(username) instanceof Customer) || (type == 2 && users.get(username) instanceof Admin)) //incorrect username
            System.out.println("Oops! It seems like the username you entered is incorrect. Please double-check and try again.");
        else if (!users.get(username).login(password)) //incorrect password
            System.out.println("Uh-oh! It looks like the password you entered is invalid. Please double-check and try again.");
        else return users.get(username);

        return null;
    }

    public static void deleteUser(String username) {
        users.remove(username);
        saveAccountsToFile();
    }
}
