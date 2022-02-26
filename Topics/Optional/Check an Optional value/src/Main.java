import java.util.*;

class Main {
    public static void main(String[] args) {
        new ValueProvider().getValue().ifPresent(System.out::println);
    }
}

class ValueProvider {
    public Optional<String> getValue() {
        String input = new Scanner(System.in).next();
        return "null".equals(input) ? Optional.empty() : Optional.of(input);
    }
}