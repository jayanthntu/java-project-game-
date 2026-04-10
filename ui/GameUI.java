package ui;

import combatant.*;
import item.*;
import level.*;
import java.util.ArrayList;
import java.util.List;

public class GameUI {
    public static void displayBattleStatus(List<Combatant> combatants, int round, Combatant player) {
        System.out.println("\n========== ROUND " + round + " ==========");
        for (Combatant c : combatants) {
            if (!c.isDefeated()) {
                System.out.println(c.getName() + " | HP: " + c.getHP() + "/" + c.getMaxHP()
                        + " | ATK: " + c.getAttack() + " | DEF: " + c.getDefense()
                        + " | SPD: " + c.getSpeed());
            } 
            else {
                System.out.println(c.getName() + " [ELIMINATED]");
            }
        }
        System.out.println("===================================");
        displayItems((Player) player);
        System.out.println("===================================");
    }

    public static void displayItems(Player player) {
        System.out.print("Items: ");
        List<String> itemStatus = new ArrayList<>();
        for (Item item : player.getItems()) {
            String name = item.getClass().getSimpleName();
            String status = item.isUsed() ? "Used" : "Unused";
            itemStatus.add(name + ": " + status);
        }
        System.out.println(String.join(" | ", itemStatus));
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void showDifficulty(Difficulty difficulty) {
        System.out.println("Difficulty Level: " + difficulty);
    }

    public static void showSelectedPlayer(Player player) {
        System.out.println("Selected Player: " + player.getClass().getSimpleName());
        System.out.println();
    }
}