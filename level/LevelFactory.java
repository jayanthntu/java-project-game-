package level;

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

        Custom difficulty = new Custom();
        difficulty.setInitialSpawn(initGoblins, initWolves);
        difficulty.setBackupSpawn(backupGoblins, backupWolves);

        return new Level(Difficulty.TYPE.CUSTOM, difficulty.getInitialSpawn(), difficulty.getBackupSpawn());
    }
}
