package combatant;

import effect.StunEffect;

import java.util.List;

public class Warrior extends Player {
    public Warrior() {
        super("Warrior", 260, 260, 40, 20, 30);
    }

    Warrior(String name) {
        super(name, 260, 260, 40, 20, 30);
    }

    public void useSpecialSkill(List<Combatant> targets) {
        if (getSpecialCooldown() > 0) {
            System.out.println("Special skill on cooldown");
            return;
        }
        // Deal BasicAttack damage
        Combatant target = targets.getFirst();
        int dmg = Math.max(0, this.ATK - target.getDefense());
        target.takeDamage(dmg);
        // Apply stun
        target.addStatusEffect(new StunEffect());
        setSpecialCooldown(3);
    }

    public void useSpecialSkillEffect(List<Combatant> targets) {
        // Deal BasicAttack damage
        Combatant target = targets.get(0);
        int dmg = Math.max(0, this.ATK - target.getDefense());
        target.takeDamage(dmg);
        // Apply stun
        target.addStatusEffect(new StunEffect());
    }
}