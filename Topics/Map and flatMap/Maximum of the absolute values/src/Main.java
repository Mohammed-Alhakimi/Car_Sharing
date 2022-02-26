import java.util.*;
import java.util.stream.Stream;

public class Main {

    /**
     * Returns the largest absolute value in the array of numbers.
     *
     * @param numbers the input array of String integer numbers
     * @return the maximum integer absolute value in the array
     */
    public static int maxAbsValue(String[] numbers) {

        return Stream.of(numbers)
                .map(s -> Math.abs(Integer.parseInt(s)))
                .max(Comparator.naturalOrder()).orElse(-1);
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(maxAbsValue(scanner.nextLine().split("\\s+")));
    }
}