package combatant;

import action.Action;
import action.ArcaneBlast;
import java.util.List;

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
}
