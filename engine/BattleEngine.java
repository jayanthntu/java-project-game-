package engine;

import action.Action;
import combatant.Combatant;
import combatant.Enemy;
import combatant.Player;
import level.Level;
import ui.GameUI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BattleEngine {
    private List<Combatant> combatants;
    private TurnOrderStrategy strategy;
    private Player player;
    private Level level;
    private GameUI ui;
    private int roundCount = 0;

    public BattleEngine(Player player, Level level, GameUI ui) {
        this.player = player;
        this.level = level;
        this.ui = ui;
        this.strategy = new SpeedBasedTurnOrder();
        this.combatants = new ArrayList<>();
        this.combatants.add(player);
        this.combatants.addAll(level.getInitialSpawn());
    }

    public void runRound() {
        roundCount++;
        ui.displayBattleStatus(combatants, roundCount, player);

        List<Combatant> order = strategy.determineTurnOrder(combatants);

        for (Combatant current : order) {
            if (current.isDefeated()) continue;

            // Check if stunned (stun skips turn)
            if (current.isStunned()) {
                ui.displayMessage(current.getName() + " is STUNNED - turn skipped.");
                continue;
            }

            if (current instanceof Player p) {
                List<Combatant> aliveEnemies = getAliveEnemies();
                Action action = ui.showActionMenu(p, aliveEnemies);
                action.execute(p, aliveEnemies);
                if (p.getSpecialSkillsCooldown() > 0) {
                    p.decrementSpecialSkillsCooldown();
                }
            } else if (current instanceof Enemy e) {
                Action action = e.takeTurn(List.of(player));
                action.execute(e, List.of(player));
            }

            if (isOver()) return;
        }

        for (Combatant current : order) {
            current.tickStatusEffects();
        }

        // Check if backup spawn should trigger
        List<Enemy> aliveEnemies = getAliveEnemies().stream()
                .filter(c -> c instanceof Enemy)
                .map(c -> (Enemy) c)
                .collect(Collectors.toList());

        if (aliveEnemies.isEmpty() && level.hasBackupSpawn()) {
            List<Enemy> backup = level.triggerBackupSpawn();
            ui.displayMessage("Backup enemies have spawned!");
            combatants.addAll(backup);
        }


    }

    public boolean isOver() {
        boolean playerDefeated = player.isDefeated();
        boolean allEnemiesDefeated = getAliveEnemies().isEmpty() && !level.hasBackupSpawn();
        return playerDefeated || allEnemiesDefeated;
    }

    public boolean playerWon() {
        return !player.isDefeated() && getAliveEnemies().isEmpty() && !level.hasBackupSpawn();
    }

    private List<Combatant> getAliveEnemies() {
        return combatants.stream()
                .filter(c -> c instanceof Enemy && !c.isDefeated())
                .collect(Collectors.toList());
    }

    public Player getPlayer() { return player; }
    public int getRoundCount() { return roundCount; }
}
