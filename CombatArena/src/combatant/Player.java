package combatant;

import item.Item;
import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Combatant{
    public Player(String username, int maxHP, int HP, int ATK, int DEF, int SPD) {
        super(username, maxHP, HP, ATK, DEF, SPD);
    }

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public abstract void useSpecialSkill(List<Combatant> targets);

    public abstract void useSpecialSkillEffect(List<Combatant> targets);
}
