public class ItemAction implements Action {
    private Item itemToUse;

    private ItemAction(Item selectedItem) {
        this.itemToUse = selectedItem;
    }
    @Override
    public void execute(Combatant user, Combatant target) {
        itemToUse.use(user, target);
        user.removeItem(itemToUse);
    }
}