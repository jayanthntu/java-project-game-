package action;

import combatant.Combatant;
import item.Item;

import java.util.List;

public class ItemAction implements Action {
    private final Item itemToUse;

    public ItemAction(Item selectedItem) {
        itemToUse = selectedItem;
    }

    @Override
    public void execute(Combatant user, List<Combatant> targets) {
        itemToUse.use(user, targets);
    }
}