package combatant;

public abstract class Combatant
{
    protected String name;
    protected int maxHP;
    protected int HP;
    protected int ATK;
    protected int DEF;
    protected int SPD;
    protected boolean invulnerable;
    protected List<StatusEffect> StatusEffect;

    public int getAttack() {
        return ATK;
    }

    public int getDefense() {
        return DEF;
    }

    public void setAttack(int atk) {
        ATK = atk;
    }

    public void setDefense(int def) {
        DEF = def;
    }

    public Combatant (String username, int maxHP, int HP, int ATK, int DEF, int SPD)
    {
        this.name = username;
        this.maxHP = maxHP;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPD = SPD;
    }

    public void setInvulnerable() {
        this.invulnerable = true;
    }

    public void takeDamage(int dmg) {
        if (this.invulnerable) {
            return;
        }
        else {
            HP = max(this.HP - dmg, 0);
        }
    }
   
    public boolean isDefeated()
    {
        return (this.HP <= 0); 
    }
}
