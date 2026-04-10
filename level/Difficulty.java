package level;

import ui.Describable;

public interface Difficulty extends Describable {
    enum Type {
        EASY, MEDIUM, HARD, CUSTOM;
    }
}
