package level;

public class Hard implements Difficulty {
    private static final String NAME = "HARD";
    private static final String DESCRIPTION = "HARD";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}