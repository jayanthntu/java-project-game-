package ui;

import action.*;
import combatant.Combatant;
import combatant.Player;
import item.Item;
import java.util.List;

public class ActionMenu {
        public static Action showActionMenu(Player player, List<Combatant> aliveEnemies) {
        System.out.println("\nYour turn! Choose an action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");

        boolean hasItems = player.getItems().stream().anyMatch(i -> !i.isUsed());
        if (hasItems) {
            System.out.println("3. Use Item");
            System.out.print("4. ");
        } 
        else {
            System.out.print("3. ");
        }

        boolean skillReady = player.getSpecialSkillCooldown() == 0;


        System.out.print("Special Skill");
        if (skillReady) {
            System.out.println(" (Ready!)");
        } 
        else {
            System.out.println(" (Cooldown: " + player.getSpecialSkillCooldown() + " turns)");
        }

        int choice = InputHandler.getPlayerInput(hasItems ? 4 : 3);

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
                    Item item = ItemSelector.selectItem(player);
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
                        System.out.println("Special skill is on cooldown (" + player.getSpecialSkillCooldown() + " turns remaining). Using Basic Attack instead.");
                        yield fallback(player, aliveEnemies);
                    }
                }
                default -> {
                    System.out.println("Invalid input, please enter input within valid range.");
                    yield fallback(player, aliveEnemies);
                }
            };
        } 
        else {
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
                        SpecialSkill specialSkill = player.getSpecialSkill();
                        System.out.println(specialSkill.getName() + " activated!");
                        yield specialSkill;
                    } else {
                        System.out.println("Special skill is on cooldown (" + player.getSpecialSkillCooldown() + " turns remaining). Using Basic Attack instead.");
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

    private static Action fallback(Player player, List<Combatant> aliveEnemies) {
        System.out.println("Skill not ready yet. Please choose again.");
        return showActionMenu(player, aliveEnemies);
    }
}