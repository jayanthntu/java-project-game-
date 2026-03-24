package combatant;

import effect.StatusEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class Combatant
{
    protected String name;
    protected int maxHP;
    protected int HP;
    protected int ATK;
    protected int DEF;
    protected int SPD;
    protected List<StatusEffect> StatusEffect;
    protected boolean isStunned;
    protected boolean isInvulnerable;
    protected int SpecialSkillsCooldown;


    public Combatant (String username, int maxHP, int HP, int ATK, int DEF, int SPD) 
    {
        this.name = username;
        this.maxHP = maxHP;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPD = SPD;
        this.StatusEffect = new ArrayList<>();
        this.isStunned = false;
        this.isInvulnerable = false;
        this.SpecialSkillsCooldown = 0;
    }

    public void takeDamage (int dmg) 
    {
        if (!isInvulnerable) 
        {
            HP -= dmg;
            if (HP < 0)
            {
                HP = 0;
            }
        }
    }

    public boolean isDefeated() 
    {
        return (this.HP <= 0);
    }

    public void setStunned(boolean stun) 
    {
        if (stun)
        {
            this.isStunned = true;
        } 
        else 
        {
            this.isStunned = false;
        }
    }

    public void setInvulnerable(boolean invulnerable) 
    {
        if (invulnerable) 
        {
            this.isInvulnerable = true;
        } 
        else 
        {
            this.isInvulnerable = false;
        }
    }

    public boolean isInvulnerable() 
    {
        return isInvulnerable;
    }

    public void heal(int amount) 
    {
        this.HP += amount;
        if (this.HP > this.maxHP)
        {
            this.HP = this.maxHP;
        }
    }

    public void increaseDefense(int amount) 
    {
        this.DEF += amount;
    }

    public void increaseAttack(int amount) 
    {
        this.ATK += amount;
    }

    public String getName() 
    {
        return this.name;
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
        return this.ATK;
    }

    public int getDefense() 
    {
        return this.DEF;
    }

    public int getSpecialCooldown() 
    {
        return this.SpecialSkillsCooldown;
    }

    public void setSpecialCooldown(int cooldown) 
    {
        this.SpecialSkillsCooldown = cooldown;
    }

    public void decrementSpecialCooldown() 
    {
        this.SpecialSkillsCooldown -= 1;
    }

    public List<StatusEffect> getStatusEffect() 
    {
        return this.StatusEffect;
    }

    public void addStatusEffect(StatusEffect effect) 
    {
        this.StatusEffect.add(effect);
        effect.apply(this);
    }

    public void removeStatusEffect(StatusEffect effect) 
    {
        effect.remove(this);
    }

    public int getSpeed() 
    {
        return this.SPD;
    }

    public boolean isStunned() 
    {
        return this.isStunned;
    }

    public void tickStatusEffects() 
    {
        for (StatusEffect effect : StatusEffect) 
        {
            if (effect.isActive()) 
            {
                effect.decrementDuration();
                if (effect.getDuration() <= 0) 
                {
                    effect.setActive(false);
                    removeStatusEffect(effect);
                }
            }
        }
    }
}