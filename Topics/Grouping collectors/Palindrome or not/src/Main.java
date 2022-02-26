import java.util.*;
import java.util.stream.Collectors;


class CollectorPalindrome {

    public static void main(String[] args) {

        System.out.println(new LinkedHashMap<>(Arrays.stream(new Scanner(System.in).nextLine().split(" "))
                .collect(
                        Collectors.partitioningBy(s -> s.equals(new StringBuilder(s).reverse().toString()))
                )));
    }
}