public interface SpecialSkill implements Action {
    void execute(Combatant attacker, Combatant target);
    // Check if skill is available
    if (performer.getSpecialCooldown() > 0) {
        return;

    // Cooldown 3 turns
    performer.getSpecialSkillCooldown(3)

    // Track state for each combatant
    if (performer instanceof Warrior) {
        executeShieldBash((Warrior) performer, target);
    } else if {performer instanceof Wizard} {
        executeArcaneBlast((Wizard) performer);
    }
}
