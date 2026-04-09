package combatant;

import action.ShieldBash;

public class Warrior extends Player 
{
    public Warrior() {
        super("Warrior", 260, 260, 40, 20, 30);
        specialSkill = new ShieldBash();
    }

    public Warrior(Player player) {
        super(player);
    }

    public Warrior(String name)
    {
        super(name, 260, 260, 40, 20, 30);
        specialSkill = new ShieldBash();
    }
}
