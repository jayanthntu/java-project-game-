package level;

public class Easy implements Difficulty {
    private static final String NAME = "HARD";
    private static final String DESCRIPTION = "2 Goblins            | Backup: 1 Goblin + 2 Wolves";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}