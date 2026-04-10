package effect;

import combatant.Combatant;

public class SmokeBombEffect extends StatusEffect {
    public SmokeBombEffect(int duration) {
        super(duration);
    }

    @Override
    public void apply(Combatant target) {}

    @Override
    public void remove(Combatant target) {}

    @Override
    public String toString() {
        return "Smoke Bomb active (" + duration + " turns remaining)";
    }
}

