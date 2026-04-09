package action;

import combatant.Combatant;

import java.util.List;

public class CustomSkill implements SpecialSkill {
    private static final String NAME = "???";
    private static final String DESCRIPTION = "";

    @Override
    public void execute(Combatant attacker, List<Combatant> targets) {
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}