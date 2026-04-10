package action;

import combatant.Combatant;
import effect.ArcaneBlastEffect;
import java.util.List;

public class ArcaneBlast implements SpecialSkill 
{
    private static final String NAME = "Arcane Blast";
    private static final String DESCRIPTION = """
        Deal damage to all enemies currently in combat.
        Each enemy defeated by Arcane Blast adds 10 to the Wizard's Attack, lasting until end of the level.""";

    private static final int ATK_BONUS_PER_KILL = 10;
    private int totalBonusApplied = 0;
    private int enemyKilled = 0;

    @Override
    public void execute(Combatant user, List<Combatant> targets) {
        for (Combatant target : targets) {
            if (!target.isDefeated()) {
                int dmg = Math.max(0, user.getAttack() - target.getDefense());
                target.takeDamage(dmg);
                if (target.isDefeated()) {
                    enemyKilled += 1;
                }
            }
        }
        totalBonusApplied = enemyKilled * ATK_BONUS_PER_KILL;
        user.addStatusEffect(new ArcaneBlastEffect(totalBonusApplied));
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