package modele;

public class Player extends Entity {
    private boolean isRunning = false;
    private PlayerInventory playerInventory;

    private boolean isInvicible;

    private int invicibleCooldown;
    private CraftInventory craftInventory;


    public Player(int x, int y, Terrain terrain) {
        super(20, 7, new Hitbox(20, 38, x, y), "/Sprites/MC/MCSpace_Idle_right.gif", terrain, 20, false);
        playerInventory = new PlayerInventory(50);
        craftInventory = new CraftInventory(9);
        this.isInvicible = false;
        this.invicibleCooldown = 10;
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if(leftCheck){
            if (this.getHitbox().getX().getValue() >= 10) {
                if (!sideLeftCollision())
                    getHitbox().setX(this.getHitbox().getX().intValue() - getSpeed());

                else if (canClimbLeft())
                    climb(2);
            }

        }

        if(rightCheck){
            if (this.getHitbox().getX().getValue() <= this.getTerrain().getWidth() * 32 - 30) {
                if (!sideRightCollisions())
                    getHitbox().setX(this.getHitbox().getX().intValue() + getSpeed());

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

    public void mourrir(){

    }

}
