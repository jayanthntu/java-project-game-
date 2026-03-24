package action;

import java.util.List;
import combatant.Combatant;

public class BasicAttack implements Action {

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        Combatant target = targets.getFirst();

        if (!target.isInvulnerable()) {
            int dmg = Math.max(0, actor.getAttack() - target.getDefense());
            System.out.println(actor.getName() + " attacks " + target.getName()
                    + "! Damage: " + actor.getAttack() + " - "
                    + target.getDefense() + " = " + dmg);
            target.takeDamage(dmg);
            System.out.println(target.getName() + " HP: "
                    + target.getHP() + "/" + target.getMaxHP());
        } else {
            System.out.println(actor.getName() + " attacks " + target.getName()
                    + "! Damage: 0 (Smoke bomb active)");
        }
    }
}