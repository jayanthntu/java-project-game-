package combatant;

import action.Action;
import action.ShieldBash;
import java.util.List;

public class Warrior extends Player {
    public Warrior() {
        super("Warrior", 260, 260, 40, 20, 30);
        specialSkill = new ShieldBash();
    }

    public Warrior(String name)
    {
        super(name, 260, 260, 40, 20, 30);
        specialSkill = new ShieldBash();
    }

    public Warrior(Player other) {
        super(other.getName(), other.getMaxHP(), other.getHP(), other.getAttack(), other.getDefense(), other.getSpeed());
        specialSkill = other.getSpecialSkill();
    }
}
