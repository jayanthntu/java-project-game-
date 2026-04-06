package combatant;

import item.Item;
import java.util.List;
import java.util.ArrayList;

public abstract class Player extends Combatant {
    private List<Item> items;

    public Player(String name, int maxHP, int HP, int ATK, int DEF, int SPD) {
        super(name, maxHP, HP, ATK, DEF, SPD);
        items = new ArrayList<>();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public final void useSpecialSkillWithCD(List<Combatant> targets) {
        if (getSpecialSkillsCooldown() == 0) {
            useSpecialSkills(targets);
            setSpecialSkillsCooldown(3);
        }
    }

    public final void useSpecialSkillEffectWithoutCD(List<Combatant> targets) {
        useSpecialSkills(targets);
    }

    abstract void useSpecialSkills(List<Combatant> targets);
}