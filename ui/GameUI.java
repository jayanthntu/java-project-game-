package ui;

import action.*;
import combatant.*;
import item.*;
import level.Difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameUI {
    private final Scanner scanner = new Scanner(System.in);

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
        System.out.print("4. Special Skill");
        if (skillReady) {
            System.out.println(" (Ready!)");
        } else {
            System.out.println(" (Cooldown: " + player.getSpecialSkillsCooldown() + " turns)");
        }

        int choice = getPlayerInput(hasItems ? 4 : 3);

        if (hasItems) {
            return switch (choice) {
                case 1 -> {
                    System.out.println("Basic Attack selected!");
                    yield new BasicAttack();
                }
                case 2 -> {
                    System.out.println("Defend selected!");
                    yield new Defend();
                }
                case 3 -> {
                    Item item = selectItem(player);
                    if (item != null) {
                        yield new ItemAction(item);
                    } else {
                        System.out.println("Item selection failed. Using Basic Attack instead.");
                        yield new BasicAttack();
                    }
                }
                case 4 -> {
                    if (skillReady) {
                        System.out.println(player.getSpecialSkill().getName()+ " activated!");
                        yield player.getSpecialSkill();
                    } else {
                        System.out.println("Special skill is on cooldown (" + player.getSpecialSkillsCooldown() + " turns remaining). Using Basic Attack instead.");
                        yield fallback(player, aliveEnemies);
                    }
                }
                default -> {
                    System.out.println("Invalid input, please enter input within valid range.");
                    yield new BasicAttack();
                }
            };
        } else {
            return switch (choice) {
                case 1 -> {
                    System.out.println("Basic Attack selected!");
                    yield new BasicAttack();
                }
                case 2 -> {
                    System.out.println("Defend selected!");
                    yield new Defend();
                }
                case 3 -> {
                    if (skillReady) {
                        if (player instanceof Wizard) {
                            System.out.println("Arcane Blast activated!");
                            yield new ArcaneBlast();
                        } else {
                            System.out.println("Shield Bash activated!");
                            yield new ShieldBash();
                        }
                    } else {
                        System.out.println("Special skill is on cooldown (" + player.getSpecialSkillsCooldown() + " turns remaining). Using Basic Attack instead.");
                        yield fallback(player, aliveEnemies);
                    }
                }
                default -> {
                    System.out.println("Invalid input, please enter input within valid range.");
                    yield new BasicAttack();
                }
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
            String userInput = scanner.nextLine().trim();

            try {
                input = Integer.parseInt(userInput);
                if (input < 1 || input > max) {
                    System.out.println("Invalid input, please enter input within valid range.");
                    input = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter input within valid range.");
            }
        }
        return input;
    }

    private Item selectItem(Player player) {
        List<Item> available = player.getItems().stream()
                .filter(i -> !i.isUsed()).toList();

        if (available.isEmpty()) {
            System.out.println("No unused items available.");
            return null;
        }

        System.out.println("Select item to use:");
        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i).getClass().getSimpleName());
        }

        int choice = getPlayerInput(available.size());

        try {
            Item selected = available.get(choice - 1);
            System.out.println("Using " + selected.getClass().getSimpleName() + "!");
            return selected;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid item selection. Using first available item.");
            return available.getFirst();
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public Player selectPlayer() 
    {
        List<Player> availablePlayers = List.of( new Warrior(), new Wizard() );
        PrintDescribables.printDescribables("SELECT YOUR CHARACTER", availablePlayers, '|');

        int choice = getPlayerInput(2);

        return availablePlayers.get(choice-1);
    }

public void selectItems(Player player) 
{
    List<Item> availableItems = List.of( new Potion(), new PowerStone(), new SmokeBomb());

    System.out.println("SELECT 2 ITEMS (duplicates allowed)");
    PrintDescribables.printDescribables("Item List", availableItems);

    List<Item> chosen = new ArrayList<>();

    for (int i = 1; i <= 2; i++) 
    {
        System.out.println("\nSelect item " + i + ":");
        int choice = getPlayerInput(availableItems.size());

        Item selectedItem = availableItems.get(choice - 1).copy();

        System.out.println(selectedItem.getName() + " selected!");
        chosen.add(selectedItem);
    }

    System.out.println("\nItems confirmed! Ready to battle!");
    player.setItems(chosen);
}

    public Difficulty selectDifficulty() {
        System.out.println("\n=== SELECT DIFFICULTY ===");
        System.out.println("1. Easy   - 3 Goblins");
        System.out.println("2. Medium - 1 Goblin + 1 Wolf | Backup: 2 Wolves");
        System.out.println("3. Hard   - 2 Goblins         | Backup: 1 Goblin + 2 Wolves");

        int choice = getPlayerInput(3);

        Difficulty selected = switch (choice) {
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.HARD;
            default -> {
                System.out.println("Invalid input, please enter input within valid range.");
                yield Difficulty.EASY;
            }
        };

        return selected;
    }
}
