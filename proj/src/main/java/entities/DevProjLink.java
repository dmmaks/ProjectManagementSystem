package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DevProjLink {
    private int devId;
    private int projId;
}
