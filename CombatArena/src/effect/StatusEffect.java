package effect;

import combatant.Combatant;

public abstract class StatusEffect {
    protected int duration;
    protected boolean isActive;

    public StatusEffect(int duration) {
        this.duration = duration;
        this.isActive = true;
    }

    // Shared logic — all effects use this identically
    public void decrementDuration() {
        duration--;
        isActive = duration > 0;
    }

    public boolean isActive()   { return isActive; }
    public int getDuration()    { return duration; }
    public void setActive(boolean active) { isActive = active; }

    // Each subclass must define what they do to a combatant
    public abstract void apply(Combatant combatant);

    // Each subclass must define how to UNDO their effect when they expire
    public abstract void remove(Combatant combatant);
}