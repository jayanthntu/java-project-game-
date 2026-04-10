package ui;

import java.util.Scanner;
import java.util.List;
import combatant.Player;
import combatant.CustomPlayer;
import action.*;

public class PlayerFactory {
    public static final int MAX_STAT = 999;

    public static Player createCustomPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== CUSTOM PLAYER BUILDER ===");

        // Select custom stats
        System.out.print("NAME: ");
        String name = scanner.nextLine().trim();
        System.out.print("HP: ");
        int hp = InputHandler.getPlayerInput(MAX_STAT);
        System.out.print("ATK: ");
        int atk = InputHandler.getPlayerInput(MAX_STAT);
        System.out.print("DEF: ");
        int def = InputHandler.getPlayerInput(MAX_STAT);
        System.out.print("SPD: ");
        int spd = InputHandler.getPlayerInput(MAX_STAT);

        // Select specialskill
        List<SpecialSkill> availableSpecialSkills = List.of(new ShieldBash(), new ArcaneBlast());

        PrintDescribables.printDescribables("Choose Special Skill", availableSpecialSkills);
        System.out.println();
        int choice = InputHandler.getPlayerInput(availableSpecialSkills.size());

        SpecialSkill selectedSpecialSkill = availableSpecialSkills.get(choice - 1);

        System.out.println(selectedSpecialSkill.getName() + " selected!");
        System.out.println("Custom Player Created!\n");

        return new CustomPlayer(name, hp, atk, def, spd, selectedSpecialSkill);
    }
}