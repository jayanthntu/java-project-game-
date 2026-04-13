import combatant.*;
import engine.BattleEngine;
import level.*;
import ui.*;

public class Game {
    private Level currentLevel;
    private BattleEngine engine;
    private Difficulty difficultyBackup;
    private Player playerBackup;

    public void start() {
        GameUI.displayMessage("=== TURN-BASED COMBAT ARENA ===");

        playerBackup = PlayerSelector.selectPlayer();
        Player player = playerBackup.copy();

        GameUI.showSelectedPlayer(player);
        ItemSelector.selectItems(player);

        difficultyBackup = DifficultySelector.selectDifficulty();
        Difficulty difficulty = difficultyBackup.copy();

        GameUI.showDifficulty(difficulty);
        currentLevel = LevelFactory.create(difficulty);

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
                Player player = playerBackup.copy();
                GameUI.showSelectedPlayer(player);
                ItemSelector.selectItems(player);

                Difficulty difficulty = difficultyBackup.copy();
                GameUI.showDifficulty(difficulty);
                currentLevel = LevelFactory.create(difficulty);

                engine = new BattleEngine(player, currentLevel);
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
