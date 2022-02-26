package carsharing;

import lombok.*;

@AllArgsConstructor
public class Company {
    @Getter
    private final int id;
    @Getter @Setter
    private String name;
}
