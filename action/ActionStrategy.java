package action;

public class ActionStrategy {
    public Action selectAction() {
        return new BasicAttack();
    }
}