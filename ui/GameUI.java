package ui;

import action.*;
import combatant.Combatant;
import combatant.Player;
import combatant.Warrior;
import combatant.Wizard;
import item.Item;
import item.*;
import level.Difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameUI {
    private Scanner scanner = new Scanner(System.in);

    public void displayBattleStatus(List<Combatant> combatants, int round, Combatant player) {
        System.out.println("\n========== ROUND " + round + " ==========");
        for (Combatant c : combatants) {
            if (!c.isDefeated()) {
                System.out.println(c.getName() + " | HP: " + c.getHP() + "/" + c.getMaxHP()
                        + " | ATK: " + c.getAttack() + " | DEF: " + c.getDefense()
                        + " | SPD: " + c.getSpeed());
            } else {
                System.out.println(c.getName() + " [ELIMINATED]");
            }
        }
        System.out.println("===================================");
        displayItems((Player) player);
        System.out.println("===================================");
    }

    public void displayItems(Player player) {
        System.out.print("Items: ");
        List<String> itemStatus = new ArrayList<>();
        for (Item item : player.getItems()) {
            String name = item.getClass().getSimpleName();
            String status = item.isUsed() ? "Used" : "Unused";
            itemStatus.add(name + ": " + status);
        }
        System.out.println(String.join(" | ", itemStatus));
    }

    public Action showActionMenu(Player player, List<Combatant> aliveEnemies) {
        System.out.println("\nYour turn! Choose an action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");

        boolean hasItems = player.getItems().stream().anyMatch(i -> !i.isUsed());
        if (hasItems) System.out.println("3. Use Item");

        boolean skillReady = player.getSpecialSkillsCooldown() == 0;
        System.out.print(hasItems ? 4 : 3);
        System.out.println(". Special Skill (Cooldown: " + player.getSpecialSkillsCooldown() + ")");

        int choice = getPlayerInput(hasItems ? 4 : 3);

        if (hasItems) {
            return switch (choice) {
                case 1 -> new BasicAttack();
                case 2 -> new Defend();
                case 3 -> {
                    Item item = selectItem(player);
                    yield new ItemAction(item);
                }
                case 4 -> {
                    if (skillReady) {
                        yield player.getSpecialSkill();
                    } else {
                        yield fallback(player, aliveEnemies);
                    }
                }
                default -> new BasicAttack();
            };
        } else {
            return switch (choice) {
                case 1 -> new BasicAttack();
                case 2 -> new Defend();
                case 3 -> {
                    if (skillReady) {
                        yield player.getSpecialSkill();
                    } else {
                        yield fallback(player, aliveEnemies);
                    }
                }
                default -> new BasicAttack();
            };
        }
    }

    private Action fallback(Player player, List<Combatant> aliveEnemies) {
        System.out.println("Skill not ready yet. Please choose again.");
        return showActionMenu(player, aliveEnemies);
    }

    public int getPlayerInput(int max) {
        int input = -1;
        while (input < 1 || input > max) {
            System.out.print("Enter choice (1-" + max + "): ");
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        return input;
    }

    private Combatant selectTarget(List<Combatant> targets) {
        System.out.println("Select target:");
        for (int i = 0; i < targets.size(); i++) {
            System.out.println((i + 1) + ". " + targets.get(i).getName()
                    + " (HP: " + targets.get(i).getHP() + ")");
        }
        int choice = getPlayerInput(targets.size());
        return targets.get(choice - 1);
    }

    private Item selectItem(Player player) {
        List<Item> available = player.getItems().stream()
                .filter(i -> !i.isUsed()).toList();
        System.out.println("Select item:");
        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i).getClass().getSimpleName());
        }
        int choice = getPlayerInput(available.size());
        return available.get(choice - 1);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public Player selectPlayer() {
        System.out.println("\n=== SELECT YOUR CHARACTER ===");
        System.out.println("1. Warrior | HP: 260  ATK: 40  DEF: 20  SPD: 30 | Skill: Shield Bash");
        System.out.println("2. Wizard  | HP: 200  ATK: 50  DEF: 10  SPD: 20 | Skill: Arcane Blast");
        int choice = getPlayerInput(2);
        return choice == 1 ? new Warrior() : new Wizard();
    }

    public void selectItems(Player player) {
        System.out.println("\n=== SELECT 2 ITEMS (duplicates allowed) ===");
        System.out.println("1. Potion     - Heal 100 HP");
        System.out.println("2. Power Stone - Free extra use of special skill");
        System.out.println("3. Smoke Bomb  - Enemy attacks deal 0 damage for 2 turns");

        List<Item> chosen = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            System.out.println("Select item " + i + ":");
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
        System.out.println("\n=== SELECT DIFFICULTY ===");
        System.out.println("1. Easy   - 3 Goblins");
        System.out.println("2. Medium - 1 Goblin + 1 Wolf | Backup: 2 Wolves");
        System.out.println("3. Hard   - 2 Goblins          | Backup: 1 Goblin + 2 Wolves");
        int choice = getPlayerInput(3);
        return switch (choice) {
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.HARD;
            default -> Difficulty.EASY;
        };
    }
}