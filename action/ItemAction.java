package action;

public class ItemAction implements Action {
    private Item itemToUse;

    public ItemAction(Item selectedItem) {
        this.itemToUse = selectedItem;
    }

    @Override
    public void execute(Combatant user, Combatant target) {
        itemToUse.use(user, target);
        user.removeItem(itemToUse);
    }
}