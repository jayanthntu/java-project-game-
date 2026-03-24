package action;

import combatant.Combatant;
import java.util.List;

public interface Action {
    void execute(Combatant actor, List<Combatant> targets);
}
