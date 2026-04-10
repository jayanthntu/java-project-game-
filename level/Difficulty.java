package level;

import ui.Describable;
import combatant.*;
import java.util.List;

public interface Difficulty extends Describable {
    public enum TYPE {
        EASY, MEDIUM, HARD, CUSTOM;
    }

    List<Enemy> getInitialSpawn();
    List<Enemy> getBackupSpawn();
}
