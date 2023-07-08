import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String uniqueItem;
    private boolean isDead;
    private boolean isContinue;
    protected Random random = new Random();

    public BattleLoc(Player player, String name, Obstacle obstacle, String uniqueItem) {
        super(player, name);
        this.obstacle = obstacle;
        this.uniqueItem = uniqueItem;
        this.isDead = false;
        this.isContinue = true;
    }

    @Override
    public boolean onLocation() {
        int tempSpawnCount = enemySpawnCount();
        System.out.println("You have been entered the " + getName() + " !!!");
        System.out.println(tempSpawnCount + " " + obstacle.getName() + " here !!!");
        System.out.print("What are you gonna do :" +
                "\n1 - Fight" +
                "\n2 - Run Away" +
                "\nDo ==> ");
        int selectCase = input.nextInt();
        switch (selectCase) {
            case 1:
                combat(tempSpawnCount, this.getPlayer(), this.obstacle);
                break;
            case 2:
                this.isContinue = false;
                break;
        }
        endSituation(tempSpawnCount);
        return isDead();
    }

    public void combat(int tempSpawnCount, Player p, Obstacle o) {
        int roundCounter = 0;
        int obstacleCounter = 0;
        for (int i = tempSpawnCount; 0 < i; i--) {
            if (this.isDead() || !this.isContinue()) break;
            o.setHealth(o.getMaxHealth());
            obstacleCounter++;
            System.out.println("==========");
            System.out.println(obstacleCounter + ". " + o.getName() + "'s approching to you...");
            if (whoFirst()) {
                System.out.println("You'll start to attack !");
                while (0 < p.getHealth() && 0 < o.getHealth()) {
                    roundCounter++;
                    hit(p, o);
                    if (checkSituation(p,o,roundCounter,obstacleCounter,tempSpawnCount)) break;
                    hit(o, p);
                    if (checkSituation(p,o,roundCounter,obstacleCounter,tempSpawnCount)) break;
                    roundSummary(roundCounter, p, o);
                    isContinueSituation();
                    if (!this.isContinue) {
                        break;
                    }
                }
            } else {
                System.out.println(o.getName() + "'ll start to attack !");
                while (0 < p.getHealth() && 0 < o.getHealth()) {
                    roundCounter++;
                    hit(o, p);
                    if (checkSituation(p,o,roundCounter,obstacleCounter,tempSpawnCount)) break;
                    hit(p, o);
                    if (checkSituation(p,o,roundCounter,obstacleCounter,tempSpawnCount)) break;
                    roundSummary(roundCounter, p, o);
                    isContinueSituation();
                    if (!this.isContinue) {
                        break;
                    }
                }
            }
        }
    }

    public int enemySpawnCount() {
        int min = 1;
        int max = 3;
        return random.nextInt(max - min + 1) + min;
    }

    public boolean whoFirst() {
        int min = 1;
        int max = 2;
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber == 1;
    }

    public void hit(Obstacle o, Player p) {
        int newHealth = p.getHealth() - o.getDamage();
        p.setHealth(newHealth);
        System.out.println(o.getName() + " hit : " + o.getDamage());
    }

    public void hit(Player p, Obstacle o) {
        int newHealth = o.getHealth() - p.getDamage();
        o.setHealth(newHealth);
        System.out.println(p.getName() + " hit : " + p.getDamage());
    }

    public void roundSummary(int roundNumber, Player p, Obstacle o) {
        System.out.println("==========");
        System.out.println(roundNumber + ". Round Summary : ");
        if(0 < p.getHealth()) {
            System.out.println(p.getName() + "'s Health : " + p.getHealth());
        } else {
            System.out.println(p.getName() + "'s DEAD");
        }
        if (0 < o.getHealth()) {
            System.out.println(o.getName() + "'s Health : " + o.getHealth());
        } else {
            System.out.println(o.getName() + "'s DEAD ");
        }
        System.out.println("==========");
    }

    public void isContinueSituation() {
        System.out.println("-----");
        System.out.println("What're you gonna do ? : ");
        System.out.print("1 - Continue \n2 - Run Away\nDo ==> ");
        int selectCase = input.nextInt();
        System.out.println("-----");
        switch (selectCase) {
            case 1:
                break;
            case 2:
                this.isContinue = false;
                break;
        }
    }

    public void endSituation (int tempSpawnCount) {
        if (!this.isContinue()) {
            System.out.println("You've got away !!!");
        } else if (!this.isDead()) {
            endOfLoc(this.getPlayer(),this.obstacle,tempSpawnCount);
        }
    }

    public void endOfLoc(Player p,Obstacle o,int tempSpawnCount) {
        System.out.println("Congrats !!! , You've been cleared the " + this.getName() + " !!!");
        System.out.println("You found " + this.getUniqueItem() + " !!! * Added to Inventory *");
        int droppedMoney = o.getMoneyDrop() * tempSpawnCount;
        int balance = p.getMoney() + droppedMoney;
        p.setMoney(balance);
        System.out.println("You found " + droppedMoney + " Money from Obstacles !!! * Added to Inventory *");
    }

    public boolean checkHealth(Player p, Obstacle o) {
        return p.getHealth() <= 0 || o.getHealth() <= 0;
    }

    public boolean checkSituation(Player p,Obstacle o,int roundCounter,int obstacleCounter,int tempSpawnCount) {
        if (checkHealth(p, o)) {
            if (p.getHealth() == 0) {
                roundSummary(roundCounter,p,o);
                this.setDead(true);
                return true;
            } else {
                roundSummary(roundCounter, p, o);
                if(tempSpawnCount != obstacleCounter) {
                    isContinueSituation();
                }
                return true;
            }
        }
        return false;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getUniqueItem() {
        return uniqueItem;
    }

    public void setUniqueItem(String uniqueItem) {
        this.uniqueItem = uniqueItem;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isContinue() {
        return isContinue;
    }

    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }
}



