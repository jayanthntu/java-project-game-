package level;

public class Easy implements Difficulty {
    private static final String NAME = "Easy";
    private static final String DESCRIPTION = "3 Goblins";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}