package combatant;

import effect.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Combatant
{
    private final String name;
    private final int maxHP;
    private int HP;
    private int ATK;
    private int DEF;
    private final int SPD;
    private final List<StatusEffect> statusEffects;

    public Combatant(String username, int maxHP, int HP, int ATK, int DEF, int SPD) {
        this.name = username;
        this.maxHP = maxHP;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPD = SPD;
        this.statusEffects = new ArrayList<>();
    }

    public String getName() 
    {
        return name;
    }

    public int getHP() 
    {
        return this.HP;
    }

    public int getMaxHP() 
    {
        return this.maxHP;
    }

    public int getAttack() 
    {
        return ATK;
    }

    public int getSpeed() 
    {
    return this.SPD;
    }

    public int getDefense() 
    {
        return DEF;
    }

    public void increaseAttack(int atk) {
        ATK += atk;
    }

    public void decreaseAttack(int atk) { ATK -= atk; }


    public void increaseDefense(int def) {
        DEF += def;
    }

    public void decreaseDefense(int def) { DEF -= def; }

    public void takeDamage(int dmg) {
        if (!hasStatusEffect(SmokeBombEffect.class)) {
            HP -= dmg;
            if (HP < 0) {
                HP = 0;
            }
        }
    }

    public boolean isDefeated() {
        return HP <= 0;
    }

    public void heal(int amount) {
        HP += amount;
        if (HP > maxHP) {
            HP = maxHP;
        }
    }

    public List<StatusEffect> getStatusEffects() {
        return statusEffects;
    }

    public boolean hasStatusEffect(Class<? extends StatusEffect> type) {
        return statusEffects.stream().anyMatch(type::isInstance);
    }

    public void addStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
    }

    public void removeStatusEffect(StatusEffect effect) {
        statusEffects.remove(effect);
    }

    public void tickStatusEffects() {
        Iterator<StatusEffect> it = statusEffects.iterator();
        while (it.hasNext()) {
            StatusEffect current = it.next();
            current.reduceDuration();
            if (current.isExpired()) {
                current.remove(this);
                it.remove();
            }
        }
    }
}