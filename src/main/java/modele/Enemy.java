package modele;

import controleur.Controleur;

public abstract class Enemy extends Entity{

    private int range;
    private boolean playerDetected = false;

    private String state = "idle";
    private int idleCooldown = 50;
    private int idleDirection = 0;

    private boolean canMove = true;
    public Enemy(int vie, int vitesse, Hitbox hitbox, String path, int range, Terrain terrain) {
        super(vie, vitesse, hitbox, path, terrain);
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isPlayerDetected() {
        return playerDetected;
    }

    public void setPlayerDetected(boolean playerDetected) {
        this.playerDetected = playerDetected;
    }

    public int getIdleCooldown() {
        return idleCooldown;
    }

    public void setIdleCooldown(int idleCooldown) {
        this.idleCooldown = idleCooldown;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public int getIdleDirection() {
        return idleDirection;
    }

    public void setIdleDirection(int idleDirection) {
        this.idleDirection = idleDirection;
    }

    public void detectPlayer(Player player, int rangeMultiplier){
        if((this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() - range*rangeMultiplier && this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() + range*rangeMultiplier) && (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() - range*rangeMultiplier && this.getHitbox().getY().intValue() < player.getHitbox().getY().intValue() + range*rangeMultiplier)) {
                if(this.state != "attack") {
                    this.state = "hunting";
                    this.playerDetected = true;
                }
        }
        else{
            this.state = "idle";
            this.playerDetected = false;
        }
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
                System.out.println(getSpeed());
                break;

            default:
                break;
        }
        System.out.println(getState());
        System.out.println(isJumping());
        System.out.println(this.getHitbox().getX().intValue() - player.getHitbox().getX().intValue());
    }

}
