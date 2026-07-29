package util;

import java.time.LocalDate;
import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static LocalDate readDate(String message) {

        while (true) {

            try {
                System.out.print(message);
                return LocalDate.parse(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Please enter the date in yyyy-MM-dd format.");
            }
        }
    }
    public static int readChoice(String message, int min, int max) {

        while (true) {

            int choice = readInt(message);

            if (choice >= min && choice <= max) {
                return choice;
            }

            System.out.println("Invalid choice.");
        }
    }
}