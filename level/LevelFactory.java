package level;

import combatant.*;

import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    public static Level create(Difficulty.TYPE difficultyType) {
        Difficulty difficulty =  switch (difficultyType) {
            case EASY -> new Easy();
            case MEDIUM -> new Medium();
            case HARD -> new Hard();
            default -> new Easy();
        };
        return new Level(difficultyType, difficulty.getInitialSpawn(), difficulty.getBackupSpawn());
    }
    public static Level createCustom(List<Integer> customSettings) {
        int initGoblins  = customSettings.get(0);
        int initWolves   = customSettings.get(1);
        int backupGoblins = customSettings.get(2);
        int backupWolves  = customSettings.get(3);

        List<Enemy> initialSpawns = new ArrayList<>();
        List<Enemy> backupSpawns  = new ArrayList<>();

        for (int i = 0; i < initGoblins; i++) initialSpawns.add(new Goblin("Goblin " + (char)('A' + i)));
        for (int i = 0; i < initWolves; i++) initialSpawns.add(new Wolf("Wolf "   + (char)('A' + i)));
        for (int i = 0; i < backupGoblins; i++) backupSpawns.add(new Goblin("Goblin " + (char)('A' + i)));
        for (int i = 0; i < backupWolves; i++) backupSpawns.add(new Wolf("Wolf "    + (char)('A' + i)));

        return new Level(Difficulty.TYPE.CUSTOM, initialSpawns, backupSpawns);
    }
}
