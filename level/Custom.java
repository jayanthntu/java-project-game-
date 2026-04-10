package level;

import combatant.*;
import java.util.ArrayList;
import java.util.List;

public class Custom implements Difficulty {
    private static final String NAME = "Custom";
    private static final String DESCRIPTION = "? Goblins + ? Wolves | Backup: ? Goblins + ? Wolves";
    private static final List<Enemy> initialSpawn = new ArrayList<>();
    private static final List<Enemy> backupSpawn = new ArrayList<>();

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

    public void setInitialSpawn(int initGoblins, int initWolves) {
        for (int i = 0; i < initGoblins; i++) initialSpawn.add(new Goblin("Goblin " + (char)('A' + i)));
        for (int i = 0; i < initWolves; i++) initialSpawn.add(new Wolf("Wolf "   + (char)('A' + i)));
    }

    public void setBackupSpawn(int backupGoblins, int backupWolves) {
        for (int i = 0; i < backupGoblins; i++) backupSpawn.add(new Goblin("Goblin " + (char)('A' + i)));
        for (int i = 0; i < backupWolves; i++) backupSpawn.add(new Wolf("Wolf "   + (char)('A' + i)));
    }
}