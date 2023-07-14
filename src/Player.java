import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private Inventory inventory;
    private Location location;
    private int damage;
    private int health;
    private String name;
    private int maxHealth;
    private String playerName;
    protected Scanner s = new Scanner(System.in);

    Player() {
        this.inventory = new Inventory();
        this.playerName = "";
        this.name = "";
        this.damage = 0;
        this.health = 0;
    }

    public void selectChar() {
        GameChar[] gameChars = {new Samurai(), new Archer(), new Knight(),new Admin()};
        System.out.println("---------------------------------------------------------------");
        System.out.println("Here are the characters and their attributes, choose wisely : ");
        for (GameChar g : gameChars) {
            System.out.println("ID : " + g.getID()
                    + "\t Name : " + g.getName()
                    + "\t\t Health : " + g.getHealth()
                    + "\t Money : " + g.getMoney());
        }
        System.out.println("----------------------------------------------------------------");
        int n = -1;
        while(n <= 0 || gameChars.length < n) {
            try {
                System.out.print("Which one : ");
                n = s.nextInt();
                if (n <= 0 || gameChars.length < n) {
                    System.out.println("Please Enter Valid Value !!!");
                    continue;
                }
            } catch(InputMismatchException e) {
                System.out.println("Please Enter An Integer and Valid Value !!!");
                System.out.print("Which one : ");
                s.nextLine();
                n = -1;
            }
            s.nextLine();
        }
        GameChar playerChar = gameChars[n - 1];
        initChar(playerChar);
    }

    public void initChar(GameChar g) {
        this.name = g.getName();
        this.damage = g.getDamage();
        this.health = g.getHealth();
        this.getInventory().setMoney(g.getMoney());
        this.maxHealth = g.getHealth();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return Math.max(this.health, 0);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
