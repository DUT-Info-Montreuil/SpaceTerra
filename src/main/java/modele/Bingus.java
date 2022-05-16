package modele;

public class Bingus extends Entite{

    private int force;

    public Bingus(int x, int y) {
        super(10, 5, new Hitbox(50,50,x,y), "/Sprites/Enemies/YinYang.png");
        this.force = 3;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void deplacement(Player player, boolean leftCheck, boolean rightCheck) {
        if(this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue()){
            if (leftCheck)
                this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getVitesse());
        }

        else if(this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue()){
            if(rightCheck)
                this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getVitesse());
        }
    }


}
