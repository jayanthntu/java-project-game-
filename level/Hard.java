package level;

import combatant.*;
import java.util.List;

public class Hard implements Difficulty {
    private static final String NAME = "Hard";
    private static final String DESCRIPTION = "2 Goblins              | Backup: 1 Goblin + 2 Wolves";
    private final List<Enemy> initialSpawn = List.of(new Goblin("Goblin A"), new Goblin("Goblin B"));
    private final List<Enemy> backupSpawn = List.of(new Goblin("Goblin C"), new Wolf("Wolf A"), new Wolf("Wolf B"));

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
        return new Hard();
    }
}