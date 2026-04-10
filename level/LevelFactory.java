package level;

import combatant.*;

import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    private static final List<Difficulty> DIFFICULTIES = List.of(new Easy(), new Medium(), new Hard(), new Custom());

    public static Level create(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> new Level(difficulty,
                    List.of(new Goblin("Goblin A"), new Goblin("Goblin B"), new Goblin("Goblin C")),
                    List.of());
            case MEDIUM -> new Level(difficulty,
                    List.of(new Goblin("Goblin A"), new Wolf("Wolf A")),
                    List.of(new Wolf("Wolf B"), new Wolf("Wolf C")));
            case HARD -> new Level(difficulty,
                    List.of(new Goblin("Goblin A"), new Goblin("Goblin B")),
                    List.of(new Goblin("Goblin C"), new Wolf("Wolf A"), new Wolf("Wolf B")));
            default -> new Level(difficulty,
                    List.of(new Goblin("Goblin A"), new Goblin("Goblin B"), new Goblin("Goblin C")),
                    List.of());
        };
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

        return new Level(Difficulty.CUSTOM, initialSpawns, backupSpawns);
    }
}
