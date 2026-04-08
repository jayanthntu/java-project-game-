package combatant;

import action.ArcaneBlast;

public class Wizard extends Player {
    public Wizard() {
        super("Wizard", 200, 200, 50, 10, 20);
        specialSkill = new ArcaneBlast();
    }

    public Wizard(String name)
    {
        super(name, 200, 200, 50, 10, 20);
        specialSkill = new ArcaneBlast();
    }

    public Wizard(Player other) {
        super(other.getName(), other.getMaxHP(), other.getHP(), other.getAttack(), other.getDefense(), other.getSpeed());
        specialSkill = other.getSpecialSkill();
    }
}
