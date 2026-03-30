package action;

public class DefendAction implements Action {
    @Override
    public void execute(Combatant user, Combatant target) {
        user.addStatusEffect(new DefendEffect(2, 10));
    }
}