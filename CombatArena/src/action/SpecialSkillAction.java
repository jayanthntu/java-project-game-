package action;

import combatant.Combatant;
import combatant.Player;
import java.util.List;

public class SpecialSkillAction implements Action {

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        if (actor instanceof Player) {
            Player player = (Player) actor;

            if (player.getSpecialCooldown() > 0) {
                System.out.println("Special skill on cooldown! " + player.getSpecialCooldown() + " turns remaining.");
                return;
            }

            player.useSpecialSkill(targets);
            player.setSpecialCooldown(3); // cooldown starts after use
        }
    }
}