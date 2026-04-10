package action;

import combatant.Combatant;

import java.util.List;

public class BasicAttack implements Action {
    @Override
    public void execute(Combatant user, List<Combatant> targets) {
        Combatant target = targets.getFirst();

        if (!target.isInvulnerable()) {
            int dmg = Math.max(0, user.getAttack() - target.getDefense());
            System.out.println(user.getName() + " attacks " + target.getName()
                    + "! Damage: " + user.getAttack() + " - "
                    + target.getDefense() + " = " + dmg);
            target.takeDamage(dmg);
            System.out.println(target.getName() + " HP: "
                    + target.getHP() + "/" + target.getMaxHP());
        }
        else {
            System.out.println(user.getName() + " attacks " + target.getName()
                    + "! Damage: 0 (Smoke bomb active)");
        }
    }
}