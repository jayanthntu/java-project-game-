package item;

import effect.SmokeBombEffect;
import combatant.Combatant;
import java.util.List;

public class SmokeBomb extends Item {

    public SmokeBomb() {
        super("Smoke Bomb");
    }

    @Override
    public void use(Combatant actor, List<Combatant> targets) {
        actor.addStatusEffect(new SmokeBombEffect(2));
        setUsed(true);
        System.out.println(actor.getName()
                + " used Smoke Bomb! Enemy attacks deal 0 damage for 2 turns.");
    }
}
