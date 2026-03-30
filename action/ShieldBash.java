package action;

import combatant.Combatant;
import effect.StunEffect;
import java.util.List;

public class ShieldBash implements Action {
    @Override
    public void execute(Combatant attacker, List<Combatant> targets) {
        Combatant target = targets.getFirst();
        int dmg = Math.max(0, attacker.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        target.addStatusEffect(new StunEffect(2));
        attacker.setSpecialSkillsCooldown(3);
    }
}