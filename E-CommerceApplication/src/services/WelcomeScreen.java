package services;

import usersData.Customer;
import usersData.UserModel;
import usersData.UsersList;

import java.util.Scanner;

public class WelcomeScreen implements ISharedFunctions {
    private final Scanner sc;
    private UserModel user;

    public WelcomeScreen() {
        sc = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        println("**********************************************");
        println("*           Welcome to HikeHaven             *");
        println("**********************************************");
        println("*                                            *");
        println("*  Embark on a shopping adventure with       *");
        println("*  our E-Commerce Console Application!       *");
        println("*                                            *");
        println("           * Made by Mouhib Bahri *           ");
        println("*                                            *");
        println("**********************************************\n");
    }

    private void login() {
        printMenu("Login as Admin (1) or customer (2)? Your pick! \uD83D\uDE04");
        int type = getIntInput("Rolling the dice, huh? Enter 1 for admin or 2 for customer. No middle ground here! \uD83C\uDFB2\uD83E\uDD14", 1, 2);

        print("Username: ");
        String username = sc.nextLine();
        print("Password: ");
        String password = sc.nextLine();
        user = UsersList.login(username, password, type);
        while (user == null) {
            println("(Enter 0 to return to Home Menu)");
            print("Username: ");
            username = sc.nextLine();
            if (username.equals("0")) {
                landingScreen();
                return;
            }
            print("Password: ");
            password = sc.nextLine();
            user = UsersList.login(username, password, type);
        }
        if (user instanceof Customer)
            printMenu("Welcome back, " + username + "! \uD83C\uDF89\uD83D\uDC4B Let the shopping adventure continue! \uD83D\uDECD️\uD83D\uDE80");
        else
            printMenu("Welcome to Admin Service, " + username + "! \uD83C\uDF89\uD83D\uDC4B");

    }

    private void signup() {

        printMenu("Awesome choice! \uD83C\uDF1F Let's get personal. Please enter your full name: \uD83D\uDC64✨");
        String fullname = sc.nextLine();
        printMenu("Great step! \uD83D\uDCE7✉️ Please enter your email address: \uD83C\uDF10\uD83D\uDC69\u200D\uD83D\uDCBB");
        String email = sc.nextLine();
        printMenu("Fantastic! \uD83D\uDE80\uD83C\uDF89 Now, choose a unique username: \uD83C\uDD94\uD83D\uDD10");
        String username = sc.nextLine();
        while (UsersList.checkUsername(username)) {
            println("Whoops! \uD83D\uDE4A It seems there's already an account with that username. Please choose another one. \uD83D\uDD04\uD83C\uDD94");
            username = sc.nextLine();
        }
        printMenu("Lock it down! \uD83D\uDD12\uD83D\uDCAA Please enter a strong password: \uD83D\uDD10\uD83D\uDD11");
        String password = sc.nextLine();
        user = new Customer(fullname, email, username, password);
        UsersList.addUser(user);
        printMenu("Welcome abroad, " + username + "! \uD83C\uDF89\uD83D\uDC4B Let the shopping adventure begin! \uD83D\uDECD️\uD83D\uDE80");
    }

    public void landingScreen() {
        println("**********************************************");
        println("*  1. Log In - Ready to rediscover your      *");
        println("*              shopping haven?               *");
        println("*  2. Sign Up - New to the retail galaxy?    *");
        println("*               Join us on this retail       *");
        println("*               exploration!                 *");
        println("*  3. Exit - No more shopping adventures?    *");
        println("**********************************************");

        int choice = getIntInput("Whoops! Please enter 1,2 or 3 to continue. \uD83E\uDD14", 1, 3);

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            case 3:
                System.exit(0);
        }

        println("");
        IService service;
        if (user instanceof Customer) {
            service = new CustomerService(user);
        } else {
            service = new AdminService();
        }
        service.entryScreen();

    }
}