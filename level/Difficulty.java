package level;

import ui.Describable;
import combatant.*;
import java.util.List;

public interface Difficulty extends Describable {
    List<Enemy> getInitialSpawn();
    List<Enemy> getBackupSpawn();
}
