package effect;

import combatant.Combatant;

public class SmokeBombEffect extends StatusEffect {
    public SmokeBombEffect(int duration) {
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
        return "Smoke Bomb active (" + duration + " turns remaining)";
    }
}

