package item;

import java.util.List;
import combatant.Combatant;

public abstract class Item 
{
    protected String name;
    protected String description;
    protected boolean used;

    public Item(String name, String description) 
    {
        this.name = name;
        this.description = description;
        this.used = false;
    }

    public abstract void use(Combatant actor, List<Combatant> targets);

    public boolean isUsed() 
    {
        return used;
    }

    public void setUsed(boolean used) 
    {
        this.used = used;
    }

    public String getName() 
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() 
    {
        return name + " - " + description + (used ? " (used)" : "");
    }
}