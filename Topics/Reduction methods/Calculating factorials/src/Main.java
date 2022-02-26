import java.util.Scanner;
import java.util.stream.*;

class Main {

    public static void main(String[] args) {
        System.out.println(LongStream.rangeClosed(1,
                        new Scanner(System.in).nextLong())
                .reduce(1, (sum, next) -> sum * next));
    }
}