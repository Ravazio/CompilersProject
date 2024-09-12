package io.compiler.runtime;

public class ExpressionChecker {

	// Method to check if the string is a number or a valid equation
    public static int checkString(String input) {
        // Try to parse it as a number
        if (isNumber(input)) {
            return -1;  // It's a number
        }
        
        // Check if it's a valid equation (e.g., contains +, -, *, or / operators)
        if (isEquation(input)) {
            return -1;  // It's an equation
        }

        // If it's neither a number nor an equation, return 0
        return 0;
    }

    // Helper method to check if the input is a number
    private static boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper method to check if the input is a valid equation
    private static boolean isEquation(String input) {
        // A simple regex to check for basic math expressions like: 3 + 4, 5 * 6, etc.
        return input.matches("[-+]?\\d+(\\.\\d+)?([\\s]*[+\\-*/][\\s]*[-+]?\\d+(\\.\\d+)?)*");
    }
    
}
