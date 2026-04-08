package action;

import combatant.Combatant;
import effect.StunEffect;
import java.util.List;
import ui.Describable;

public class ShieldBash implements Action, Describable {
    private static final String NAME = "Shield Bash";
    private static final String DESCRIPTION = """
        Deal damage to selected enemy.
        Selected enemy is unable to take actions for the current turn and the next turn.""";

    @Override
    public void execute(Combatant attacker, List<Combatant> targets) {
        Combatant target = targets.getFirst();
        int dmg = Math.max(0, attacker.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        target.addStatusEffect(new StunEffect(2));
        attacker.setSpecialSkillsCooldown(3);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}