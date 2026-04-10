package level;

public class Custom implements Difficulty {
    private static final String NAME = "CUSTOM";
    private static final String DESCRIPTION = "CUSTOM";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}