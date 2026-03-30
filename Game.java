import combatant.Player;
import engine.BattleEngine;
import level.Difficulty;
import level.Level;
import level.LevelFactory;
import ui.GameUI;

public class Game {
    private Level currentLevel;
    private GameUI ui;
    private BattleEngine engine;
    private Player player;

    public Game() {
        this.ui = new GameUI();
    }

    public void start() {
        ui.displayMessage("=== TURN-BASED COMBAT ARENA ===");
        player = ui.selectPlayer();
        ui.selectItems(player);
        Difficulty difficulty = ui.selectDifficulty();
        currentLevel = LevelFactory.create(difficulty);
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
                engine = new BattleEngine(player, currentLevel, ui);
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
