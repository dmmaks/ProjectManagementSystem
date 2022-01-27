package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Skill {
    private int id;
    private String area;
    private String level;
}
