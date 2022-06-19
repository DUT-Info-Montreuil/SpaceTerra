package modele;

import java.util.ArrayList;

public class Player extends Entity {
    private boolean isRunning = false;
    private PlayerInventory playerInventory;

    private boolean isInvicible;

    private int invicibleCooldown;
    private CraftInventory craftInventory;


    public Player(int x, int y, Terrain terrain){
        super(20, 7, new Hitbox(20,38,x,y, false), terrain, 20, false, new ArrayList<String>(){
            {
                add("idle");
                add("walk");
            }
        });
        //super(20, 7, new Hitbox(20, 38, x, y), "/Sprites/MC/MCSpace_Idle_right.gif", terrain, 20, false);
        playerInventory = new PlayerInventory(50);
        craftInventory = new CraftInventory(9);
        this.isInvicible = false;
        this.invicibleCooldown = 10;
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if(leftCheck){
            if (this.getHitbox().getX() >= 10) {
                if (!sideLeftCollision())
                    getHitbox().setX(this.getHitbox().getX() - getSpeed());

                else if (canClimbLeft())
                    climb(2);
            }
        }

        if(rightCheck){
            if (this.getHitbox().getX() <= this.getTerrain().getWidth() * getTerrain().getTileWidth() - 30) {
                if (!sideRightCollisions())
                    getHitbox().setX(this.getHitbox().getX() + getSpeed());

                else if (canClimbRight())
                    climb(1);
            }
        }
    }
    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height


    public Item drop() {
        return this.playerInventory.removeFromCurrSlot();
    }

    public void pick(Item item) {
        this.playerInventory.addIntoNextEmptySlot(item);
    }

    public void pick(Item item, int quantity) {
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
        if(isRunning){
            setSpeed(14);
        }
        else{
            setSpeed(7);
        }
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
                if(damage > 0){
                    b.setHealth(b.getHealth() - damage);
                    if (getTerrain().checkDestroyedBlock(b) && b.getRessourceAsItem() != null && !this.getPlayerInventory().isInventoryFull()) {
                        this.pick(b.getRessourceAsItem());
                    }
                }
            }
        }
    }
    public boolean isInvicible() {
        return isInvicible;
    }

    public void setInvicible(boolean invicible) {
        isInvicible = invicible;
    }

    public void launchInvicibleCooldown() {
        if (this.invicibleCooldown > 0) {
            isInvicible = true;
            invicibleCooldown--;
           // System.out.println(invicibleCooldown);
        } else {
            this.isInvicible = false;
            this.invicibleCooldown = 10;
        }
    }

    public void checkDie(){
        if(this.getHealth().getValue() <= 0){
            this.getHitbox().setX(2500);
            this.getHitbox().setY(2030);
            this.setHealth(20);
        }

    }

}
