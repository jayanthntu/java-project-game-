package effect;

import combatant.Combatant;

public class ArcaneBlastEffect extends StatusEffect {

    private static final int ATK_BONUS_PER_KILL = 10;
    private int totalBonusApplied = 0;

    public ArcaneBlastEffect(int killCount) {
        super(Integer.MAX_VALUE); // lasts until end of level — never expires naturally
        this.totalBonusApplied = ATK_BONUS_PER_KILL * killCount;
    }

    @Override
    public void apply(Combatant combatant) {
        // +10 ATK per enemy killed by Arcane Blast
        combatant.increaseAttack(totalBonusApplied);
    }

    @Override
    public void remove(Combatant combatant) {
        // Only removed when the level ends, not by duration
        combatant.increaseAttack(-totalBonusApplied);
    }

    @Override
    public String toString() {
        return "Arcane Power +" + totalBonusApplied + " ATK (permanent this level)";
    }
}