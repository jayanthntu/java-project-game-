package level;

import combatant.*;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final Difficulty difficulty;
    private final List<Enemy> initialSpawn;
    private final List<Enemy> backupSpawn;
    private boolean backupSpawned = false;

    public Level(Difficulty difficulty, List<Enemy> initialSpawn, List<Enemy> backupSpawn) {
        this.difficulty = difficulty;
        this.initialSpawn = initialSpawn;
        this.backupSpawn = backupSpawn;
    }

    public List<Enemy> triggerBackupSpawn() {
        if (!backupSpawned && backupSpawn != null && !backupSpawn.isEmpty()) {
            backupSpawned = true;
            return new ArrayList<>(backupSpawn);
        }
        return new ArrayList<>();
    }

    public boolean hasBackupSpawn() {
        return !backupSpawned && backupSpawn != null && !backupSpawn.isEmpty();
    }

    public List<Enemy> getInitialSpawn() { 
        return initialSpawn; 
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
