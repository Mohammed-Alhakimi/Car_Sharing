package carsharing;

import lombok.*;

@AllArgsConstructor
public class Customer {
    @Getter
    private final int id;
    @Getter @Setter
    private String name;
    @Getter
    private int rentedCarId;
}
