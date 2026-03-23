package app.cli;

import app.domain.DifficultyLevel;
import app.domain.EnemyInstance;
import app.domain.EnemyType;
import app.domain.GameConfig;
import app.domain.ItemType;
import app.domain.PlayerClass;
import app.domain.SpawnEntry;
import app.domain.StatBlock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ConsoleUI {
    private static final String APP_NAME = "Turn-Based Combat Arena";
    private static final String APP_VERSION = "0.1.0";

    private final ConsoleIO io;

    public ConsoleUI(ConsoleIO io) {
        this.io = io;
    }

    public void run() throws IOException {
        printBanner();
        while (true) {
            GameConfig config = setupNewGame();
            runBattleStub(config);

            int choice = promptMenu("Game Complete", List.of(
                    "Replay with same settings",
                    "Start a new game",
                    "Exit"
            ));

            if (choice == 1) {
                runBattleStub(config);
            } else if (choice == 2) {
                continue;
            } else {
                io.println("Goodbye.");
                return;
            }
        }
    }

    private GameConfig setupNewGame() throws IOException {
        io.println("\nLoading Screen - Initiation");
        io.println("Players");
        for (PlayerClass playerClass : PlayerClass.values()) {
            io.println("- " + playerClass.displayName() + " | " + playerClass.stats());
            io.println("  Special Skill: " + playerClass.specialSkillDescription());
        }

        int playerChoice = promptMenu("Choose your player", List.of(
                PlayerClass.WARRIOR.displayName(),
                PlayerClass.WIZARD.displayName()
        ));
        PlayerClass selectedPlayer = playerChoice == 1 ? PlayerClass.WARRIOR : PlayerClass.WIZARD;

        io.println("\nItems");
        for (ItemType itemType : ItemType.values()) {
            io.println("- " + itemType.displayName() + " | " + itemType.description());
        }

        List<ItemType> items = new ArrayList<>();
        items.add(promptItemSelection("Pick item 1"));
        items.add(promptItemSelection("Pick item 2"));

        io.println("\nEnemies");
        for (EnemyType enemyType : EnemyType.values()) {
            io.println("- " + enemyType.displayName() + " | " + enemyType.stats());
        }

        io.println("\nDifficulty Levels");
        for (DifficultyLevel level : DifficultyLevel.values()) {
            io.println(level.levelNumber() + ". " + level.displayName());
            io.println("   Initial Spawn: " + spawnLabel(level.initialSpawn()));
            String backup = level.backupSpawn().isEmpty() ? "None" : spawnLabel(level.backupSpawn());
            io.println("   Backup Spawn: " + backup);
        }

        int levelChoice = promptMenu("Select difficulty", List.of(
                DifficultyLevel.EASY.displayName(),
                DifficultyLevel.MEDIUM.displayName(),
                DifficultyLevel.HARD.displayName()
        ));
        DifficultyLevel difficulty = switch (levelChoice) {
            case 1 -> DifficultyLevel.EASY;
            case 2 -> DifficultyLevel.MEDIUM;
            default -> DifficultyLevel.HARD;
        };

        return new GameConfig(selectedPlayer, items, difficulty);
    }

    private void runBattleStub(GameConfig config) throws IOException {
        BattleState state = new BattleState(config);
        io.println("\nStarting battle (CLI UI stub). Actions are recorded, but no combat logic runs yet.\n");

        while (true) {
            printBattleStatus(state);

            int action = promptMenu("Choose action", List.of(
                    "Basic Attack",
                    "Defend",
                    "Item",
                    "Special Skill",
                    "End Demo Battle"
            ));

            if (action == 5) {
                break;
            }

            switch (action) {
                case 1 -> handleBasicAttack(state);
                case 2 -> io.println("Defend selected. Defense +10 for current and next round (stub).\n");
                case 3 -> handleItemUse(state);
                case 4 -> io.println(config.playerClass().displayName() + " uses Special Skill (stub).\n");
                default -> io.println("Unknown action.\n");
            }

            io.println("Enemy turns execute BasicAttack (stub).\n");
            state.round++;
        }

        printVictoryScreen(state);
    }

    private void handleBasicAttack(BattleState state) throws IOException {
        if (state.enemies.isEmpty()) {
            io.println("No enemies available.\n");
            return;
        }

        List<String> labels = new ArrayList<>();
        for (EnemyInstance enemy : state.enemies) {
            labels.add(enemy.label() + " (HP: " + enemy.currentHp() + ")");
        }
        int targetChoice = promptMenu("Choose a target", labels);
        EnemyInstance target = state.enemies.get(targetChoice - 1);
        io.println("Basic Attack -> " + target.label() + " (stub).\n");
    }

    private void handleItemUse(BattleState state) throws IOException {
        List<ItemType> available = state.availableItems();
        if (available.isEmpty()) {
            io.println("No items remaining.\n");
            return;
        }

        List<String> options = new ArrayList<>();
        for (ItemType item : available) {
            options.add(item.displayName() + " (x" + state.itemCounts.get(item) + ")");
        }
        int choice = promptMenu("Choose an item", options);
        ItemType chosen = available.get(choice - 1);
        state.consumeItem(chosen);
        io.println(chosen.displayName() + " used (stub).\n");
    }

    private void printBattleStatus(BattleState state) {
        io.println("============================");
        io.println("Round " + state.round);
        io.println("Player: " + state.playerClass.displayName() + " | " + state.playerStats);
        io.println("Special Skill Cooldown: " + state.specialSkillCooldown + " rounds (stub)");
        io.println("Items: " + state.itemsSummary());
        io.println("Enemies:");
        for (EnemyInstance enemy : state.enemies) {
            io.println("- " + enemy.label() + " | " + enemy.type().displayName() + " | HP: " + enemy.currentHp());
        }
        io.println("============================");
    }

    private void printVictoryScreen(BattleState state) {
        io.println("\nPlayer Victory Screen");
        io.println("Congratulations, you have defeated all your enemies. (stub)");
        io.println("Statistics: Remaining HP: " + state.playerStats.maxHp() + " | Total Rounds: " + (state.round - 1));
    }

    private void printBanner() {
        io.println("============================");
        io.println(APP_NAME + " v" + APP_VERSION);
        io.println("============================");
    }

    private String spawnLabel(List<SpawnEntry> spawn) {
        if (spawn.isEmpty()) {
            return "None";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < spawn.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(spawn.get(i).label());
        }
        return builder.toString();
    }

    private int promptMenu(String title, List<String> options) throws IOException {
        io.println("\n" + title);
        for (int i = 0; i < options.size(); i++) {
            io.println((i + 1) + ". " + options.get(i));
        }
        while (true) {
            io.print("Select an option: ");
            String input = io.readLine();
            if (input == null) {
                return options.size();
            }
            try {
                int value = Integer.parseInt(input.trim());
                if (value >= 1 && value <= options.size()) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // fall through
            }
            io.println("Invalid selection. Try again.");
        }
    }

    private ItemType promptItemSelection(String label) throws IOException {
        int choice = promptMenu(label, List.of(
                ItemType.POTION.displayName(),
                ItemType.POWER_STONE.displayName(),
                ItemType.SMOKE_BOMB.displayName()
        ));
        return switch (choice) {
            case 1 -> ItemType.POTION;
            case 2 -> ItemType.POWER_STONE;
            default -> ItemType.SMOKE_BOMB;
        };
    }

    private static class BattleState {
        private final PlayerClass playerClass;
        private final StatBlock playerStats;
        private final List<EnemyInstance> enemies;
        private final Map<ItemType, Integer> itemCounts;
        private int round = 1;
        private int specialSkillCooldown = 0;

        private BattleState(GameConfig config) {
            this.playerClass = config.playerClass();
            this.playerStats = config.playerClass().stats();
            this.enemies = buildEnemies(config.difficulty().initialSpawn());
            this.itemCounts = new EnumMap<>(ItemType.class);
            for (ItemType item : config.items()) {
                itemCounts.merge(item, 1, Integer::sum);
            }
        }

        private static List<EnemyInstance> buildEnemies(List<SpawnEntry> spawn) {
            List<EnemyInstance> list = new ArrayList<>();
            for (SpawnEntry entry : spawn) {
                for (int i = 0; i < entry.count(); i++) {
                    String label = entry.type().displayName() + " " + (char) ('A' + i);
                    list.add(new EnemyInstance(entry.type(), label));
                }
            }
            return list;
        }

        private List<ItemType> availableItems() {
            List<ItemType> available = new ArrayList<>();
            for (Map.Entry<ItemType, Integer> entry : itemCounts.entrySet()) {
                if (entry.getValue() > 0) {
                    available.add(entry.getKey());
                }
            }
            return available;
        }

        private void consumeItem(ItemType itemType) {
            itemCounts.computeIfPresent(itemType, (item, count) -> Math.max(0, count - 1));
        }

        private String itemsSummary() {
            if (itemCounts.isEmpty()) {
                return "None";
            }
            StringBuilder builder = new StringBuilder();
            int index = 0;
            for (Map.Entry<ItemType, Integer> entry : itemCounts.entrySet()) {
                if (entry.getValue() <= 0) {
                    continue;
                }
                if (index > 0) {
                    builder.append(", ");
                }
                builder.append(entry.getKey().displayName()).append(" x").append(entry.getValue());
                index++;
            }
            return index == 0 ? "None" : builder.toString();
        }
    }
}
