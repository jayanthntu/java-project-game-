package level;

import combatant.*;
import java.util.List;

public class Custom implements Difficulty {
    private static final String NAME = "Custom";
    private static final String DESCRIPTION = "? Goblins + ? Wolves | Backup: ? Goblins + ? Wolves";
    private static final List<Enemy> initialSpawn = List.of(new Goblin("Goblin A"), new Goblin("Goblin B"), new Goblin("Goblin C"));
    private static final List<Enemy> backupSpawn = List.of();

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
}