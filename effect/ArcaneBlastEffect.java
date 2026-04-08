package effect;

import combatant.Combatant;

public class ArcaneBlastEffect extends StatusEffect {
    private final int amount;

    public ArcaneBlastEffect(int amount) {
        super(Integer.MAX_VALUE);
        this.amount = amount;
    }

    @Override
    public void apply(Combatant target) {
        // Different combatants have different defense amount
        target.increaseAttack(amount);
    }

    @Override
    public void remove(Combatant target) {
        // Returns the original amount after effect expires
        target.decreaseAttack(amount);
    }

    @Override
    public String toString() {
        return "Arcane Power +" + amount + " ATK (permanent this level)";
    }
}