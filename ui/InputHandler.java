package ui;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getPlayerInput(int max) {
        int input = -1;
        while (input < 1 || input > max) {
            System.out.print("Enter choice (1-" + max + "): ");
            String userInput = scanner.nextLine().trim();

            try {
                input = Integer.parseInt(userInput);
                if (input < 1 || input > max) {
                    System.out.println("Invalid input, please enter input within valid range.");
                    input = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter input within valid range.");
            }
        }
        return input;
    }
}