import java.util.Scanner;

public class Game {
    private Player p;
    private NormalLoc nLoc;
    private BattleLoc bLoc;
    protected Scanner input = new Scanner(System.in);

    Game() {
        this.p = new Player();
        this.nLoc = new SafeHouse(this.p);
    }

    public void start() {
        System.out.println("Welcome to Adventure Island Game !!!");
        System.out.print("Enter Your Name : ");
        Scanner sc = new Scanner(System.in);
        String playerName = sc.nextLine();
        this.p.setPlayerName(playerName);
        System.out.println("Hi " + this.p.getPlayerName() + " !!! You're about to start the game. " +
                "If you can collect 3 special item [water,food,firewood] from places [Cave,Forest,River] , \nYou'll be get out alive from the island." +
                " Now You're gonna choose a character then game'll begin... Good Luck !!!");
        this.p.selectChar();
    }

    public void game() {
        while (true) {
            currentAttributes();
            System.out.println("===== Where do you wanna go ? =====");
            System.out.println("1 : Safe House \n2 : Store \n3 : Cave\n4 : Forest\n5 : River\n6 : Mine (CHALLANGE)");
            if (this.p.getInventory().isFood() && this.p.getInventory().isFirewood() && this.p.getInventory().isWater()) {
                System.out.println("7 : Finish the Game !!!");
            }
            System.out.print("Go ==> ");
            int selectCase = input.nextInt();
            switch (selectCase) {
                case 1 -> {
                    tenLengthLine();
                    this.p.setLocation(new SafeHouse(this.p));
                    this.p.getLocation().onLocation();
                }
                case 2 -> {
                    tenLengthLine();
                    this.p.setLocation(new Store(this.p));
                    this.p.getLocation().onLocation();
                }
                case 3 -> {
                    tenLengthLine();
                    this.p.setLocation(new Cave(this.p));
                    this.p.getLocation().onLocation();
                    deathSituation();
                }
                case 4 -> {
                    tenLengthLine();
                    this.p.setLocation(new Forest(this.p));
                    this.p.getLocation().onLocation();
                    deathSituation();
                }
                case 5 -> {
                    tenLengthLine();
                    this.p.setLocation(new River(this.p));
                    this.p.getLocation().onLocation();
                    deathSituation();
                }
                case 6 -> {
                    tenLengthLine();
                    this.p.setLocation(new Mine(this.p));
                    this.p.getLocation().onLocation();
                    deathSituation();
                }
                case 7 -> {
                    if (this.p.getInventory().isFood() && this.p.getInventory().isFirewood() && this.p.getInventory().isWater()) {
                        System.out.println("Congrats, You've collected all unique item. You've got out the Island. Thanks for the playing :) ");
                        System.exit(0);
                    } else {
                        System.out.println("Playing for second time :) ? You haven't collected all unique item yet !!!");
                    }
                }
                default -> System.out.println("Please Enter Valid Condition !!!");
            }
        }
    }

    public void currentAttributes() {
        System.out.println("\n===== Current Attributes =====");
        System.out.println("Money : " + this.p.getMoney()
                + "\nEquipped Weapon : " + this.p.getInventory().getWeapon().getName() + " (Contributing Damage:+" + this.p.getInventory().getWeapon().getDamage() + ")"
                + "\nEquipped Armor : " + this.p.getInventory().getArmor().getName() + " (Contributing Health:+" + this.p.getInventory().getArmor().getProtection() + ")"
                + "\nTotal Damage : " + this.p.getDamage()
                + "\nTotal Health : " + this.p.getHealth());
        System.out.println("==============================\n");
    }

    public void tenLengthLine() {
        System.out.println("\n==============================");
    }

    public void deathSituation() {
        if (this.p.getHealth() <= 0) {
            System.out.print("You've died :( Try Again ?\nYes : 1\nNo : 2 \nDo ==> ");
            int selectCase = input.nextInt();
            if (selectCase == 1) {
                Game g = new Game();
                System.out.println("====================================================================================");
                g.start();
                g.game();
            } else {
                System.exit(0);
            }
        }
    }
}
