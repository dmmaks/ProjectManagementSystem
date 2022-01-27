package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private int id;
    private String name;
    private String startDate;
    private int customerId;
    private int companyId;
    private int cost;
}
