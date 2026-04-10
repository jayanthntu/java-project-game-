package combatant;

import action.CustomSkill;
import action.SpecialSkill;


public class CustomPlayer extends Player {
    public CustomPlayer() {
        super("???", 999, 999, 999, 999, 999);
        specialSkill = new CustomSkill();
    }

    public CustomPlayer(String name, int HP, int atk, int def, int spd, SpecialSkill specialSkill) {
        super(name, HP, HP, atk, def, spd);
        this.specialSkill = specialSkill;
    }

    public CustomPlayer(Player other) {
        super(other);
    }

    @Override
    public Player copy() {
        return new CustomPlayer(this);
    }
}
