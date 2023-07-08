import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Snake extends Obstacle{
    private Armor a;
    private Weapon w;
    protected static Random r = new Random();
    protected static Scanner sc = new Scanner(System.in);
    public Snake() {
        super(4,"Snake",0,30,0,12);

        int minDamage = 5;
        int maxDamage = 10;
        this.setDamage(r.nextInt(maxDamage - minDamage + 1) + minDamage);
    }

    public static String[] luckValueArray() {
        String[] luck = new String[10];
        int weaponLuck = 2;
        int armorLuck = 2;
        int moneyLuck = 1;
        int voidLuck = 5;
        int index = 0;
        for(int i = 0 ; i < weaponLuck ; i++) {
            luck[index] = "w";
            index++;
        }
        for (int j = 0 ; j < armorLuck ; j++) {
            luck[index] = "a";
            index++;
        }
        for (int k = 0 ; k < moneyLuck ; k++) {
            luck[index] = "m";
            index++;
        }
        for(int p = 0 ; p < voidLuck ; p++) {
            luck[index] = " ";
            index++;
        }
        return luck;
    }

    public static int[] luckValueWeapon() {
        int[] luckWeapon = new int[10];
        int firstWeaponLuck = 5;
        int secondWeaponLuck = 3;
        int thirdWeaponLuck = 2;
        int index = 0;
        for(int i = 0 ; i < firstWeaponLuck ; i++) {
            luckWeapon[index] = 1;
            index++;
        }
        for (int j = 0 ; j < secondWeaponLuck ; j++) {
            luckWeapon[index] = 2;
            index++;
        }
        for (int k = 0 ; k < thirdWeaponLuck ; k++) {
            luckWeapon[index] = 3;
            index++;
        }
        return luckWeapon;
    }

    public static Weapon takeWeapon() {
        int first = 0;
        int last = luckValueArmor().length - 1;
        int luckyNumber = r.nextInt(last - first + 1);
        int itemIndex = luckValueWeapon()[luckyNumber] - 1;
        System.out.println(Weapon.weapons()[itemIndex].getName());
        return Weapon.weapons()[itemIndex];
    }



    public static int[] luckValueArmor() {
        int[] luckArmor = new int[10];
        int firstArmorLuck = 5;
        int secondArmorLuck = 3;
        int thirdArmorLuck = 2;
        int index = 0;
        for(int i = 0 ; i < firstArmorLuck ; i++) {
            luckArmor[index] = 1;
            index++;
        }
        for (int j = 0 ; j < secondArmorLuck ; j++) {
            luckArmor[index] = 2;
            index++;
        }
        for (int k = 0 ; k < thirdArmorLuck ; k++) {
            luckArmor[index] = 3;
            index++;
        }
        return luckArmor;
    }

    public static Armor takeArmor() {
        int first = 0;
        int last = luckValueArmor().length - 1;
        int luckyNumber = r.nextInt(last - first + 1);
        int itemIndex = luckValueArmor()[luckyNumber] - 1;
        return Armor.armors()[itemIndex];
    }

    public static int takeMoney() {
        int first = 1;
        int last = 2;
        int luckyNumber = r.nextInt(last - first + 1) + first;
        luckyNumber = (luckyNumber == 1) ? 5 : 10;
        System.out.println(luckyNumber);
        return luckyNumber;
    }







    public Armor getA() {
        return a;
    }

    public void setA(Armor a) {
        this.a = a;
    }

    public Weapon getW() {
        return w;
    }

    public void setW(Weapon w) {
        this.w = w;
    }

    public Random getR() {
        return r;
    }

}
