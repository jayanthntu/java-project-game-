package effect;

import combatant.Combatant;

public class StunEffect extends StatusEffect {
    public StunEffect(int duration) {
        super(duration);
    }

    @Override
    public void apply(Combatant target) {
        target.addStatusEffect(this);
    }

    @Override
    public void remove(Combatant target) {
        target.removeStatusEffect(this);
    }

    @Override
    public String toString() {
        return "Stunned (" + duration + " turns remaining)";
    }
}

