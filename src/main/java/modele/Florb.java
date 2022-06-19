package modele;


import java.util.ArrayList;

import static modele.Environment.player;
import static modele.Environment.terrain;

public class Florb extends Enemy{


    public Florb(int x, int y, Terrain terrain) {
        super(10, 1, new Hitbox(22, 16, x, y, false), 12, 3, terrain, 0, true, new ArrayList<String>(){
            {
                add("idle");
            }
        });
        this.setFlying(true);
    }


    @Override
    public void action() {
        Block b = terrain.getBlock((int)getHitbox().getX(), (int)getHitbox().getY() + 350);
        if (b != null && b.getHitbox().isSolid()) {
            moveY(-1);
        }

    }

    public void moveY(int vertDirection) {
        Block b = terrain.getBlock((int)this.getHitbox().getX(), (int)this.getHitbox().getY() + this.getSpeed());
        if(b == null || !b.getHitbox().isSolid()){
            this.getHitbox().setY(this.getHitbox().getY()+ this.getSpeed() * vertDirection);
        }
        else {
            this.getHitbox().setY(this.getHitbox().getY() - this.getSpeed());
        }
    }


    public void huntingY() {
        Block b = terrain.getBlock((int)getHitbox().getX(), (int)getHitbox().getY() + 200);
        if (b != null && b.getHitbox().isSolid()) {
            moveY(-1);
        }
        else {
            moveY(1);
        }
    }

    @Override
    public void attack() {
        this.setSpeed(2);
        if (this.getHitbox().getX() < player.getHitbox().getX()) {
            this.setIdleDirection(1);
        } else if (this.getHitbox().getX() > player.getHitbox().getX()) {
            this.setIdleDirection(-1);
        }
        if(this.getAttackCooldown() > 0 && isCanAttack()){
            this.moveY(1);
            moveX(getIdleDirection());
            setAttackCooldown(getAttackCooldown() - 7);
            if ((distanceToPosition((int)player.getHitbox().getX(), (int)player.getHitbox().getY()) == 0 && !player.isInvicible())) {
                player.decreaseHealth(2);
                this.setAttackCooldown(50);
            }
            else if(this.getHitbox().getY() == player.getHitbox().getY()){
                this.setAttackCooldown(0);
            }
            player.launchInvicibleCooldown();
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