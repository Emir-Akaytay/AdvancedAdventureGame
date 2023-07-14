
public class Inventory {
    private boolean water;
    private boolean food;
    private boolean firewood;
    private int money;
    private Weapon weapon;
    private Armor armor;

    Inventory() {
        this.water = false;
        this.food = false;
        this.firewood = false;
        this.money = 0;
        this.weapon = new Weapon(0,"Bare Hands",0,0);
        this.armor = new Armor(0,"Old Clothes",0,0);
    }


    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
