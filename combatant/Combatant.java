package combatant;

import effect.StatusEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Combatant
{
    private String name;
    private int maxHP;
    private int HP;
    private int ATK;
    private int DEF;
    private int SPD;
    private List<StatusEffect> statusEffect;
    private boolean stunned;
    private boolean invulnerable;
    private int specialSkillsCooldown;

    public Combatant(String username, int maxHP, int HP, int ATK, int DEF, int SPD) {
        this.name = username;
        this.maxHP = maxHP;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPD = SPD;
        this.statusEffect = new ArrayList<>();
        this.stunned = false;
        this.invulnerable = false;
        this.specialSkillsCooldown = 0;
    }

    public String getName() { return name; }

    public int getHP() { return this.HP; }

    public int getMaxHP() { return this.maxHP; }

    public int getAttack() {
        return ATK;
    }

    public void increaseAttack(int atk) {
        ATK += atk;
    }

    public void decreaseAttack(int atk) { ATK -= atk; }

    public int getDefense() {
        return DEF;
    }

    public void increaseDefense(int def) {
        DEF += def;
    }

    public void decreaseDefense(int def) { DEF -= def; }

    public boolean isInvulnerable() { return invulnerable; }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public boolean isStunned() { return stunned; }

    public void setStunned(boolean stun) { this.stunned = stun; }

    public int getSpecialSkillsCooldown() { return specialSkillsCooldown; }

    public void setSpecialSkillsCooldown(int cooldown) { specialSkillsCooldown = cooldown; }

    public void decrementSpecialSkillsCooldown() { specialSkillsCooldown -= 1; }

    public void takeDamage(int dmg) {
        if (!invulnerable) {
            HP -= dmg;
            if (HP < 0) {
                HP = 0;
            }
        }
    }

    public boolean isDefeated() { return HP <= 0; }

    public void heal(int amount) {
        HP += amount;
        if (HP > maxHP) {
            HP = maxHP;
        }
    }

    public List<StatusEffect> getStatusEffect() { return statusEffect; }

    public void addStatusEffect(StatusEffect effect) {
        statusEffect.add(effect);
        effect.apply(this);
    }

    public void removeStatusEffect(StatusEffect effect) {
        effect.remove(this);
    }

    public int getSpeed() {
        return this.SPD;
    }

    public void tickStatusEffects() {
        Iterator<StatusEffect> it = statusEffect.iterator();
        while (it.hasNext()) {
            StatusEffect current = it.next();
            current.reduceDuration();
            if (current.isExpired()) {
                removeStatusEffect(current);
                it.remove();
            }
        }
    }
}