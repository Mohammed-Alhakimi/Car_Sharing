package carsharing;

import lombok.*;

@AllArgsConstructor
public class Car {
    @Getter
    private final int id;
    @Getter @Setter
    private String name;
    @Getter
    private final int companyId;
}