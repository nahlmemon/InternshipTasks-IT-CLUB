import java.util.Scanner;

public class FactorialCalculator {

    // Loop-based factorial calculation
    public static int factCalcLoop(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }
        int fact = 1;
        for (int i = num; i >= 1; i--) {
            fact *= i;
        }
        return fact;
    }

    // Recursion-based factorial calculation
    public static int factCalcRecursion(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }
        return num * factCalcRecursion(num - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create Scanner object once
        String choice;  // Renamed variable for better clarity
        System.out.println("Factorial Calculator");
        do {
            System.out.print("Enter the number: ");
            int num = scanner.nextInt();

            System.out.println("Calculate Factorial using:\n1. Loops\n2. Recursion");
            int input = scanner.nextInt();

            if (input == 1) {
                System.out.println("The Factorial of " + num + " is: " + factCalcLoop(num));
            } else {
                System.out.println("The Factorial of " + num + " is: " + factCalcRecursion(num));
            }

            System.out.print("Do you want to continue? (y/n): ");
            choice = scanner.next(); // Read user input

        } while (choice.equalsIgnoreCase("y"));

        scanner.close(); 
    }
}
