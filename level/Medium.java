package level;

import combatant.*;
import java.util.List;

public class Medium implements Difficulty {
    private static final String NAME = "Medium";
    private static final String DESCRIPTION = "1 Goblin + 1 Wolf    | Backup: 2 Wolves";
    private final List<Enemy> initialSpawn = List.of(new Goblin("Goblin A"), new Wolf("Wolf A"));
    private final List<Enemy> backupSpawn = List.of(new Wolf("Wolf B"), new Wolf("Wolf C"));

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public List<Enemy> getInitialSpawn() {
        return initialSpawn;
    }

    @Override
    public List<Enemy> getBackupSpawn() {
        return backupSpawn;
    }

    @Override
    public Difficulty copy() {
        return new Medium();
    }
}