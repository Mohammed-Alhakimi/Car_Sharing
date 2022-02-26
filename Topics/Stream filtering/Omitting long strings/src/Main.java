import java.io.*;
import java.util.*;
import java.util.stream.*;


public class Main {
    private static final int MINIMUM_LENGTH = 4;

    private static Stream<String> omitLongStrings(List<String> strings) {
        return strings.stream()
                .filter(s -> s.length() < MINIMUM_LENGTH);
    }

    public static void main(String[] args) throws IOException {
        omitLongStrings(
                new ArrayList<>(Arrays.asList(
                        new BufferedReader(
                                new InputStreamReader(System.in))
                                .readLine()
                                .split(" "))))
                .forEach(System.out::println);
    }
}
