package action;

public class SpecialSkill implements Action {
    @Override
    public void execute(Combatant attacker, Combatant target) {
        // Check if skill is available
        if (attacker.getSpecialCooldown() > 0)
            return;

        // Cooldown 3 turns
        attacker.getSpecialSkillCooldown(3)

        // Track state for each combatant
        if (attacker instanceof Warrior) {
            ShieldBash.execute(attacker, target);
        } else if (attacker instanceof Wizard) {
            ArcaneBlast.execute(attacker, target);
        }
    }
}
