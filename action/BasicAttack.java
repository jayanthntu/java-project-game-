package action;

public class BasicAttack implements Action {
    @Override
    public void execute(Combatant attacker, Combatant target) {
        int dmg =  attacker.getAttack() - target.getDefense();
        target.takeDamage(dmg);
    }
}