package level;

import java.util.List;

public class LevelFactory {
    public static Level create(Difficulty difficulty) {
        return new Level(difficulty, difficulty.getInitialSpawn(), difficulty.getBackupSpawn());
    }

    public static Level createCustom(List<Integer> customSettings) {
        int initGoblins  = customSettings.get(0);
        int initWolves   = customSettings.get(1);
        int backupGoblins = customSettings.get(2);
        int backupWolves  = customSettings.get(3);

        Custom difficulty = new Custom();
        difficulty.setInitialSpawn(initGoblins, initWolves);
        difficulty.setBackupSpawn(backupGoblins, backupWolves);

        return new Level(difficulty, difficulty.getInitialSpawn(), difficulty.getBackupSpawn());
    }
}
