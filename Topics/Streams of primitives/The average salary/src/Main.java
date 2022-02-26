import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class CalculateAverageSalary {
    public static void main(String[] args) {

        System.out.println(new Scanner(System.in)
                .tokens()
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(-1));
    }
}