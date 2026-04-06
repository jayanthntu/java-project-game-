package combatant;

import action.Action;
import action.ShieldBash;
import java.util.List;

public class Warrior extends Player {
    private final Action specialSkill = new ShieldBash();

    public Warrior() { super("Warrior", 260, 260, 40, 20, 30); }

    Warrior(String name)
    {
        super(name, 260, 260, 40, 20, 30);
    }

    @Override
    void useSpecialSkills(List<Combatant> targets) {
        specialSkill.execute(this, targets);
    }
}
