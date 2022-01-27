package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DevSkillLink {
    private int devId;
    private int skillId;
}
