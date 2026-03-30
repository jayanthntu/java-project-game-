package action;

import combatant.Combatant;
import effect.ArcaneBlastEffect;

import java.util.List;

public class ArcaneBlast implements Action {
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
}