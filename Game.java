import combatant.CustomPlayer;
import combatant.*;
import engine.BattleEngine;
import level.*;
import ui.*;

import java.util.List;

public class Game {
    private Level currentLevel;
    private Level currentLevelBackup;
    private BattleEngine engine;
    private Player player;
    private Player playerBackup;

    public void start() {
        GameUI.displayMessage("=== TURN-BASED COMBAT ARENA ===");

        player = PlayerSelector.selectPlayer();
        playerBackup = player.copy();
        GameUI.showSelectedPlayer(player);

        if (player instanceof CustomPlayer) {
            player = PlayerFactory.createCustomPlayer();
            playerBackup = new CustomPlayer(player);
        }

        ItemSelector.selectItems(player);

        Difficulty difficulty = DifficultySelector.selectDifficulty();
        GameUI.showDifficulty(difficulty);

        if (difficulty instanceof Custom) {
            List<Integer> customLevelSettings = CustomLevelFactory.createCustomLevel();
            currentLevel = LevelFactory.createCustom(customLevelSettings);
            currentLevelBackup = LevelFactory.createCustom(customLevelSettings);
        } else {
            currentLevel = LevelFactory.create(difficulty);
            currentLevelBackup = LevelFactory.create(difficulty);
        }

        engine = new BattleEngine(player, currentLevel);
        runGame();
    }

    private void runGame() {
        while (!engine.isOver()) {
            engine.runRound();
        }
        GameUI.showResult(engine);
        restart();
    }

    private void restart() {
        GameUI.restartMenu();
        int choice = InputHandler.getPlayerInput(3);
        
        switch (choice) {
            case 1 -> {
                ItemSelector.selectItems(playerBackup);
                engine = new BattleEngine(playerBackup, currentLevelBackup);
                runGame();
            }
            case 2 -> start();
            case 3 -> {
                GameUI.displayMessage("Thanks for playing!");
                System.exit(0);
            }
        }
    }
}
