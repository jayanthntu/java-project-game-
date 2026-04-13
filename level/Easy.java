package level;

import combatant.*;
import java.util.List;

public class Easy implements Difficulty {
    private static final String NAME = "Easy";
    private static final String DESCRIPTION = "3 Goblins";
    private final List<Enemy> initialSpawn = List.of(new Goblin("Goblin A"), new Goblin("Goblin B"), new Goblin("Goblin C"));
    private final List<Enemy> backupSpawn = List.of();

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
        return new Easy();
    }
}