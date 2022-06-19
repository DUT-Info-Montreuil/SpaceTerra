package modele;

import java.util.ArrayList;

import static modele.Environment.player;

public abstract class Enemy extends Entity {

    private int range;
    private boolean playerDetected;
    private int rangeMultiplier;
    private boolean canAttack;
    private boolean canMove = true;
    // pour state : 0 c'est idle, 1 c'est hunting et 2 c'est attack
    private int state;
    private int idleCooldown;
    private int attackCooldown;
    private int idleDirection;

    private int strenght;

    public Enemy(int vie, int vitesse, Hitbox hitbox, int range, int strenght, Terrain terrain, int jumpHeight, boolean flying, ArrayList<String> actions) {
        super(vie, vitesse, hitbox, terrain, jumpHeight, flying, actions);
        this.range = range;
        this.state = 0;
        this.idleCooldown = 0;
        this.idleDirection = 0;
        this.playerDetected = false;
        this.rangeMultiplier = 1;
        this.canMove = true;
        this.attackCooldown = 0;
        this.canAttack = true;
        this.attackCooldown = 100;
    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getState() {
        return state;
    }

    public int getRangeMultiplier() {
        return rangeMultiplier;
    }

    public void setRangeMultiplier(int rangeMultiplier) {
        this.rangeMultiplier = rangeMultiplier;
    }

    public void setState(int state) {
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

    public void detectPlayer(Player player, int rangeMultiplier) {
        if (distanceToPosition((int) player.getHitbox().getX(), (int) player.getHitbox().getY()) <= range / 2) {
            this.setState(2);
        } else if (distanceToPosition((int) player.getHitbox().getX(), (int) player.getHitbox().getY()) < (range * rangeMultiplier)) {
            if (getState() == 2 && isJumping()) {
                this.state = 1;
                this.playerDetected = true;
            } else if (getState() == 0) {
                this.state = 1;
                this.playerDetected = true;
                this.setAttackCooldown(1000);
            }

        } else {
            this.state = 0;
            this.playerDetected = false;
        }
    }

    public void idle() {
        if (getIdleCooldown() > 0 && isCanMove()) {
            if (getIdleDirection() == -1) {
                if (this.getHitbox().getX() >= 10) {
                    moveX(getIdleDirection());
                    if (this.sideLeftCollision()) {
                        action();
                    }

                }
            } else if (getIdleDirection() == 1) {
                if (this.getHitbox().getX() <= this.getTerrain().getWidth() * 32 - 30) {
                    moveX(getIdleDirection());
                    if (this.sideRightCollisions()) {
                        action();
                    }
                }
            } else {
                this.setIdleDirection(this.randomDirection());
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

    @Override
    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        if (this.getAttackCooldown() < 100) {
            this.setAttackCooldown(this.getAttackCooldown() + 1);
        }
        this.detectPlayer(player, getRangeMultiplier());
        if (this.getState() == 0) {
            if (this.isFlying()) {
                action();
            }
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
    }

    public abstract void attack();

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }


    public void moveX(int direction) {
        // if (this.getIdleCooldown() <= 50 && this.isCanMove()) {
        this.getHitbox().setX(this.getHitbox().getX() + (this.getSpeed()) * direction);
        if (this.getState() == 0) {
            this.setIdleCooldown(this.getIdleCooldown() - 1);
        }
    }

    public void hunting() {
        huntingX();
        huntingY();
    }

    public void huntingX() {
        if (this.getHitbox().getX() < player.getHitbox().getX()) {
            if (!sideRightCollisions()) {
                setIdleDirection(1);
                moveX(getIdleDirection());
            } else {
                setIdleDirection(1);
                moveX(getIdleDirection());
                action();
            }
        } else {
            if (!sideLeftCollision()) {
                setIdleDirection(-1);
                moveX(getIdleDirection());
            } else {
                setIdleDirection(-1);
                moveX(getIdleDirection());
                action();
            }
        }
    }

    public abstract void huntingY();

    public abstract void action();



}
