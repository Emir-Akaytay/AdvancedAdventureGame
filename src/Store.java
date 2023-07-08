import java.util.Arrays;

public class Store extends NormalLoc {
    public Store(Player player) {
        super(player, "Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome To The Store !!! What Do You Wanna Buy ? :");
        while (true) {
            System.out.println("""
                    1 - Weapon
                    2 - Armor
                    3 - Quit""");
            System.out.print("Want ==> ");
            int selectCase = input.nextInt();
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Choice !!! Please Select Again.");
                    continue;
            }
            if(selectCase == 3) {
                break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("\n===== Weapons =====");
        System.out.println("! Current Money : " + this.getPlayer().getMoney());
        for (Weapon w : Weapon.weapons()) {
            System.out.println("ID : " + w.getID()
                    + "\tName : " + w.getName()
                    + "\tDamage : " + w.getDamage()
                    + "\tCost : " + w.getCost());
        }
        System.out.println("No one : " + (Weapon.weapons().length + 1));
    }

    public void buyWeapon() {
        Weapon w;
        System.out.print("Which ==> ");
        int selectCase = input.nextInt();
        System.out.println("\n===========");
        if (selectCase == Weapon.weapons().length + 1) {
            System.out.println("Anything else ?");
        } else {
            w = Weapon.weapons()[selectCase - 1];
            int balance = getPlayer().getMoney() - w.getCost();
            Weapon currentWeapon = this.getPlayer().getInventory().getWeapon();
            if (SafeHouse.getOwnedWeapons()[w.getID() - 1] != null ) {
                System.out.println("You've already got " + currentWeapon.getName() + " !!!");
            } else if (0 <= balance) {
                System.out.println("You bought " + w.getName() + " !!!     ** " + w.getName() + " added to Inventory **     -- " + currentWeapon.getName() + " removed from Inventory --" );
                this.getPlayer().setMoney(balance);
                weaponToInv(w, this.getPlayer());
            } else {
                System.out.println("You haven't got enough money for " + w.getName() + " !!!");
            }
            System.out.println("Anything Else ? :");
        }
    }

    public void weaponToInv(Weapon w, Player p) {
        int plainDamage = p.getDamage() - p.getInventory().getWeapon().getDamage();
        int damage = plainDamage + w.getDamage();
        p.setDamage(damage);
        p.getInventory().setWeapon(w);
        SafeHouse.getOwnedWeapons()[w.getID() - 1] = w;
    }

    public void printArmor() {
        System.out.println("\n===== Armors =====");
        System.out.println("! Current Money : " + this.getPlayer().getMoney());
        for (Armor a : Armor.armors()) {
            System.out.println("ID : " + a.getID()
                    + "\tName : " + a.getName()
                    + "\tDamage : " + a.getProtection()
                    + "\tCost : " + a.getCost());
        }
        System.out.println("No one : " + (Armor.armors().length + 1));
    }

    public void buyArmor() {
        Armor a;
        System.out.print("Which ==> ");
        int selectCase = input.nextInt();
        System.out.println("\n==========");
        if (selectCase == Weapon.weapons().length + 1) {
            System.out.println("Anything else ?");
        } else {
            a = Armor.armors()[selectCase - 1];
            int balance = this.getPlayer().getMoney() - a.getCost();
            Armor currentArmor = this.getPlayer().getInventory().getArmor();
            if (a.getName().equals(currentArmor.getName())) {
                System.out.println("You've already equipped " + currentArmor.getName() + " !!!");
            } else if (0 <= balance) {
                System.out.println("You bought " + a.getName() + " !!!     ** " + a.getName() + " added to Inventory **     -- " + currentArmor.getName() + " removed from Inventory --" );
                this.getPlayer().setMoney(balance);
                armorToInv(a, this.getPlayer());
            } else {
                System.out.println("You haven't got enough money for " + a.getName() + " !!!");
            }
            System.out.println("Anything Else ? :");
        }
    }

    public void armorToInv(Armor a, Player p) {
        int plainHealth = p.getHealth() - p.getInventory().getArmor().getProtection();
        int health = plainHealth + a.getProtection();
        p.setMaxHealth(health);
        p.setHealth(health);
        p.getInventory().setArmor(a);
    }
}
