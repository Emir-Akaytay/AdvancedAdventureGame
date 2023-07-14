import java.util.Scanner;

public class SafeHouse extends NormalLoc {
    protected static Scanner s = new Scanner(System.in);
    private static Weapon[] ownedWeapons = new Weapon[Weapon.weapons().length];
    private static Armor[] ownedArmors = new Armor[Armor.armors().length + 1];

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        regenHealth();
        boolean firstIn = true;
        int selectCase = -1;
        while (selectCase != 0) {
            if (!firstIn) {
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
                    0 - Quit""");
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
                case 0:
                    break;
                default:
                    System.out.println("Please Enter Valid Values !!!");
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
        System.out.println("Current Money : " + this.getPlayer().getInventory().getMoney());
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

    public static void printWeapon(Player p, Weapon[] boughtWeapons) {
        System.out.println("==========");
        Weapon currentWeapon = p.getInventory().getWeapon();
        System.out.print("Weapons You Owned : \n");
        for (Weapon w : boughtWeapons) {
            if (w != null) {
                System.out.print("ID : " + w.getID()
                        + "\tName : " + w.getName()
                        + "\tDamage : " + w.getDamage());
                if (!w.equals(currentWeapon)) {
                    System.out.println();
                } else {
                    System.out.println(" (EQUİPPED)");
                }
            } else {
                System.out.println("----------LOCKED----------");
            }
        }
    }

    public void equipWeapon(Player p) {
        printWeapon(p, ownedWeapons);
        System.out.print("Which One You Want To Equip (No One : 0)");
        if (!p.getInventory().getWeapon().getName().equals("Bare Hands")) {
            System.out.print(" (Note : Press the ID of the Equipped Weapon To Unequip It) ==> ");
        } else {
            System.out.print(" ==>");
        }
        int selectCase = -1;
        while (selectCase < 0 || getOwnedWeapons().length < selectCase) {
            selectCase = s.nextInt();
            if(selectCase < 0 || getOwnedWeapons().length < selectCase) {
                System.out.println("Invalid Choice !!! Please Enter Valid Choice !!!");
                System.out.print("==> ");
            }
        }
        if (selectCase != 0) {
            Weapon tempW = getOwnedWeapons()[selectCase - 1];
            if (tempW == null) {
                System.out.println("You haven't got that item !!!");
            } else if (!tempW.equals(p.getInventory().getWeapon())) {
                weaponToInv(p, tempW);
            } else {
                System.out.println("-- " + p.getInventory().getWeapon().getName() + " Removed from Inventory --");
                p.getInventory().setWeapon(new Weapon(0, "Bare Hands", 0, 0));
            }
        }
    }

    public void weaponToInv(Player p, Weapon tempW) {
        if (!p.getInventory().getWeapon().getName().equals("Bare Hands")) {
            System.out.print("-- " + p.getInventory().getWeapon().getName() + " Removed from Inventory --");
        }
        p.getInventory().setWeapon(tempW);
        int plainDamage = p.getDamage() - p.getInventory().getWeapon().getDamage();
        int damage = plainDamage + tempW.getDamage();
        p.setDamage(damage);
        System.out.println("    ** " + p.getInventory().getWeapon().getName() + " Added to Inventory **");
    }

    public void checkArmorAvailable(Player p, Armor[] boughtArmors) {
        boolean isHave = false;
        for (Armor a : boughtArmors) {
            if (a != null) {
                isHave = true;
                break;
            }
        }
        if (isHave) {
            equipArmor(p, boughtArmors);
        } else {
            System.out.println("You Haven't Unlocked Any Armor Yet !!!");
        }
    }

    public void equipArmor(Player p, Armor[] ownedArmors) {
        printArmor(p, ownedArmors);
        System.out.print("Which One You Want To Equip (No One : 0)");
        if (!p.getInventory().getArmor().getName().equals("Old Clothes")) {
            System.out.print(" (Note : Press the ID of the Equipped Weapon To Unequip It) ==> ");
        } else {
            System.out.print("==> ");
        }
        int selectCase = -1;
        while (selectCase < 0 || getOwnedArmors().length < selectCase) {
            selectCase = s.nextInt();
            if (selectCase < 0 || getOwnedArmors().length < selectCase) {
                System.out.println("Invalid Choice !!! Please Enter Valid Choice !!!");
                System.out.print("==> ");
            }
        }
        if (selectCase != 0) {
            Armor tempA = getOwnedArmors()[selectCase - 1];
            if (tempA == null) {
                System.out.println("You haven't got that item !!!");
            } else if (!tempA.equals(p.getInventory().getArmor())) {
                armorToInv(p, tempA);
            } else {
                System.out.println("-- " + p.getInventory().getArmor().getName() + " Removed from Inventory --");
                p.getInventory().setArmor(new Armor(0, "Old Clothes", 0, 0));
            }
        }

    }

    public static void printArmor(Player p, Armor[] armors) {
        System.out.println("==========");
        Armor currentArmor = p.getInventory().getArmor();
        System.out.println("Armors You Owned :");
        for (Armor a : armors) {
            if (a != null) {
                System.out.print("ID : " + a.getID()
                        + "\tName : " + a.getName()
                        + "\t\tProtection : " + a.getProtection());
                if (a.equals(currentArmor)) {
                    System.out.println(" (EQUİPPED)");
                } else {
                    System.out.println();
                }
            } else {
                System.out.println("----------LOCKED----------");
            }
        }
    }

    public void armorToInv(Player p, Armor tempA) {
        if (!p.getInventory().getWeapon().getName().equals("Old Clothes")) {
            System.out.print("-- " + p.getInventory().getArmor().getName() + " Removed from Inventory --");
        }
        p.getInventory().setArmor(tempA);
        int plainHealth = p.getHealth() - p.getInventory().getArmor().getProtection();
        int health = plainHealth + tempA.getProtection();
        p.setMaxHealth(health);
        p.setHealth(health);
        System.out.println("    ** " + p.getInventory().getArmor().getName() + " Added to Inventory **");
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
