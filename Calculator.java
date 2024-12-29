import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        // Create a single Scanner instance for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Console Calculator!");

        while (true) {
            // Prompt user for the first and second numbers
            double num1 = getNumber(sc, "Enter the first number: ");
            double num2 = getNumber(sc, "Enter the second number: ");

            // Get a valid operation from the user
            String operation = getOperation(sc);

            try {
                // Perform the chosen operation and display the result
                double result = performOperation(num1, num2, operation);
                System.out.println("The result is: " + result);
            } catch (ArithmeticException e) {
                // Handle division by zero or other arithmetic errors
                System.out.println(e.getMessage());
                continue;
            }

            // Ask the user if they want to perform another calculation
            System.out.print("Do you want to perform another calculation? (yes/no): ");
            if (!sc.next().equalsIgnoreCase("yes")) {
                System.out.println("Thank you for using the calculator. Goodbye!");
                break; // Exit the loop if the user does not enter "yes"
            }
        }

        sc.close(); // Close the Scanner to release resources
    }

    /**
     * Prompts the user to enter a number and handles invalid inputs.
     *
     * @param sc     the Scanner instance for user input
     * @param prompt the message to display to the user
     * @return a valid double entered by the user
     */
    private static double getNumber(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.next()); // Parse the input as a double
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    /**
     * Prompts the user to choose a valid arithmetic operation.
     *
     * @param sc the Scanner instance for user input
     * @return a valid operation (+, -, *, /) as a string
     */
    private static String getOperation(Scanner sc) {
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println(" +  Addition");
            System.out.println(" -  Subtraction");
            System.out.println(" *  Multiplication");
            System.out.println(" /  Division");
            System.out.print("Enter your choice (+, -, *, /): ");
            String operation = sc.next();

            // Check if the input is one of the valid operations
            if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {
                return operation;
            } else {
                System.out.println("Invalid operation. Please choose from +, -, *, /.");
            }
        }
    }

    /**
     * Performs the specified arithmetic operation on two numbers.
     *
     * @param num1      the first number
     * @param num2      the second number
     * @param operation the operation to perform (+, -, *, /)
     * @return the result of the operation
     * @throws ArithmeticException if division by zero is attempted
     */
    private static double performOperation(double num1, double num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2; // Addition
            case "-":
                return num1 - num2; // Subtraction
            case "*":
                return num1 * num2; // Multiplication
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Error: Division by zero is not allowed."); // Handle division by zero
                }
                return num1 / num2; // Division
            default:
                throw new IllegalArgumentException("Invalid operation."); // This should never happen
        }
    }
}
