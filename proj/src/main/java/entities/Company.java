package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Company {
    private int id;
    private String name;
    private String foundationDate;
}
