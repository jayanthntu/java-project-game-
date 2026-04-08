package item;

import java.util.List;
import combatant.Combatant;
import combatant.Player;

public class PowerStone extends Item 
{

    public PowerStone() 
    {
        super("Power Stone", "Free extra use of special skill");
    }

    @Override
    public void use(Combatant actor, List<Combatant> targets) 
    {
        if (actor instanceof Player player) 
        {
            // Trigger special skill effect without touching cooldown
            player.useSpecialSkillEffectWithoutCD(targets);
            setUsed(true);
            System.out.println(actor.getName()
                    + " used Power Stone! Special skill triggered for free!");
        }
    }

    @Override
    public Item copy() 
    {
        return new PowerStone();
    }
}
