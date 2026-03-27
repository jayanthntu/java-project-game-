public class SmokeBombEffect extends StatusEffect {
    public SmokeBombEffect(int duration) {
        super(duration);
    }
    @Override
    public void apply(Combatant target) {
        target.setInvulnerable(true);
    }
    @Override
    public void remove(Combatant target) {
        target.setInvulnerable(false);
    }
}

