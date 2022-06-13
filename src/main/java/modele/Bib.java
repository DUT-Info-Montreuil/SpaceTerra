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

        if (this.getState() == 0) {
            this.setRangeMultiplier(1);
            idle();
        } else {
            this.setRangeMultiplier(2);
            if (this.isJumping() && !this.isGrounded()) {
                this.jump();
            }
            /*else {
                this.stopJump();
            }*/
            // else {
            if (this.getState() == 1) {
                hunting();
            } else {
                attack();
            }
            // }
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
        if (getIdleCooldown() > 0 && isCanMove()) {
            if (getIdleDirection() == -1 && !this.sideLeftCollision()) {
                move(-1);
            } else if (getIdleDirection() == 1 && !this.sideRightCollisions()) {
                move(1);
            } else {
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
        // if (this.getIdleCooldown() <= 50 && this.isCanMove()) {
        this.getHitbox().setX(this.getHitbox().getX().intValue() + (this.getSpeed()) * direction);
        if (this.getState() == 0) {
            this.setIdleCooldown(this.getIdleCooldown() - 1);
        }

           /* if (this.getIdleCooldown() == 0) {
                this.setCanMove(false);
               // this.setIdleCooldown(this.getIdleCooldown() + 1);
            }

            */
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
