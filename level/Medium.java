package level;

public class Easy implements Difficulty {
    private static final String NAME = "MEDIUM";
    private static final String DESCRIPTION = "1 Goblin + 1 Wolf    | Backup: 2 Wolves";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}