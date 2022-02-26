import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // (͡°͜ʖ͡°)    (͡°͜ʖ͡°)    (͡°͜ʖ͡°)   (͡°͜ʖ͡°)  (͡°͜ʖ͡°)    (͡°͜ʖ͡°)
        System.out.println(Arrays.stream(new Scanner(System.in)
                        .nextLine()
                        .split("\\s+"))
                .collect(Collectors.joining("", "(", ")")));
        // (͡°͜ʖ͡°)    (͡°͜ʖ͡°)    (͡°͜ʖ͡°)   (͡°͜ʖ͡°)  (͡°͜ʖ͡°)    (͡°͜ʖ͡°)
    }
}