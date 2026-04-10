package combatant;

import action.ArcaneBlast;

public class Wizard extends Player 
{
    public Wizard() {
        super("Wizard", 200, 200, 50, 10, 20);
        specialSkill = new ArcaneBlast();
    }

    public Wizard(Player player) {
        super(player);
        specialSkill = new ArcaneBlast();
    }

    @Override
    public Player copy() {
        return Wizard(this);
    }

    public Wizard(String name)
    {
        super(name, 200, 200, 50, 10, 20);
        specialSkill = new ArcaneBlast();
    }
}
