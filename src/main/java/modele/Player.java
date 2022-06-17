package modele;

public class Player extends Entity {
    private boolean isRunning = false;


    private PlayerInventory playerInventory;


    private CraftInventory craftInventory;


    public Player(int x, int y, Terrain terrain){
        super(20, 7, new Hitbox(20,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif", terrain);
        playerInventory = new PlayerInventory(50);
        craftInventory = new CraftInventory(9);
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if(this.getHitbox().getX().getValue() >= 10){
            if (leftCheck) {
                if(!sideLeftCollision())
                    getHitbox().setX(this.getHitbox().getX().intValue() - getSpeed());

                else if(grimpableLeft())
                    grimper(2);
            }
        }

        if(this.getHitbox().getX().getValue() <= this.getTerrain().getWidth()*32-30){
            if (rightCheck){
                if(!sideRightCollisions())
                    getHitbox().setX(this.getHitbox().getX().intValue() + getSpeed());

                else if(grimpableRight())
                    grimper(1);

            }
        }

    }
    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height


    public Item drop(){
        return this.playerInventory.removeFromCurrSlot();
    }

    public void pick(Item item){
        this.playerInventory.addIntoNextEmptySlot(item);
    }

    public void pick(Item item, int quantity){
        this.playerInventory.addIntoNextEmptySlot(item, quantity);
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public CraftInventory getCraftInventory() {
        return craftInventory;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void breakBlock(int x, int y, int pickPow, int axePow) {
        Block b = getTerrain().getBlock(x, y);
        if (b != null) {
            if (getTerrain().checkDistanceBlock(this, b)) {
                int damage;
                if(pickPow - b.getPickDef() > axePow - b.getAxeDef())
                    damage = pickPow - b.getPickDef();
                else
                    damage = axePow - b.getAxeDef();
                System.out.println(damage + ", pick"+ b.getPickDef() + ", axe" + b.getAxeDef());
                if(damage > 0){
                    b.setHealth(b.getHealth() - damage);
                    if (getTerrain().checkDestroyedBlock(b) && b.getRessourceAsItem() != null && !this.getPlayerInventory().isInventoryFull()) {
                        this.pick(b.getRessourceAsItem());
                    }
                }
            }
        }
    }
}
