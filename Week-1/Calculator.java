
import java.util.Scanner;

public class Calculator {

    public static double addition(double num1, double num2){
        return num1+num2;
    }
    public static double diff(double num1, double num2){
        return num1-num2;
    }
    public static double product(double num1, double num2){
        return num1*num2;
    }
    public static double division(double num1, double num2){
        if (num2==0){
            System.out.println("Error: Division by 0 is not valid");
            return 0;}
        else{
            return num1/num2;
        }
    }
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        double num1 , num2;
        String operator;
        String input;
        do{
        System.out.println("CALCULATOR");

        // User input for numbers
        System.out.println("Enter the first number: ");
        num1 = scanner.nextInt();
        System.out.print("Enter the second number: ");
        num2 = scanner.nextInt();

         // User input for operation
         System.out.print("Select the operation: + for Addition, - for Subtraction, * for Multiplication, / for Division: ");
         operator = scanner.next();

        switch (operator) {
            case "+":
                System.out.println("Sum:" +addition(num1,num2));
                break;
                case "-":
                System.out.println("Difference:" +diff(num1,num2));
                break;
                case "*":
                System.out.println("Multiplication:" +product(num1,num2));
                break;
                case "/":
                System.out.println("Division:" +division(num1,num2));
                break;
            default:
            System.out.println("Invalid operator");
                break;
        }
        System.out.println("Do you want to continue(y/n)");
        input = scanner.next();}
        while (input.equalsIgnoreCase("y"));

        System.out.println("Thank you for using the calculator. Goodbye!");
        scanner.close();
        
    }
}
