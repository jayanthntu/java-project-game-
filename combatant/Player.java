package combatant;

import action.SpecialSkill;
import item.Item;
import ui.Describable;
import java.util.List;

import java.util.ArrayList;

public abstract class Player extends Combatant implements Describable
{
    private List<Item> items;
    protected SpecialSkill specialSkill;

    public Player(String name, int maxHP, int HP, int ATK, int DEF, int SPD) 
    {
        super(name, maxHP, HP, ATK, DEF, SPD);
        items = new ArrayList<>();
    }

    public Player(Player other) {
        super(other.getName(), other.getMaxHP(), other.getHP(), other.getAttack(), other.getDefense(), other.getSpeed());
        items = new ArrayList<>();
    }

    public abstract Player copy();

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
        int cooldown = getSpecialSkillsCooldown();
        useSpecialSkills(targets);
        setSpecialSkillsCooldown(cooldown);
    }

    void useSpecialSkills(List<Combatant> targets) {
        specialSkill.execute(this, targets);
    }

    public SpecialSkill getSpecialSkill() {
        return specialSkill;
    }

    @Override
    public String getName()
    {
        return super.getName();
    }

    @Override
    public String getDescription()
    {
        return "HP: " + super.getHP() + " ATK: " + super.getAttack() + " DEF: " + super.getDefense()
            + " SPD: " + super.getSpeed() + " | Skill: " + this.getSpecialSkill().getName();  
    }
}