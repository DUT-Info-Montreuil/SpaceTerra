package modele;

public class Player extends Entity {

    private final double walkSpeed = 10;
    private final double gravite = 9.81;


    public Player(int x,int y){
        super(20, 10, new Hitbox(24,38,x,y),"/Sprites/MC/MCSpace_Idle_right.gif");
    }

    public void movement(Player player, boolean left, boolean right) {
        if (left) {
            getHitbox().setX(this.getHitbox().getX().intValue() - walkSpeed);
        }
        else if (right){
            getHitbox().setX(this.getHitbox().getX().intValue() + walkSpeed);
        }
    }
    // haut du block = block.getHitY(); bas du block = block.getHitY() + block.getTile().getHitbox().getHeight()
    // haut du personnage = yProperty.intValue(); bas du personnage = yProperty.intValue() + height
}
