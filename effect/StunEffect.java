package effect;

import combatant.Combatant;

public class StunEffect extends StatusEffect {
    public StunEffect(int duration) {
        super(duration);
    }

    @Override
    public void apply(Combatant target) {}

    @Override
    public void remove(Combatant target) {}

    @Override
    public String toString() {
        return "Stunned (" + duration + " turns remaining)";
    }
}

