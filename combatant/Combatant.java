public abstract class Combatant
{
    protected String name;
    protected int maxHP;
    protected int HP;
    protected int ATK;
    protected int DEF;
    protected int SPD;
    protected List<StatusEffect> StatusEffect;

    public Combatant (String username, int maxHP, int HP, int ATK, int DEF, int SPD)
    {
        this.name = username;
        this.maxHP = maxHP;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPD = SPD;
    }

    public int getSpecialSkillCooldown() {
        return setSpecialSkillCooldown;
    }

    public void getSpecialSkillCooldown(int rounds) {
        this.SpecialSkillCooldown = rounds;
    }
    
    public void updateCooldown() {
        if (SpecialSkillCooldown > 0) {
            SpecialSkillCooldown--;
        }
    }
   
    public void takeDamage (int dmg)
    {
        this.HP = this.HP - dmg;
        if (this.HP < 0)
        {
            this.HP = 0;
        }
    }

    public boolean isDefeated()
    {
        return (this.HP <= 0); 
    }
}
