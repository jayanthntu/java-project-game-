package level;

public class Easy implements Difficulty {
    private static final String NAME = "EASY";
    private static final String DESCRIPTION = "EASY";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}