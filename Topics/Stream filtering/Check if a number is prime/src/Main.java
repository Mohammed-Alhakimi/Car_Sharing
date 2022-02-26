import java.util.Scanner;
import java.util.stream.*;

class PrimeNumbers {

    /**
     * Checking if a number is prime
     *
     * @param number to test >= 2
     * @return true if number is prime else false
     */
    private static boolean isPrime(int number) {
        return IntStream.range(2, number)
                .filter(i -> number % i == 0)
                .count() == 0;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(new Scanner(System.in)
                .nextLine()
                .trim());
        System.out.println(isPrime(n) && n >= 2 ? "True" : "False");
    }
}