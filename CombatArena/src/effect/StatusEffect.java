package effect;

import combatant.Combatant;

public interface StatusEffect {
    protected int duration;
    protected boolean isActive;

    default StatusEffect(int duration) {
        this.duration = duration;
        this.isActive = true;
    }

    default void decrementDuration() {
        duration--;
        isActive = duration > 0;
    }

    default boolean isActive() {
        return isActive;
    }

    default int getDuration() {
        return duration;
    }

    default void setActive(boolean active) {
        isActive = active;
    }

    // Each subclass must define what they do to a combatant
    void apply(Combatant combatant);

    // Each subclass must define how to UNDO their effect when they expire
    void remove(Combatant combatant);
}