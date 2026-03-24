package engine;

import combatant.Combatant;
import java.util.List;

public interface TurnOrderStrategy {
    List<Combatant> determineTurnOrder(List<Combatant> combatants);
}