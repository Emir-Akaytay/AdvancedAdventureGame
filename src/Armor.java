public class Armor {
    private int ID;
    private String name;
    private int protection;
    private int cost;

    public Armor(int ID, String name, int protection, int cost) {
        this.ID = ID;
        this.name = name;
        this.protection = protection;
        this.cost = cost;
    }

    public static Armor[] armors() {
        Armor[] armorsList = new Armor[3];
        armorsList[0] = new Armor(1,"Light Armor",1,15);
        armorsList[1] = new Armor(2,"Middle Armor",3,25);
        armorsList[2] = new Armor(3,"Heavy Armor",5,40);
        return armorsList;
    }

    public static Armor uniqueArmor() {
        return new Armor(4,"Unique Armor",20,0);
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

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
