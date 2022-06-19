package modele;

import java.util.ArrayList;

import static modele.Environment.player;

public class Bib extends Enemy{

    public Bib(int x, int y, Terrain terrain) {
        super(5, 6, new Hitbox(14, 18, x, y, false), 8, 1, terrain, 5, false, new ArrayList<String>(){
            {
                add("idle");
                add("walk");
            }
        });
    }

    @Override
    public void action() {
        jump();
    }

    @Override
    public void attack() {
        if (this.getAttackCooldown() > 0 && isCanAttack()) {
            setAttackCooldown(getAttackCooldown() - 20);
            if (this.isGrounded() && !isJumping()) {
                this.jump();
            } else if (isJumping()) {
                if (distanceToPosition((int)player.getHitbox().getX(), (int)player.getHitbox().getY()) == 0 && !player.isInvicible()) {
                    player.decreaseHealth(3);
                }
                player.launchInvicibleCooldown();
                this.jump();
            }
            if(getIdleDirection() == 1 && !sideRightCollisions() && this.getHitbox().getX() <= this.getTerrain().getWidth() * 32 - 30){
                moveX(1);
            }
            else if (getIdleDirection() == -1 && !sideLeftCollision() && this.getHitbox().getX() >= 10){
                moveX(-1);
            }
        } else {
            setCanAttack(false);
            if (getAttackCooldown() < 1000 && !isCanAttack()) {
                setAttackCooldown(getAttackCooldown() + 5);
            } else {
                setCanAttack(true);
            }
            if (this.getHitbox().getX() < player.getHitbox().getX()) {
                this.setIdleDirection(1);
            } else if (this.getHitbox().getX() > player.getHitbox().getX()) {
                this.setIdleDirection(-1);
            }
        }
    }

    @Override
    public void huntingY() {

    }
}
