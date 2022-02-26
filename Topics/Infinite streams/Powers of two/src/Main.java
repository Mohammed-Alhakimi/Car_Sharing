import java.util.Scanner;
import java.util.stream.*;

class StreamUtils {

    public static Stream<Integer> generateStreamWithPowersOfTwo(int n) {
        return Stream.iterate(0, i -> i + 1).map(i -> (int) Math.pow(2, i)).limit(n);
    }
}