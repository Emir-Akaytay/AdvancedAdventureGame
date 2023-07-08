
public class Obstacle {
    private int ID;
    private String name;
    private int damage;
    private int health;
    private int moneyDrop;
    private int maxHealth;

    public Obstacle(int ID,String name, int damage, int health, int moneyDrop,int maxHealth) {
        this.ID = ID;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.moneyDrop = moneyDrop;
        this.maxHealth = maxHealth;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        if(health < 0) return 0;
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoneyDrop() {
        return moneyDrop;
    }

    public void setMoneyDrop(int moneyDrop) {
        this.moneyDrop = moneyDrop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
