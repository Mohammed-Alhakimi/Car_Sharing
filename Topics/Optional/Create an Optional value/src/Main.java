import java.util.*;

class Main {
    public static void main(String[] args) {
        new InputStringReader()
                .getValue()
                .ifPresentOrElse(s -> System.out.println("Value is present: " + s),
                        () -> System.out.println("Value is empty"));
    }
}

class InputStringReader {
    public Optional<String> getValue() {
        String value = new Scanner(System.in).nextLine();
        return "empty".equalsIgnoreCase(value) ? Optional.empty() : Optional.ofNullable(value);
    }
}