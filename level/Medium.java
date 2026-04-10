package level;

public class Medium implements Difficulty {
    private static final String NAME = "MEDIUM";
    private static final String DESCRIPTION = "MEDIUM";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}