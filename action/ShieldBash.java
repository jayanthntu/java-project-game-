package action;

import combatant.Combatant;
import combatant.Player;
import effect.StunEffect;
import java.util.List;

public class ShieldBash implements SpecialSkill {
    private static final String NAME = "Shield Bash";
    private static final String DESCRIPTION = """
        Deal damage to selected enemy.
        Selected enemy is unable to take actions for the current turn and the next turn.""";

    @Override
    public void execute(Combatant user, List<Combatant> targets) {
        Player userPlayer = (Player) user;
        Combatant target = targets.getFirst();
        int dmg = Math.max(0, user.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        target.addStatusEffect(new StunEffect(2));
        userPlayer.setSpecialSkillCooldown(3);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public SpecialSkill copy() {
        return new ShieldBash();
    }
}