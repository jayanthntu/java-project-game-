package action;

import combatant.Combatant;
import effect.DefendEffect;
import java.util.List;

public class Defend implements Action {
    @Override
    public void execute(Combatant user, List<Combatant> targets) {
        user.addStatusEffect(new DefendEffect(2));
    }
}