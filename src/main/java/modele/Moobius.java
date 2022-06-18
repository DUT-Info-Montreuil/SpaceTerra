package modele;

import controleur.Controleur;

public class Moobius extends Animal{
    public Moobius(Terrain terrain,int x, int y) {
        super(20, 5, new Hitbox(39,31,x,y), "/Sprites/Mobs/Moobius/moobiusIdle.gif", terrain);
    }

    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        switch (this.getDirection()) {
            case 0:
                this.setDirection(Controleur.randomNum(1, 3));
                break;

            case 1:
                if (leftCheck && this.getIdleCooldown() <= 50 && this.isCanMove()) {
                    this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                    this.setIdleCooldown(this.getIdleCooldown() - 1);

                    if (this.getIdleCooldown() == 0) {
                        this.setCanMove(false);
                        this.setIdleCooldown(this.getIdleCooldown() + 1);
                    }
                } else if (this.getIdleCooldown() == 0) {
                    this.setCanMove(false);
                    this.setIdleCooldown(this.getIdleCooldown() + 1);
                } else if (!isCanMove() && getIdleCooldown() == 50) {
                    setCanMove(true);
                    this.setDirection(0);
                } else
                    this.setIdleCooldown(this.getIdleCooldown() + 1);
                break;

            case 2:
                if (rightCheck && this.getIdleCooldown() <= 50 && this.isCanMove()) {
                    this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                    this.setIdleCooldown(this.getIdleCooldown() - 1);

                    if (this.getIdleCooldown() == 0) {
                        this.setCanMove(false);
                        this.setIdleCooldown(this.getIdleCooldown() + 1);
                    }
                }

                else if (!isCanMove() && getIdleCooldown() == 50) {
                    setCanMove(true);
                    this.setDirection(0);
                } else
                    this.setIdleCooldown(this.getIdleCooldown() + 1);
                break;

            case 3:
                if (this.isCanMove()) {
                    this.setIdleCooldown(this.getIdleCooldown() - 1);
                    if (this.getIdleCooldown() == 0) {
                        this.setCanMove(false);
                        this.setIdleCooldown(this.getIdleCooldown() + 1);
                    }
                } else if (!isCanMove() && getIdleCooldown() == 50) {
                    setCanMove(true);
                    this.setDirection(0);
                } else
                    this.setIdleCooldown(this.getIdleCooldown() + 1);

                break;
        }
    }
}
