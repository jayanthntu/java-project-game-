package level;

import combatant.Goblin;
import combatant.Wolf;
import java.util.List;

public class LevelFactory {
    public static Level create(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> new Level(difficulty,
                    List.of(new Goblin(), new Goblin(), new Goblin()),
                    List.of());
            case MEDIUM -> new Level(difficulty,
                    List.of(new Goblin(), new Wolf()),
                    List.of(new Wolf(), new Wolf()));
            case HARD -> new Level(difficulty,
                    List.of(new Goblin(), new Goblin()),
                    List.of(new Goblin(), new Wolf(), new Wolf()));
        };
    }
}
