import combatant.Player;
import combatant.Warrior;
import combatant.Wizard;
import engine.BattleEngine;
import level.Difficulty;
import level.Level;
import level.LevelFactory;
import ui.GameUI;

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

        player = ui.selectPlayer();
        playerBackup = (player instanceof Warrior) ? new Warrior(player) : new Wizard(player);

        ui.selectItems(player);

        Difficulty difficulty = ui.selectDifficulty();
        ui.showDifficulty(difficulty);

        if (difficulty == Difficulty.CUSTOM) {
            List<Integer> customLevelSettings = ui.createCustomLevel();
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
        int choice = ui.getPlayerInput(3);
        switch (choice) {
            case 1 -> {
                ui.selectItems(playerBackup);
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
