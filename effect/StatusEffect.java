package effect;

import combatant.Combatant;

public abstract class StatusEffect {
    protected int duration;

    public StatusEffect(int duration) {
        this.duration = duration;
    }

    // When the effect is active
    public abstract void apply(Combatant target);
    // When the effect expires
    public abstract void remove(Combatant target);
    public abstract String getName();

    public void reduceDuration() {
        this.duration--;
    }

    public boolean isExpired() {
        return duration <= 0;
    }
}
