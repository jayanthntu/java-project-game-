public class BasicAttack implements Action {
    @Override
    public void execute(Combatant attacker, Combatant target) {
        int dmg = Math.max(0, attacker.getAttack() - target.getDefense());
        target.takeDamage(dmg);
    }
}