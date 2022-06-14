package modele;

import controleur.Controleur;

public class Bib extends Enemy {
    public Bib(int x, int y, Terrain terrain) {
        super(5, 6, new Hitbox(14, 18, x, y), "/Sprites/Enemies/Bib/BibIdle.gif", 8, 1, terrain, 5);
    }


    @Override
    public void idle() {
        if (getIdleCooldown() > 0 && isCanMove()) {
            move(getIdleDirection());
            if (getIdleDirection() == -1) {
                if(this.sideLeftCollision()) {
                    jump();
                }

            } else if (getIdleDirection() == 1) {
                if(this.sideRightCollisions()){
                    jump();
                }
                }
             else {
                this.setIdleDirection(this.proba());
            }
        } else {
            setIdleDirection(0);
            setCanMove(false);
            if (getIdleCooldown() < 50 && !isCanMove()) {
                setIdleCooldown(getIdleCooldown() + 1);
            } else {
                System.out.println(getIdleCooldown());
                setCanMove(true);
            }
        }
    }

    public void move(int direction) {
        this.getHitbox().setX(this.getHitbox().getX().intValue() + (this.getSpeed()) * direction);
        if (this.getState() == 0) {
            this.setIdleCooldown(this.getIdleCooldown() - 1);
        }

    }


    @Override
    public void hunting() {
        if (this.getHitbox().getX().intValue() < Controleur.player.getHitbox().getX().intValue()) {
            if (!sideRightCollisions()) {
                move(1);
           }
        } else {
            if (!sideLeftCollision()) {
                move(-1);
            }
        }
        this.jump();
    }

    @Override
    public void attack() {
        if (this.isGrounded() && !isJumping()) {
            this.setGravity(5);
            this.jump();
        } else if (isJumping())
            this.jump();
    }
}
