package action;

import combatant.Combatant;
import effect.DefendEffect;
import java.util.List;

public class Defend implements Action {

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        actor.addStatusEffect(new DefendEffect());
        System.out.println(actor.getName()
                + " defends! +10 DEF for 2 turns.");
    }
}