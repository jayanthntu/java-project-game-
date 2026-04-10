package ui;

import java.util.ArrayList;
import java.util.List;

public class CustomLevelFactory {
    private static final int MAX_SPAWN = 5;

    public static List<Integer> createCustomLevel() {
        List<Integer> customSettings = new ArrayList<>();

        System.out.println();
        System.out.println("=== INITIAL SPAWN ===");
        System.out.println("Choose the number of goblins (Maximum: "+ MAX_SPAWN + ")");
        customSettings.add(InputHandler.getPlayerInput(5));
        System.out.println("Choose the number of wolves (Maximum: "+ MAX_SPAWN + ")");
        customSettings.add(InputHandler.getPlayerInput(5));
        System.out.println();
        System.out.println("=== BACKUP SPAWN ===");
        System.out.println("Choose the number of goblins (Maximum: "+ MAX_SPAWN + ")");
        customSettings.add(InputHandler.getPlayerInput(5));
        System.out.println("Choose the number of wolves (Maximum: "+ MAX_SPAWN + ")");
        customSettings.add(InputHandler.getPlayerInput(5));

        return customSettings;
    }
}