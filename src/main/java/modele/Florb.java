package modele;

import controleur.Controleur;

import java.util.ArrayList;

public class Florb extends Enemy{



    public Florb(int x, int y, Terrain terrain) {
        super(10, 6, new Hitbox(22,16,x,y, false), 200, terrain, new ArrayList<String>(){
            {
                add("idle");
            }
        });
        this.strength = 3;
        //super(10, 1, new Hitbox(22, 16, x, y), "/Sprites/Enemies/Florb/Florb.gif", 12, 3, terrain, 0, true);
        this.setFlying(true);
    }


    @Override
    public void action() {
        Block b = Controleur.terrain.getBlock(getHitbox().getX().intValue(), getHitbox().getY().intValue()+350);
        if (b != null && b.getTile().getHitbox().isSolid()) {
            moveY(-1);
        }

    }

    public void moveY(int vertDirection) {
        Block b = Controleur.terrain.getBlock(this.getHitbox().getX().intValue(), this.getHitbox().getY().intValue() + this.getSpeed());
        if(b == null || !b.getTile().getHitbox().isSolid()){
            this.getHitbox().setY(this.getHitbox().getY().intValue() + this.getSpeed() * vertDirection);
        }
        else {
            this.getHitbox().setY(this.getHitbox().getY().intValue() - this.getSpeed());
        }
    }


    public void huntingY() {
        Block b = Controleur.terrain.getBlock(getHitbox().getX().intValue(), getHitbox().getY().intValue()+200);
        if (b != null && b.getTile().getHitbox().isSolid()) {
            moveY(-1);
        }
        else {
            moveY(1);
        }
    }

    @Override
    public void attack() {
        this.setSpeed(2);
        if (this.getHitbox().getX().intValue() < Controleur.player.getHitbox().getX().intValue()) {
            this.setIdleDirection(1);
        } else if (this.getHitbox().getX().intValue() > Controleur.player.getHitbox().getX().intValue()) {
            this.setIdleDirection(-1);
        }
        if(this.getAttackCooldown() > 0 && isCanAttack()){
            this.moveY(1);
            moveX(getIdleDirection());
            setAttackCooldown(getAttackCooldown() - 7);
            if ((distanceToPosition(Controleur.player.getHitbox().getX().intValue(), Controleur.player.getHitbox().getY().intValue()) == 0 && !Controleur.player.isInvicible())) {
                Controleur.player.decreaseHealth(2);
                this.setAttackCooldown(50);
            }
            else if(this.getHitbox().getY().intValue() == Controleur.player.getHitbox().getY().intValue()){
                this.setAttackCooldown(0);
            }
            Controleur.player.launchInvicibleCooldown();

        }
        else {
            setCanAttack(false);
            if (getAttackCooldown() < 1000 && !isCanAttack()) {
                setAttackCooldown(getAttackCooldown() + 4);
                this.moveY(-1);
                this.moveX(getIdleDirection());
            } else {
                setCanAttack(true);
            }
        }
    }
}