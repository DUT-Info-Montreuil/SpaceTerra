package modele;

import controleur.Controleur;

public class Bib extends Enemy {
    public Bib(int x, int y, Terrain terrain) {
        super(5, 6, new Hitbox(14, 18, x, y), "/Sprites/Enemies/Bib/BibIdle.gif", 8, 1, terrain, 5);
    }

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        /*
        int rangeMultiplier;

        if (this.isPlayerDetected())
            rangeMultiplier = 2;

        else
            rangeMultiplier = 1;

        this.detectPlayer(player, rangeMultiplier);

         */
        this.detectPlayer(player, getRangeMultiplier());

       // System.out.println(getState());

        if (this.getState() == 0) {
            this.setRangeMultiplier(1);
            idle();
        } else {
            this.setRangeMultiplier(2);
            if (this.getState() == 1) {
                hunting();
            } else {
                attack();
            }
        }
        // System.out.println(this.getState());


         /*   case "hunting":

                if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 112) {
                    if (leftCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                } else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 112) {
                    if (rightCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                } else
                    this.setState("attack");
                break;

            case "attack":
                this.setSpeed(12);

                if (this.isGrounded() && !isJumping()) {
                    this.setGravity(5);
                    this.jump();
                } else if (isJumping())
                    this.jump();

                if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 6 && this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() - 112) {
                    this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                } else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 6 && this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() + 112) {
                    this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                } else {
                    stopJump();
                    this.setState("hunting");
                }
                break;

            default:
                break;
        }*/

    }


    @Override
    public void idle() {
       // System.out.println(getIdleDirection());
        if (getIdleCooldown() > 0 && isCanMove()) {
            move(getIdleDirection());
            if (getIdleDirection() == -1) {
                if (this.sideLeftCollision()) {
                    jump();
                }

            } else if (getIdleDirection() == 1) {
                if (this.sideRightCollisions()) {
                    jump();
                }
            } else {
                this.setIdleDirection(this.proba());
            }
        } else {
            this.setIdleDirection(0);
            setCanMove(false);
            if (getIdleCooldown() < 500 && !isCanMove()) {
                setIdleCooldown(getIdleCooldown() + 1);
            } else {
                setCanMove(true);
            }
        }
    }

    public void move(int direction) {
        this.setJumpHeight(5);
        // if (this.getIdleCooldown() <= 50 && this.isCanMove()) {
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
            } else {
                jump();
            }
        } else {
            if (!sideLeftCollision()) {
                move(-1);
            } else {
                jump();
            }
        }
    }


    @Override
    public void attack() {
        if (this.getAttackCooldown() > 0 && isCanAttack()) {

            setAttackCooldown(getAttackCooldown() - 10);
            if (this.isGrounded() && !isJumping()) {
                this.jump();
            }
            else if (isJumping()){
                if(distanceToPosition(Controleur.player.getHitbox().getX().intValue(), Controleur.player.getHitbox().getY().intValue()) == 0 && !Controleur.player.isInvicible()){
                    Controleur.player.decreaseHealth(1);
                }
                Controleur.player.launchInvicibleCooldown();
                this.jump();
            }
            move(getIdleDirection());
        } else {
            setCanAttack(false);
            if (getAttackCooldown() < 1000 && !isCanAttack()) {
                setAttackCooldown(getAttackCooldown() + 5);
            } else {
                setCanAttack(true);
            }
            if (this.getHitbox().getX().intValue() < Controleur.player.getHitbox().getX().intValue()) {
                this.setIdleDirection(1);
            }
            else if (this.getHitbox().getX().intValue() > Controleur.player.getHitbox().getX().intValue()){
                this.setIdleDirection(-1);
            }
        }
    }
}
