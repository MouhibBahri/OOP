package services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface ISharedFunctions {
    default void println(String message) {
        System.out.println(message);
    }

    default void print(String message) {
        System.out.print(message);
    }

    default void printSep(String m, int length) {
        println(m.repeat(length));
    }

    default void printMenu(String message) {
        int length = message.length() + 4;
        printSep("*", Math.max(0, length));

        println("* " + message + " *");

        printSep("*", Math.max(0, length));
    }

    default void printWithoutSep(String message, String m) {

        println(m + " " + message + " ".repeat(Math.max(0, 41 - message.length())) + m);
    }

    default int getIntInput(String prompt, int minValue, int maxValue) {
        Scanner sc = new Scanner(System.in);
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                if (input >= minValue && input <= maxValue) {
                    break;
                } else {
                    println(prompt);
                }
            } catch (InputMismatchException e) {
                println("Invalid input. Please enter a valid number.");
                sc.next(); // Consume the invalid input
            }
        }
        return input;
    }

    default void printlnFormat(String msg, String[] args) {
        println(MessageFormat.format(msg, (Object[]) args));
    }

    default double roundPrice(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
