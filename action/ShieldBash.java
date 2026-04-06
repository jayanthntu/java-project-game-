<<<<<<< HEAD
public class ShieldBash implements Action {
    @Override
    public void execute(Combatant attacker, Combatant target) {
        int dmg = Math.max(0, attacker.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        target.addStatusEffect(new StunEffect(2));
        attacker.setSpecialSkillCooldown(3);
=======
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
>>>>>>> baaf8ad9d1f7610920b05143771d62385c34d569
    }
}