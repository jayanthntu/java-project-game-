package level;

import combatant.Goblin;
import combatant.Wolf;
import java.util.List;

public class LevelFactory {
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
        };
    }
}
