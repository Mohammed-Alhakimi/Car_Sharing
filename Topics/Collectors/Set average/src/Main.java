import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) {

        System.out.println(Arrays.stream(new Scanner(System.in)
                        .nextLine()
                        .split("\\s+"))
                .map(Integer::parseInt)
                .distinct()
                .collect(Collectors
                        .averagingDouble(Integer::doubleValue)));
    }
}