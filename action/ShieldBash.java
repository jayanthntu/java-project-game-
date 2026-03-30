package action;

public class ShieldBash implements Action {
    @Override
    public void execute(Combatant attacker, Combatant target) {
        int dmg = attacker.getAttack() - target.getDefense();
        target.takeDamage(dmg);
        target.addStatusEffect(new StunEffect(2));
        attacker.setSpecialSkillCooldown(3);
    }
}