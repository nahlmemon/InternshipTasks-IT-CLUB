import java.util.Scanner;

public class PrimeNumberChecker {

    static boolean primeCheck(int n) {
                int divisorCount = 0;

        for (int i = 1; i <= n; i++) { // Check all numbers from 1 to n
            if (n % i == 0) {
                divisorCount++;
            }
        }
        return divisorCount == 2; // A prime number has exactly 2 divisors (1 and itself)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to check if it's prime: ");
        int n = scanner.nextInt();

        System.out.println(n + (primeCheck(n) ? " is a prime number." : " is not a prime number."));

        scanner.close();
    }
}
