import java.util.Scanner;

public class SafeHouse extends NormalLoc {
    protected static Scanner s = new Scanner(System.in);
    private static Weapon[] ownedWeapons = new Weapon[Weapon.weapons().length];
    private static Armor[] ownedArmors = new Armor[Armor.armors().length];

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        regenHealth();
        boolean firstIn = true;
        int selectCase = 0;
        while (selectCase != 5) {
            if(!firstIn) {
                System.out.println("==========");
                System.out.println("Anything Else ? :");
            } else {
                System.out.println("You're in safe house, your health's been regened !!!");
                firstIn = false;
            }
            System.out.println("""
                    1 - Check Inventory
                    2 - Check Character
                    3 - Change Weapon
                    4 - Change Armor
                    5 - Quit""");
            System.out.print("Do ==> ");
            selectCase = input.nextInt();
            switch (selectCase) {
                case 1:
                    checkInv();
                    break;
                case 2:
                    checkChar();
                    break;
                case 3:
                    checkWeaponAvailable(this.getPlayer(), getOwnedWeapons());
                    break;
                case 4:
                    checkArmorAvailable(this.getPlayer(), getOwnedArmors());
                    break;
                case 5:
                    break;
            }
        }
        return true;
    }

    public void regenHealth() {
        this.getPlayer().setHealth(this.getPlayer().getMaxHealth() + this.getPlayer().getInventory().getArmor().getProtection());
    }

    public void checkInv() {
        System.out.println("==========");
        boolean anyWater = this.getPlayer().getInventory().isWater();
        boolean anyFood = this.getPlayer().getInventory().isFood();
        boolean anyFirewood = this.getPlayer().getInventory().isFirewood();
        System.out.print("Water : ");
        if (anyWater) System.out.println("Found");
        else System.out.println("Not Found");
        System.out.print("Food : ");
        if (anyFood) System.out.println("Found");
        else System.out.println("Not Found");
        System.out.print("Firewood : ");
        if (anyFirewood) System.out.println("Found");
        else System.out.println("Not Found");
        System.out.println("Your Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Your Armor : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Current Money : " + this.getPlayer().getMoney());
    }

    public void checkChar() {
        System.out.println("==========");
        System.out.println("Name : " + this.getPlayer().getPlayerName()
                + "\nCharacter : " + this.getPlayer().getName()
                + "\nCurrent Health : " + this.getPlayer().getHealth()
                + "\nCurrent Damage : " + this.getPlayer().getDamage());
    }

    public void checkWeaponAvailable(Player p, Weapon[] boughWeapons) {
        boolean isHave = false;
        for (Weapon tempW : boughWeapons) {
            if (tempW != null) {
                isHave = true;
                break;
            }
        }
        if (isHave) {
            equipWeapon(p);
        } else {
            System.out.println("You Haven't Unlocked Any Weapon Yet !!!");
        }
    }

    public static void printWeapon(Player p, Weapon[] weaponsList) {
        System.out.println("==========");
        Weapon currentWeapon = p.getInventory().getWeapon();
        System.out.println("Weapons You Owned : ");
        for (Weapon w : weaponsList) {
            if (w != null) {
                System.out.print("\nID : " + w.getID()
                        + "\tName : " + w.getName()
                        + "\tDamage : " + w.getDamage());
                if (w.equals(currentWeapon)) {
                    System.out.println(" (EQUİPPED)");
                }
            } else {
                System.out.println("---LOCKED---");
            }
        }
    }

    public void equipWeapon(Player p) {
        printWeapon(p, ownedWeapons);
        System.out.print("Which One You Want To Equip ==> ");
        int selectCase = s.nextInt();
        Weapon tempW = ownedWeapons[selectCase - 1];
        if (tempW == null) {
            System.out.println("You haven't got that item !!!");
        } else if (!tempW.equals(p.getInventory().getWeapon())) {
            System.out.print("-- " + p.getInventory().getWeapon().getName() + " Removed from Inventory --");
            p.getInventory().setWeapon(tempW);
            System.out.println("    ++ " + p.getInventory().getWeapon().getName() + " Added to Inventory ++");
        } else {
            System.out.println("You've already equipped " + tempW.getName() + " !!!");
        }
    }

    public void checkArmorAvailable(Player p, Armor[] armors) {
        boolean isHave = false;
        for (Armor a : armors) {
            if (a != null) {
                isHave = true;
                break;
            }
        }
        if (isHave) {
            equipArmor(p,armors);
        } else {
            System.out.println("You Haven't Unlocked Any Armor Yet !!!");
        }
    }

    public void equipArmor(Player p,Armor[] armors) {
        printArmor(p,armors);
        System.out.print("Which One You Want To Equip ==> ");
        int selectCase = s.nextInt();
        Armor tempA = ownedArmors[selectCase - 1];
        if (tempA == null) {
            System.out.println("You haven't got that item !!!");
        } else if (!tempA.equals(p.getInventory().getArmor())) {
            System.out.print("-- " + p.getInventory().getArmor().getName() + " Removed from Inventory --");
            p.getInventory().setArmor(tempA);
            System.out.println("    ++ " + p.getInventory().getArmor().getName() + " Added to Inventory ++");
        } else {
            System.out.println("You've already equipped " + tempA.getName() + " !!!");
        }

    }

    public static void printArmor(Player p, Armor[] armors) {
        System.out.println("==========");
        Armor currentArmor = p.getInventory().getArmor();
        System.out.println("Weapons You Owned : ");
        for (Armor a : armors) {
            if (a != null) {
                System.out.print("\nID : " + a.getID()
                        + "\tName : " + a.getName()
                        + "\tDamage : " + a.getProtection());
                if (a.equals(currentArmor)) {
                    System.out.println(" (EQUİPPED)");
                }
            } else {
                System.out.println("---LOCKED---");
            }
        }
    }

    public static Weapon[] getOwnedWeapons() {
        return ownedWeapons;
    }

    public static void setOwnedWeapons(Weapon[] ownedWeapons) {
        SafeHouse.ownedWeapons = ownedWeapons;
    }

    public static Armor[] getOwnedArmors() {
        return ownedArmors;
    }

    public static void setOwnedArmors(Armor[] ownedArmors) {
        SafeHouse.ownedArmors = ownedArmors;
    }
}
