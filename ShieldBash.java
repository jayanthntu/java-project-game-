public class ShieldBash implements Action {
    @Override
    public void execute(Combatant performer, Combatant target) {
        int dmg = Math.max(0, performer.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        target.addStatusEffect(new StunEffect(2));
        performer.setSpecialSkillCooldown(3);
    }
