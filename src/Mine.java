public class Mine extends BattleLoc {
    public Mine(Player player) {
        super(player, "Mine", new Snake(),"Unique Armor");
    }

    @Override
    public boolean checkSituation(Player p, Obstacle o, int roundCounter, int obstacleCounter, int tempSpawnCount) {
        if (checkHealth(p, o)) {
            if (p.getHealth() == 0) {
                roundSummary(roundCounter, p, o);
                this.setDead(true);
                return true;
            } else {
                roundSummary(roundCounter, p, o);
                luckyItemGenerator(this.getPlayer());
                if (tempSpawnCount != obstacleCounter) {
                    isContinueSituation();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void endOfLoc(Player p,Obstacle o,int tempSpawnCount) {
        System.out.println("Congrats !!! , You've been cleared the " + this.getName() + " !!!");
        System.out.println("You found " + this.getUniqueItem() + " !!! * Added to Inventory *");
        p.getInventory().setArmor(Armor.uniqueArmor());
    }

    public static void luckyItemGenerator(Player p) {
        int first = 0;
        int last = Snake.luckValueArray().length - 1;
        int luckyNumber = Snake.r.nextInt(last - first + 1) + first;
        String s = Snake.luckValueArray()[luckyNumber];
        switch (s) {
            case " " -> System.out.println("Unlucky no item dropped from Snake");
            case "w" -> {
                Weapon tempWeapon = Snake.takeWeapon();
                System.out.println("Snake dropped " + tempWeapon.getName());
                System.out.print("Do You Want To Equip It ? YES:1  NO:2 ==> ");
                int selectCase = Snake.sc.nextInt();
                switch (selectCase) {
                    case 1:
                        if(SafeHouse.getOwnedWeapons()[tempWeapon.getID() - 1] == null) {
                            equipWeapon(p, tempWeapon);
                        } else {
                            System.out.println("You've already got " + tempWeapon.getName() + " !!!");
                        }
                        break;
                    case 2:
                        break;
                }
            }
            case "a" -> {
                Armor tempArmor = Snake.takeArmor();
                System.out.println("Snake dropped " + tempArmor.getName());
                System.out.print("Do You Want To Equip It ? YES:1  NO:2 ==> ");
                int selectCase = Snake.sc.nextInt();
                switch (selectCase) {
                    case 1:
                        equipArmor(p, tempArmor);
                        break;
                    case 2:
                        break;
                }
            }
            default -> {
                int tempMoney = Snake.takeMoney();
                System.out.println("Snake dropped " + tempMoney + " Money");
                equipMoney(p, tempMoney);
            }
        }
    }

    public static void equipWeapon(Player p, Weapon w) {
        System.out.print("-- " + p.getInventory().getWeapon().getName() + " removed from Inventory --   ");
        p.getInventory().setWeapon(w);
        System.out.println("++ " + p.getInventory().getWeapon().getName() + " added to Inventory ++");
        SafeHouse.getOwnedWeapons()[w.getID() - 1] = w;
    }

    public static void equipArmor(Player p, Armor a) {
        System.out.print("-- " + p.getInventory().getArmor().getName() + " removed from Inventory --   ");
        p.getInventory().setArmor(a);
        System.out.println("++ " + p.getInventory().getArmor().getName() + " added to Inventory ++");
    }

    public static void equipMoney(Player p, int m) {
        System.out.println("** " + m + " Money added to Inventory **");
        int newMoney = p.getMoney() + m;
        p.setMoney(newMoney);
    }

    @Override
    public int enemySpawnCount() {
        int min = 5;
        int max = 10;
        return random.nextInt(max - min + 1) + min;
    }



}
