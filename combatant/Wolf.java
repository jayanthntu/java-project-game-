package combatant;

public class Wolf extends Enemy
{
    public Wolf(String name)
    {
        super(name, 40, 40, 45, 5, 35);
    }

    @Override
    public Enemy copy() {
        return new Wolf(super.getName());
    }
}
