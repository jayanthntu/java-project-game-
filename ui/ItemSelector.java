package ui;

import item.*;
import combatant.Player;
import java.util.ArrayList;
import java.util.List;

public class ItemSelector {
    public static Item selectItem(Player player) {
        List<Item> available = player.getItems().stream().filter(i -> !i.isUsed()).toList();

        if (available.isEmpty()) {
            System.out.println("No unused items available.");
            return null;
        }

        System.out.println("Select item to use:");
        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i).getClass().getSimpleName());
        }

        int choice = InputHandler.getPlayerInput(available.size());

        try {
            Item selected = available.get(choice - 1);
            System.out.println("Using " + selected.getClass().getSimpleName() + "!");
            return selected;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid item selection. Using first available item.");
            return available.getFirst();
        }
    }

    public static void selectItems(Player player)
    {
        List<Item> availableItems = List.of(new Potion(), new PowerStone(), new SmokeBomb());

        System.out.println("SELECT 2 ITEMS (duplicates allowed)");
        PrintDescribables.printDescribables("Item List", availableItems);

        List<Item> chosen = new ArrayList<>();

        for (int i = 1; i <= 2; i++)
        {
            System.out.print("\nSelect item " + i + ": ");
            int choice = InputHandler.getPlayerInput(availableItems.size());

            Item selectedItem = availableItems.get(choice - 1).copy();

            System.out.println(selectedItem.getName() + " selected!");
            chosen.add(selectedItem);
        }

        System.out.println("\nItems confirmed! Ready to battle!");
        player.setItems(chosen);
    }
}