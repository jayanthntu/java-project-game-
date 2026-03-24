package item;

import java.util.List;
import combatant.Combatant;

public class Potion extends Item {

    private static final int HEAL_AMOUNT = 100;

    public Potion() {
        super("Potion");
    }

    @Override
    public void use(Combatant actor, List<Combatant> targets) {
        actor.heal(HEAL_AMOUNT);
        setUsed(true);
        System.out.println(actor.getName() + " used Potion! Healed "
                + HEAL_AMOUNT + " HP. HP: " + actor.getHP()
                + "/" + actor.getMaxHP());
    }
}
