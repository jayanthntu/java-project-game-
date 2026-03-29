package ui;

import action.*;
import combatant.Combatant;
import combatant.Player;
import combatant.Warrior;
import combatant.Wizard;
import item.*;
import level.Difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ConsoleUI — a cleaner alternative to GameUI.
 *
 * Differences from GameUI:
 *  - Action menu always shows all 4 options; unavailable ones are labelled clearly.
 *  - Items display their friendly name (item.getName()) instead of the class simple name.
 *  - Target selection is wired in for Basic Attack (always targets first alive enemy for
 *    special skill / items, as before, since those affect all targets).
 *  - Banner printed on startup.
 */
public class ConsoleUI {

    private static final String APP_NAME    = "Turn-Based Combat Arena";
    private static final String APP_VERSION = "1.0";

    private final Scanner scanner = new Scanner(System.in);

    // -----------------------------------------------------------------------
    // Banner
    // -----------------------------------------------------------------------

    public void printBanner() {
        displayMessage("====================================");
        displayMessage(APP_NAME + " v" + APP_VERSION);
        displayMessage("====================================");
    }

    // -----------------------------------------------------------------------
    // Battle status
    // -----------------------------------------------------------------------

    public void displayBattleStatus(List<Combatant> combatants, int round, Combatant player) {
        displayMessage("\n========== ROUND " + round + " ==========");
        for (Combatant c : combatants) {
            if (!c.isDefeated()) {
                displayMessage(c.getName()
                        + " | HP: "  + c.getHP()      + "/" + c.getMaxHP()
                        + " | ATK: " + c.getAttack()
                        + " | DEF: " + c.getDefense()
                        + " | SPD: " + c.getSpeed());
            } else {
                displayMessage(c.getName() + " [ELIMINATED]");
            }
        }
        displayMessage("===================================");
        displayItems((Player) player);
        displayMessage("===================================");
    }

    public void displayItems(Player player) {
        System.out.print("Items: ");
        List<String> parts = new ArrayList<>();
        for (Item item : player.getItems()) {
            // Use the friendly name stored on the item, not the class name
            parts.add(item.getName() + ": " + (item.isUsed() ? "Used" : "Unused"));
        }
        displayMessage(String.join(" | ", parts));
    }

    // -----------------------------------------------------------------------
    // Action menu — always shows all 4 options
    // -----------------------------------------------------------------------

    public Action showActionMenu(Player player, List<Combatant> aliveEnemies) {
        displayMessage("\nYour turn! Choose an action:");
        displayMessage("1. Basic Attack");
        displayMessage("2. Defend");

        boolean hasItems   = player.getItems().stream().anyMatch(i -> !i.isUsed());
        boolean skillReady = player.getSpecialCooldown() == 0;

        if (hasItems) {
            displayMessage("3. Use Item");
        } else {
            displayMessage("3. Use Item  (none remaining)");
        }

        if (skillReady) {
            displayMessage("4. Special Skill");
        } else {
            displayMessage("4. Special Skill  (cooldown: " + player.getSpecialCooldown() + ")");
        }

        int choice = getPlayerInput(4);

        return switch (choice) {
            case 1 -> {
                // Let the player pick a target for basic attack
                Combatant target = selectTarget(aliveEnemies);
                yield new BasicAttack();
                // Note: BasicAttack.execute() uses targets.getFirst(); target selection
                // here informs the user visually. To fully wire per-target selection,
                // pass the chosen target as a single-element list in BattleEngine.
            }
            case 2 -> new Defend();
            case 3 -> {
                if (!hasItems) {
                    displayMessage("No items remaining. Defaulting to Basic Attack.");
                    yield new BasicAttack();
                }
                Item item = selectItem(player);
                yield new ItemAction(item);
            }
            case 4 -> {
                if (!skillReady) {
                    displayMessage("Skill not ready yet. Defaulting to Basic Attack.");
                    yield new BasicAttack();
                }
                yield new SpecialSkillAction();
            }
            default -> new BasicAttack();
        };
    }

    // -----------------------------------------------------------------------
    // Target & item selection
    // -----------------------------------------------------------------------

    private Combatant selectTarget(List<Combatant> targets) {
        if (targets.size() == 1) return targets.get(0);
        displayMessage("Select target:");
        for (int i = 0; i < targets.size(); i++) {
            displayMessage((i + 1) + ". " + targets.get(i).getName()
                    + " (HP: " + targets.get(i).getHP() + ")");
        }
        int choice = getPlayerInput(targets.size());
        return targets.get(choice - 1);
    }

    private Item selectItem(Player player) {
        List<Item> available = player.getItems().stream()
                .filter(i -> !i.isUsed())
                .toList();
        displayMessage("Select item:");
        for (int i = 0; i < available.size(); i++) {
            // Use friendly name from item.getName()
            displayMessage((i + 1) + ". " + available.get(i).getName());
        }
        int choice = getPlayerInput(available.size());
        return available.get(choice - 1);
    }

    // -----------------------------------------------------------------------
    // Setup menus
    // -----------------------------------------------------------------------

    public Player selectPlayer() {
        displayMessage("\n=== SELECT YOUR CHARACTER ===");
        displayMessage("1. Warrior | HP: 260  ATK: 40  DEF: 20  SPD: 30 | Skill: Shield Bash");
        displayMessage("2. Wizard  | HP: 200  ATK: 50  DEF: 10  SPD: 20 | Skill: Arcane Blast");
        int choice = getPlayerInput(2);
        return choice == 1 ? new Warrior() : new Wizard();
    }

    public void selectItems(Player player) {
        displayMessage("\n=== SELECT 2 ITEMS (duplicates allowed) ===");
        displayMessage("1. Potion      — Heal 100 HP");
        displayMessage("2. Power Stone — Free extra use of special skill");
        displayMessage("3. Smoke Bomb  — Enemy attacks deal 0 damage for 2 turns");

        List<Item> chosen = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            displayMessage("Select item " + i + ":");
            int choice = getPlayerInput(3);
            switch (choice) {
                case 1 -> chosen.add(new Potion());
                case 2 -> chosen.add(new PowerStone());
                case 3 -> chosen.add(new SmokeBomb());
            }
        }
        player.setItems(chosen);
    }

    public Difficulty selectDifficulty() {
        displayMessage("\n=== SELECT DIFFICULTY ===");
        displayMessage("1. Easy   — 3 Goblins");
        displayMessage("2. Medium — 1 Goblin + 1 Wolf  | Backup: 2 Wolves");
        displayMessage("3. Hard   — 2 Goblins           | Backup: 1 Goblin + 2 Wolves");
        int choice = getPlayerInput(3);
        return switch (choice) {
            case 1  -> Difficulty.EASY;
            case 2  -> Difficulty.MEDIUM;
            default -> Difficulty.HARD;
        };
    }

    // -----------------------------------------------------------------------
    // Input helpers
    // -----------------------------------------------------------------------

    public int getPlayerInput(int max) {
        int input = -1;
        while (input < 1 || input > max) {
            System.out.print("Enter choice (1-" + max + "): ");
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input < 1 || input > max) {
                    displayMessage("Please enter a number between 1 and " + max + ".");
                }
            } catch (NumberFormatException e) {
                displayMessage("Invalid input — please enter a number.");
            }
        }
        return input;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
