package effect;

import combatant.Combatant;

public abstract class StatusEffect {
    protected int duration;

    public StatusEffect(int duration) {
        this.duration = duration;
    }

    public void reduceDuration() {
        duration--;
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    // When the effect is active
    public abstract void apply(Combatant target);
    // When the effect expires
    public abstract void remove(Combatant target);

    @Override
    public abstract String toString();
}
