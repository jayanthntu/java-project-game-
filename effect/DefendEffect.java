package effect;

import combatant.Combatant;

public class DefendEffect extends StatusEffect {
    private static final int DEFENSE_BONUS = 10;

    public DefendEffect(int duration) {
        super(duration);
    }

    @Override
    public void apply(Combatant target) {
        // Different combatants have different defense amount 
        target.increaseDefense(DEFENSE_BONUS);
    }

    @Override
    public void remove(Combatant target) {
        // Returns the original amount after effect expires
        target.decreaseDefense(DEFENSE_BONUS);
    }

    @Override
    public String toString() {
        return "Defending +" + DEFENSE_BONUS + " DEF (" + duration + " turns remaining)";
    }
}