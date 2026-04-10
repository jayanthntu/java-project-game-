package level;

public class Custom implements Difficulty {
    private static final String NAME = "Custom";
    private static final String DESCRIPTION = "? Goblins + ? Wolves | Backup: ? Goblins + ? Wolves";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}