import java.util.*;
import java.util.stream.Collectors;

class ProcessNumbers {
    private static final int SKIP_LESS_THAN = 10;

    public static void main(String[] args) {

        System.out.println(Arrays.stream(new Scanner(System.in)
                        .nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(HashSet::new))
                .stream().sorted().dropWhile(n -> n < SKIP_LESS_THAN)
                .collect(Collectors.toList()).stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}