package modele;

public class Player extends Entity {
    private boolean isRunning = false;


    private Inventory inventory;


    private Inventory craftInventory;


    public Player(int x, int y, Terrain terrain){
        super(20, 7, new Hitbox(20,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif", terrain);
        inventory = new Inventory(50);
        craftInventory = new Inventory(9);
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if(this.getHitbox().getX().getValue() >= 10){
            if (leftCheck) {
                if(!sideLeftCollision()){
                    getHitbox().setX(this.getHitbox().getX().intValue() - getSpeed());
                }
            }
        }

        if(this.getHitbox().getX().getValue() <= this.getTerrain().getWidth()*32-30){
            if (rightCheck){
                if(!sideRightCollisions()){
                    getHitbox().setX(this.getHitbox().getX().intValue() + getSpeed());
                }
            }
        }

    }
    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height


    public Item drop(){
        return this.inventory.removeFromCurrSlot();
    }

    public void pick(Item item){
        this.inventory.addIntoNextEmptySlot(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Inventory getCraftInventory() {
        return craftInventory;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Item verifCraft(){
        if(craftInventory.getSlot(0).getItem().getTypeItem().equals(new ItemBlock(0).getTypeItem()) && craftInventory.getSlot(1).getItem().getTypeItem().equals(new ItemBlock(0).getTypeItem()) && craftInventory.getSlot(2).getItem().getTypeItem().equals(new ItemBlock(1).getTypeItem()) && craftInventory.getSlot(3).getItem().getTypeItem().equals(new ItemBlock(1).getTypeItem()) && craftInventory.getSlot(4).getItem() == null && craftInventory.getSlot(5).getItem() == null && craftInventory.getSlot(6).getItem() == null && craftInventory.getSlot(7).getItem() == null &&  craftInventory.getSlot(8).getItem().getTypeItem().equals(new ItemBlock(1).getTypeItem())){
            return new ItemBlock(1);
        }
        return null;
    }
}
