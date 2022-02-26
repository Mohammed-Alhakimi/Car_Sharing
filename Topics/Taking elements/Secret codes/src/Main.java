import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println(Arrays.stream(new Scanner(System.in).nextLine()
                        .split("\\s+"))
                .collect(Collectors.toList())
                .stream().dropWhile(s -> !"#0000".equals(s))
                .takeWhile(s -> !"#FFFF".equals(s))
                .skip(1L)
                .collect(Collectors.joining(" ")));
    }
}