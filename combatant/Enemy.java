package combatant;

import action.Action;
import action.ActionStrategy;

import java.util.List;

public abstract class Enemy extends Combatant
{
    public Enemy(String name, int maxHP, int HP, int ATK, int DEF, int SPD)
    {
        super(name, maxHP, HP, ATK, DEF, SPD);
    }

    public Action takeTurn(List<Combatant> targets) {
        ActionStrategy action = new ActionStrategy();
        return action.selectAction();
    }

    public abstract Enemy copy();
} 
