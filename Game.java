import combatant.CustomPlayer;
import combatant.*;
import engine.BattleEngine;
import level.*;
import ui.*;

import java.util.List;

public class Game {
    private Level currentLevel;
    private Level currentLevelBackup;
    private final GameUI ui;
    private BattleEngine engine;
    private Player player;
    private Player playerBackup;

    public Game() {
        this.ui = new GameUI();
    }

    public void start() {
        ui.displayMessage("=== TURN-BASED COMBAT ARENA ===");

        player = PlayerSelector.selectPlayer();
        playerBackup = (player instanceof Warrior) ? new Warrior(player) : new Wizard(player);

        ui.showSelectedPlayer(player);

        if (player instanceof CustomPlayer) {
            player = PlayerFactory.createCustomPlayer();
            playerBackup = new CustomPlayer(player);
        }

        ItemSelector.selectItems(player);

        Difficulty difficulty = DifficultySelector.selectDifficulty();
        ui.showDifficulty(difficulty);

        if (difficulty instanceof Custom) {
            List<Integer> customLevelSettings = CustomLevelFactory.createCustomLevel();
            currentLevel = LevelFactory.createCustom(customLevelSettings);
            currentLevelBackup = LevelFactory.createCustom(customLevelSettings);
        } else {
            currentLevel = LevelFactory.create(difficulty);
            currentLevelBackup = LevelFactory.create(difficulty);
        }

        engine = new BattleEngine(player, currentLevel, ui);
        runGame();
    }

    private void runGame() {
        while (!engine.isOver()) {
            engine.runRound();
        }
        showResult();
    }

    public void showResult() {
        if (engine.playerWon()) {
            ui.displayMessage("\n*** VICTORY! Congratulations, you have defeated all your enemies! ***");
            ui.displayMessage("Remaining HP: " + engine.getPlayer().getHP()
                    + " | Total Rounds: " + engine.getRoundCount());
        } else {
            ui.displayMessage("\n*** DEFEATED. Don't give up, try again! ***");
            ui.displayMessage("Total Rounds Survived: " + engine.getRoundCount());
        }
        restart();
    }

    public void restart() {
        ui.displayMessage("\n1. Replay with same settings");
        ui.displayMessage("2. New game");
        ui.displayMessage("3. Exit");
        int choice = InputHandler.getPlayerInput(3);
        switch (choice) {
            case 1 -> {
                ItemSelector.selectItems(playerBackup);
                engine = new BattleEngine(playerBackup, currentLevelBackup, ui);
                runGame();
            }
            case 2 -> start();
            case 3 -> {
                ui.displayMessage("Thanks for playing!");
                System.exit(0);
            }
        }
    }
}
