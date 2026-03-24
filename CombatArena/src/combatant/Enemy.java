package combatant;

import action.Action;
import action.ActionStrategy;
import java.util.List;

public class Enemy extends Combatant{
    public Enemy(String username, int maxHP, int HP, int ATK, int DEF, int SPD) {
        super(username, maxHP, HP, ATK, DEF, SPD);
    }

    public Action takeTurn(List<Combatant> targets) {
        ActionStrategy action = new ActionStrategy();
        return action.selectAction();

    }

}