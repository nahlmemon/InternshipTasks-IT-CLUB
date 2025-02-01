public class DataTypesVariablesOperators {
    
    public static void main(String[] args) {

        // 1. Exploring Datatypes and variables
        // Primitive Datatypes
        int num = 5;
        char alph = 'A';
        
        // Printing on separate lines
        System.out.println("Output:");
        System.out.println("Number: " + num);
        System.out.println("Alphabet: " + alph);

        // Non-Primitive Datatype
        String str = "This is a Java program";
        System.out.println("String: " +str);

        // 2. Exploring Operators
        // Arithemetic Operators
        int num1 = 5;
        int num2 = 7;
        int sum = num1+num2;
        int diff = num1-num2;
        int product = num1*num2;
        System.out.println("Addition: " +num1 +" + " +num2 +" = " +sum);
        System.out.println("Difference: " +num1 +" - " +num2 +" = " +diff);
        System.out.println("Product: " +num1 +" * " +num2 +" = " +product);

        // 3. Control Stuctures
        boolean result;
        if (num1==num2){
            result = true;
        }
        else{
            result = false;
        }
        System.out.println("Result of Comparison: " +result);

    }
}
