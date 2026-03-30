package effect;

public class StunEffect extends StatusEffect {
    public StunEffect(int duration) {
        super(duration);
    }

    @Override
    public void apply(Combatant target) {
        target.setStunned(true);
    }

    @Override
    public void remove(Combatant target) {
        target.setStunned(false);
    }

    @Override
    public String getName() {
        return "Stun effect";
    }
}

