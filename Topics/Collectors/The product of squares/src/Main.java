import java.util.*;

class CollectorProduct {

    public static void main(String[] args) {

        System.out.println(Arrays.stream(new Scanner(System.in)
                .nextLine()
                .split(" "))
                .map(Integer::parseInt)
                .reduce(1, (sum, next) -> sum * (next * next)));
    }
}