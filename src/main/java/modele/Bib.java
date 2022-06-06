package modele;

import controleur.Controleur;

import java.util.ArrayList;

public class Bib extends Enemy{

    private int strength;

    public Bib(int x, int y, Terrain terrain) {
        super(5, 6, new Hitbox(14,18, x, y),250, terrain, new ArrayList<String>(){
            {
                add("idle");
                add("walk");
                add("attack");
            }
        });
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
                this.setSpeed(6);
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
                this.setSpeed(6);

                if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 112) {
                    if (leftCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                }

                else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 112) {
                    if (rightCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                }

                else
                    this.setState("attack");
                break;

            case "attack":
                this.setSpeed(12);

                if(this.isGrounded() && !isJumping()){
                    this.setGravity(5);
                    this.jump();
                }

                else if(isJumping())
                    this.jump();

                if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 6 && this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() - 112) {
                    this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                }

                else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 6 && this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() + 112) {
                    this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                }

                else{
                    stopJump();
                    this.setState("hunting");
                }
                break;

            default:
                break;
        }
    }
}
