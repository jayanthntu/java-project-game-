package effect;

import combatant.Combatant;

public class SmokeBombEffect extends StatusEffect {

    public SmokeBombEffect() {
        super(2); // current turn + next turn
    }

    @Override
    public void apply(Combatant combatant) {
        // Flag on the combatant that makes incoming damage = 0
        combatant.setInvulnerable(true);

    }

    @Override
    public void remove(Combatant combatant) {
        combatant.setInvulnerable(false);
    }

    @Override
    public String toString() {
        return "Smoke Bomb active (" + duration + " turns remaining)";
    }
}