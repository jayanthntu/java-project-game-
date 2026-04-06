package combatant;

import action.Action;
import action.ArcaneBlast;
import java.util.List;

public class Wizard extends Player {
    private final Action specialSkill = new ArcaneBlast();

    public Wizard()
    {
        super("Wizard", 200, 200, 50, 10, 20);
    }

    Wizard(String name)
    {
        super(name, 200, 200, 50, 10, 20);
    }

    @Override
    void useSpecialSkills(List<Combatant> targets) {
        specialSkill.execute(this, targets);
    }
}
