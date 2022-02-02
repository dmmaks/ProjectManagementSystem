package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Developer {
    private int id;
    private String name;
    private String birthDate;
    private String sex;
    private int salary;
}
