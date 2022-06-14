package modele;

import java.util.Random;

public abstract class Enemy extends Entity{

    private int range;
    private boolean playerDetected;

    private int rangeMultiplier;

    // pour state : 0 c'est idle, 1 c'est hunting et 2 c'est attack
    private int state;
    private int idleCooldown;
    private int idleDirection;

    private int strenght;

    private boolean canMove;
    public Enemy(int vie, int vitesse, Hitbox hitbox, String path, int range, int strenght, Terrain terrain, int jumpHeight) {
        super(vie, vitesse, hitbox, path, terrain);
        this.range = range;
        this.state = 0;
        this.idleCooldown = 0;
        this.idleDirection = 0;
        this.playerDetected = false;
        this.rangeMultiplier = 1;
        this.canMove = true;

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

    public int getRangeMultiplier(){
        return rangeMultiplier;
    }

    public void setRangeMultiplier(int rangeMultiplier){
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

    public void detectPlayer(Player player, int rangeMultiplier){
        if(Math.abs(this.getHitbox().getX().intValue() - player.getHitbox().getX().intValue()) <= 5){
            this.setState(2);
        }
        else if(distanceToPosition(player.getHitbox().getX().intValue(), player.getHitbox().getY().intValue()) < (range * rangeMultiplier)) {
            this.state = 1;
            this.playerDetected = true;
        }
        else{
            this.state = 0;
            this.playerDetected = false;
        }
    }

    public void movement(Player player, boolean leftCheck, boolean rightCheck) {
        this.detectPlayer(player, getRangeMultiplier());

        if (this.getState() == 0) {
            this.setRangeMultiplier(1);
            idle();
        } else {
            this.setRangeMultiplier(2);
            /* if (this.isJumping() && !this.isGrounded()) {
                this.jump();
            }*/
            if (this.getState() == 1) {
                hunting();
            } else {
                idle();
                attack();
            }
        }
    }

    public abstract void idle();
    public abstract void hunting();
    public abstract void attack();

    public int proba() {
        Random r = new Random();
        return r.nextInt(3) - 1;
    }
}
