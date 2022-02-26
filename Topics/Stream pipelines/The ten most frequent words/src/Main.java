import java.util.Arrays;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String[] inputArray = new Scanner(System.in).nextLine().replaceAll("[^[\\w\\s]]", "").split("\\s+");
        Arrays.stream(inputArray).map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10L)
                .forEach(stringLongEntry -> System.out.println(stringLongEntry.getKey()));

    }
}