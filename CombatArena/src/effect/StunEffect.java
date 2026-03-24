package effect;

import combatant.Combatant;

public class StunEffect extends StatusEffect {
    public StunEffect() {
        super(2);
    }

    @Override
    public void apply(Combatant combatant) {
        combatant.setStunned(true);
    }

    @Override
    public void remove(Combatant combatant) {
        combatant.setStunned(false);
    }

    @Override
    public String toString() {
        return "Stunned (" + duration + " turns remaining)";
    }
}
