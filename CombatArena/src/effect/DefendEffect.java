package effect;

import combatant.Combatant;

public class DefendEffect extends StatusEffect {

    private static final int DEFENSE_BONUS = 10;

    public DefendEffect() {
        super(2); // defend lasts current round + next round
    }

    @Override
    public void apply(Combatant combatant) {
        // Directly boost the combatant's defense stat
        combatant.increaseDefense(DEFENSE_BONUS);
    }

    @Override
    public void remove(Combatant combatant) {
        // CRITICAL: take back the boost when effect expires
        // Otherwise defense stays raised permanently
        combatant.increaseDefense(-DEFENSE_BONUS);
    }

    @Override
    public String toString() {
        return "Defending +" + DEFENSE_BONUS + " DEF (" + duration + " turns remaining)";
    }
}