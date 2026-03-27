public class ArcaneBlast implements Action {
    private List<Combatant> allEnemies;

    public ArcaneBlast(List<Combatant> allEnemies) {
        this.allEnemies = allEnemies;
    }
    
    @Override
    public void execute(Combatant user, Combatant target) {
        int defeated = 0;
        
        for (Combatant enemy : allEnemies) {
            if (!enemy.isAlive()) {
                continue;
            }
            int dmg = Math.max(0, user.getAttack() - enemy.getDefense());
            enemy.takeDamage(dmg);

            if(!enemy.isAlive()) {
                defeated++;
            }
        }
        if (deafeated > 0) {
            user.setAttack(user.getAttack() + (defeated * 10));
        }
    }
}
