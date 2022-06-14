package modele;

import controleur.Controleur;

public class Player extends Entity {
    private boolean isRunning = false;


    private PlayerInventory playerInventory;


    private CraftInventory craftInventory;

    private boolean isInvicible;

    private int invicibleCooldown;


    public Player(int x, int y, Terrain terrain){
        super(20, 7, new Hitbox(20,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif", terrain);
        playerInventory = new PlayerInventory(50);
        craftInventory = new CraftInventory(9);
        this.isInvicible = false;
        this.invicibleCooldown = 5;
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
            System.out.println(invicibleCooldown);
        } else {
            this.isInvicible = false;
            this.invicibleCooldown = 5;
        }
    }

}
