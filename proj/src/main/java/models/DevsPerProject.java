package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DevsPerProject {
    private String startDate;
    private String projectName;
    private int devCount;
}
