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
    private int specialSkillCooldown = 0;

    public Player(String name, int maxHP, int HP, int ATK, int DEF, int SPD) 
    {
        super(name, maxHP, HP, ATK, DEF, SPD);
        items = new ArrayList<>();
    }

    public Player(Player other) {
        super(other.getName(), other.getMaxHP(), other.getHP(), other.getAttack(), other.getDefense(), other.getSpeed());
        specialSkill = other.getSpecialSkill().copy();
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
        if (getSpecialSkillCooldown() == 0) {
            useSpecialSkill(targets);
            setSpecialSkillCooldown(3);
        }
    }

    public final void useSpecialSkillWithoutCD(List<Combatant> targets) {
        int cooldown = getSpecialSkillCooldown();
        useSpecialSkill(targets);
        setSpecialSkillCooldown(cooldown);
    }

    private void useSpecialSkill(List<Combatant> targets) {
        specialSkill.execute(this, targets);
    }

    public SpecialSkill getSpecialSkill() {
        return specialSkill;
    }

    public int getSpecialSkillCooldown() {
        return specialSkillCooldown;
    }

    public void setSpecialSkillCooldown(int cooldown) {
        specialSkillCooldown = cooldown;
    }

    public void decrementSpecialSkillCooldown() {
        specialSkillCooldown -= 1;
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