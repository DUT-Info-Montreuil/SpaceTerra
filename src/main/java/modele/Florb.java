package modele;

import controleur.Controleur;

public class Florb extends Enemy {


    public Florb(int x, int y, Terrain terrain) {
        super(10, 1, new Hitbox(22, 16, x, y), "/Sprites/Enemies/Florb/Florb.gif", 12, 3, terrain, 0, true);
        this.setFlying(true);
    }


    @Override
    public void action() {
        Block b = Controleur.terrain.getBlock(getHitbox().getX().intValue(), getHitbox().getY().intValue()+350);
        if (b != null) {
            moveY(-1);
        }

    }

    public void moveY(int vertDirection) {
       this.getHitbox().setY(this.getHitbox().getY().intValue() + this.getSpeed() * vertDirection);
    }


    public void huntingY() {
        if(this.getHitbox().getY().intValue() < Controleur.player.getHitbox().getY().intValue()){
            Block b = Controleur.terrain.getBlock(getHitbox().getX().intValue(), getHitbox().getY().intValue()+64);
            if(b == null){
                moveY(1);
            }
            else {
                moveY(-1);
            }
        }
    }

    @Override
    public void attack() {

    }
}

