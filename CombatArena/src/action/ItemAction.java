package action;

import java.util.List;
import combatant.Combatant;
import item.Item;

public class ItemAction implements Action {
    private Item item;

    public ItemAction(Item item) {
        this.item = item;
    }

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        item.use(actor, targets);
    }
}