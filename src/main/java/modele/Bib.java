package modele;

import controleur.Controleur;

public class Bib extends Enemy{

    private int strength;

    public Bib(int x, int y, Terrain terrain) {
        super(5, 6, new Hitbox(14,18, x, y), "/Sprites/Enemies/Bib/BibIdle.gif", 250, terrain);
        this.strength = 1;
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        int range = this.getRange();
        int rangeMultiplier;

        if (this.isPlayerDetected())
            rangeMultiplier = 2;

        else
            rangeMultiplier = 1;

        this.detectPlayer(player, rangeMultiplier);

        switch(this.getState()) {
            case "idle":
                switch (this.getIdleDirection()) {
                    case 0:
                        this.setIdleDirection(Controleur.randomNum(1, 3));
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
                            this.setIdleDirection(0);
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
                        } else if (!isCanMove() && getIdleCooldown() == 50) {
                            setCanMove(true);
                            this.setIdleDirection(0);
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
                            this.setIdleDirection(0);
                        } else
                            this.setIdleCooldown(this.getIdleCooldown() + 1);
                }
                break;

            case "hunting":
                if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 100) {
                    if (leftCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                } else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 100) {
                    if (rightCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                }

                else
                    this.setState("attack");
                break;

            case "attack":

                if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 50) {

                    if(this.isGrounded()){
                        this.setGravity(5);
                        this.jump();
                    }

                    this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                }

                else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 50) {

                    if(this.isGrounded()){
                        this.setGravity(5);
                        this.jump();
                    }


                    this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                }

                else{
                    if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 5)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());

                    else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 5)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                }


                if(!this.isJumping())
                    this.setState("hunting");
                break;

            default:
                break;
        }
    }
}
