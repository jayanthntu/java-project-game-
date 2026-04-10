package ui;

import java.util.List;
import level.*;

public class DifficultySelector {
    public static Difficulty selectDifficulty() {
        List<Difficulty> difficulties = List.of(new Easy(), new Medium(), new Hard(), new Custom());
        System.out.println("\nSELECT DIFFICULTY");
        PrintDescribables.printDescribables("Difficulties", difficulties);

        System.out.println();
        int choice = InputHandler.getPlayerInput(difficulties.size());
        return difficulties.get(choice - 1);
    }
}