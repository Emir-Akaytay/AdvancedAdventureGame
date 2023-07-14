import java.util.Arrays;

public class Store extends NormalLoc {
    public Store(Player player) {
        super(player, "Store");
    }

    @Override
    public boolean onLocation() {
        boolean firstIn = true;
        int selectCase = -1;
        while (selectCase != 0) {
            if(!firstIn) {
                System.out.println("==========");
                System.out.println("Anything Else ? :");
            } else {
                System.out.println("Welcome To The Store !!! What Do You Wanna Buy ? :");
                firstIn = false;
            }
            System.out.println("""
                    1 - Weapon
                    2 - Armor
                    0 - Quit""");
            System.out.print("Want ==> ");
            selectCase = input.nextInt();
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Choice !!! Please Select Again.");
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("\n===== Weapons =====");
        System.out.println("!Current Money : " + this.getPlayer().getInventory().getMoney());
        for (Weapon w : Weapon.weapons()) {
            System.out.println("ID : " + w.getID()
                    + "\tName : " + w.getName()
                    + "\tDamage : " + w.getDamage()
                    + "\tCost : " + w.getCost());
        }
        System.out.println("No one : " + 0);
    }

    public void buyWeapon() {
        Weapon tempW;
        System.out.print("Which ==> ");
        int selectCase = input.nextInt();
        if (selectCase != 0) {
            tempW = Weapon.weapons()[selectCase - 1];
            int balance = getPlayer().getInventory().getMoney() - tempW.getCost();
            Weapon currentWeapon = this.getPlayer().getInventory().getWeapon();
            if (SafeHouse.getOwnedWeapons()[tempW.getID() - 1] != null ) {
                System.out.println("You've already got " + tempW.getName() + " !!!");
            } else if (0 <= balance) {
                this.getPlayer().getInventory().setMoney(balance);
                weaponToInv(this.getPlayer(),tempW,currentWeapon);
            } else {
                System.out.println("You haven't got enough money for " + tempW.getName() + " !!!");
            }
        }
    }

    public void weaponToInv(Player p,Weapon tempW,Weapon currentWeapon) {
        System.out.print("You bought " + tempW.getName() + " !!!     ** " + tempW.getName() + " added to Inventory **");
        if(!currentWeapon.getName().equals("Bare Hands"))System.out.println("    -- " + currentWeapon.getName() + " removed from Inventory --" );
        else System.out.println();
        int plainDamage = p.getDamage() - p.getInventory().getWeapon().getDamage();
        int damage = plainDamage + tempW.getDamage();
        p.setDamage(damage);
        p.getInventory().setWeapon(tempW);
        SafeHouse.getOwnedWeapons()[tempW.getID() - 1] = tempW;
    }

    public void printArmor() {
        System.out.println("\n===== Armors =====");
        System.out.println("! Current Money : " + this.getPlayer().getInventory().getMoney());
        for (Armor a : Armor.armors()) {
            System.out.println("ID : " + a.getID()
                    + "\tName : " + a.getName()
                    + "\tDamage : " + a.getProtection()
                    + "\tCost : " + a.getCost());
        }
        System.out.println("No one : " + 0);
    }

    public void buyArmor() {
        Armor tempA;
        System.out.print("Which ==> ");
        int selectCase = input.nextInt();
        if (selectCase != 0) {
            tempA = Armor.armors()[selectCase - 1];
            int balance = this.getPlayer().getInventory().getMoney() - tempA.getCost();
            Armor currentArmor = this.getPlayer().getInventory().getArmor();
            if (SafeHouse.getOwnedArmors()[tempA.getID() - 1] != null) {
                System.out.println("You've already got " + tempA.getName() + " !!!");
            } else if (0 <= balance) {
                this.getPlayer().getInventory().setMoney(balance);
                armorToInv(this.getPlayer(),tempA,currentArmor);
            } else {
                System.out.println("You haven't got enough money for " + tempA.getName() + " !!!");
            }
        }
    }

    public void armorToInv(Player p,Armor tempA,Armor currentArmor) {
        System.out.print("You bought " + tempA.getName() + " !!!     ** " + tempA.getName() + " added to Inventory **");
        if(!currentArmor.getName().equals("Old Clothes")) System.out.println("    -- " + currentArmor.getName() + " removed from Inventory --" );
        else System.out.println();
        int plainHealth = p.getHealth() - p.getInventory().getArmor().getProtection();
        int health = plainHealth + tempA.getProtection();
        p.setMaxHealth(health);
        p.setHealth(health);
        p.getInventory().setArmor(tempA);
        SafeHouse.getOwnedArmors()[tempA.getID() - 1] = tempA;
    }
}
