package combatant;

import effect.StunEffect;
import java.util.List;

public class Wizard extends Player {
    public Wizard() {
        super("Wizard", 200, 200, 50, 10, 20);
    }

    Wizard(String name) {
        super(name, 200, 200, 50, 10, 20);
    }

    @Override
    public void useSpecialSkill(List<Combatant> targets) {
        if (getSpecialCooldown() > 0) {
            System.out.println("Special skill on cooldown");
            return;
        }
        for (Combatant target : targets) {
            int dmg = Math.max(0, this.ATK - target.getDefense());
            target.takeDamage(dmg);
            if (target.isDefeated()) {
                this.ATK += 10;
            }
        }
        setSpecialCooldown(3);
    }

    @Override
    public void useSpecialSkillEffect(List<Combatant> targets) {
        for (Combatant target : targets) {
            int dmg = Math.max(0, this.ATK - target.getDefense());
            target.takeDamage(dmg);
            if (target.isDefeated()) {
                this.ATK += 10;
            }
        }
    }

}