import java.util.Scanner;
import java.util.stream.*;

class StreamOfPrimitives {

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();

        System.out.println(LongStream.concat(LongStream.range(-n, 0), LongStream.rangeClosed(1, n)).boxed()
                .map(Object::toString)
                .collect(Collectors.joining(" ")));
    }
}