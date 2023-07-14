
public class Weapon {
    private int ID;
    private String name;
    private int damage;
    private int cost;

    public Weapon(int ID, String name, int damage, int cost) {
        this.ID = ID;
        this.name = name;
        this.damage = damage;
        this.cost = cost;
    }



    public static Weapon[] weapons() {
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] = new Weapon(1,"Pistol",2,25);
        weaponList[1] = new Weapon(2,"Sword",3,35);
        weaponList[2] = new Weapon(3,"Rifle",7,35);
        return weaponList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
