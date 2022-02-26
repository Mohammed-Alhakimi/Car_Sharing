import java.util.Scanner;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println(IntStream.rangeClosed(a, b)
                .filter(number -> number % n == 0 || number % m == 0)
                .reduce(0, Integer::sum));
    }
}