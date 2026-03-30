import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public abstract class Player extends Combatant
{
    private List<Item> items;
    private int specialSkillCooldown;

    Scanner scan = new Scanner(System.in);

    public Player(String name, int maxHP, int HP, int ATK, int DEF, int SPD)
    {
        super(name, maxHP, HP, ATK, DEF, SPD);
        items = new ArrayList<>();
        specialSkillCooldown = 0;
    }

    public void chooseAction()
    {
        // printing relevant UI
        System.out.println("What action would you like to perform: ");
        System.out.println("---------------------------------------");
        System.out.println("1) Basic Attack");
        System.out.println("2) Special Attack");
        System.out.println("3) Defend");
        System.out.println("4) Item");
        System.out.println("---------------------------------------");
        System.out.print("Please input a number 1-4: ");

        // getting user input
        int userInput = scan.nextInt();

        // activating the correct methods
        switch (userInput)
        {
            case 1:
                this.basicAttack();
                break;

            case 2:
                this.specialAttack();
                break;

            case 3:
                this.defend();
                break;

            case 4:
                this.useItem();
                break;

            default:
                System.out.println("Invalid input.");
                break;
        }
    }

    public int getSpecialSkillCooldown() {
        return specialSkillCooldown;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setSpecialSkillCooldown(int rounds) {
        this.specialSkillCooldown = rounds;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public void updateCooldown() {
        if (specialSkillCooldown > 0) {
            specialSkillCooldown--;
        }
    }
}
