public class River extends BattleLoc{
    public River(Player player) {
        super(player, "River", new Bear(), "Water");
    }

    @Override
    public boolean onLocation() {
        return super.onLocation();
    }

    @Override
    public void endOfLoc(Player p,Obstacle o,int tempSpawnCount) {
        System.out.println("Congrats !!! You've been cleared the " + this.getName() + " !!!");
        if(!p.getInventory().isWater()) {
            System.out.println("You found " + this.getUniqueItem() + " !!! * Added to Inventory *");
            p.getInventory().setWater(true);
            int droppedMoney = o.getMoneyDrop() * tempSpawnCount;
            int balance = p.getInventory().getMoney() + droppedMoney;
            p.getInventory().setMoney(balance);
            System.out.println("You found " + droppedMoney + " Money from Obstacles !!! * Added to Inventory *");
        } else {
            System.out.println("You've cleared " + this.getName() + " before !!!");
            System.out.println("You will not receive any item !!!");
        }
    }
}
