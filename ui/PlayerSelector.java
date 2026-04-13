package ui;

import combatant.*;
import java.util.List;

public class PlayerSelector {
    public static Player selectPlayer() 
    {
        List<Player> availablePlayers = List.of(new Warrior(), new Wizard(), new CustomPlayer() );
        PrintDescribables.printDescribables("SELECT YOUR CHARACTER", availablePlayers, '|');

        System.out.println();
        int choice = InputHandler.getPlayerInput(3);

        Player selectedPlayer = availablePlayers.get(choice - 1);

        if (selectedPlayer instanceof CustomPlayer)
            selectedPlayer = PlayerFactory.createCustomPlayer();

        return selectedPlayer;
    }
}