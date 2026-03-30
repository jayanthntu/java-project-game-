package level;

import combatant.Enemy;
import combatant.Goblin;
import combatant.Wolf;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Difficulty difficulty;
    private List<Enemy> initialSpawn;
    private List<Enemy> backupSpawn;
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

    public Difficulty getDifficulty() { return difficulty; }

    public List<Enemy> getInitialSpawn() { return initialSpawn; }

}
