package combatant;

public class Goblin extends Enemy
{
    public Goblin(String name) {
        super(name, 55, 55, 35, 15, 25);
    }

    @Override
    public Enemy copy() {
        return new Goblin(super.getName());
    }
}
