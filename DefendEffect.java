public class DefendEffect extends StatusEffect {
    private int amount;

    public DefendEffect(int duration, int amount) {
        super(duration);
        this.amount = amount;
    }

    @Override
    public void apply(Combatant target) {
        // Different combatants have different defense amount 
        target.setDefense(target.getDefense() + this.amount);
    }

    @Override
    public void remove(Combatant target) {
        // Returns original amount after effect expires
        target.setDefense(target.getDefense() - this.amount);
    }

    @Override
    public String getName() {
        return "Defend effect";
    }
}