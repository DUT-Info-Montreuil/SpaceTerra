package modele;

public class Bingus extends Entity {

    private int strenght;

    public Bingus(int x, int y) {
        super(10, 5, new Hitbox(50,50,x,y), "/Sprites/Enemies/YinYang.png");
        this.strenght = 3;
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if(this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue()){
            if (leftCheck)
                this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
        }

        else if(this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue()){
            if(rightCheck)
                this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
        }
    }


}
