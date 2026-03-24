package item;

import java.util.List;
import combatant.Combatant;

public abstract class Item {
    protected String name;
    protected boolean used;

    public Item(String name) {
        this.name = name;
        this.used = false;
    }

    public abstract void use(Combatant actor, List<Combatant> targets);

    public boolean isUsed()   { return used; }
    public void setUsed(boolean used) { this.used = used; }
    public String getName()   { return name; }

    @Override
    public String toString()  { return name + (used ? " (used)" : ""); }
}