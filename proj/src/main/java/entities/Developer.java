package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Developer {
    private int id;
    private String name;
    private String birthDate;
    private String sex;
    private int salary;
}
