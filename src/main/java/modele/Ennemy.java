package modele;

public abstract class Ennemy extends Entity{

    private int range;
    private boolean playerDetected = false;

    private String state = "idle";
    private int idleCooldown = 50;
    private int idleDirection = 0;
    private boolean canMove = true;
    public Ennemy(int vie, int vitesse, Hitbox hitbox, String path, int range) {
        super(vie, vitesse, hitbox, path);
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
            this.state = "attack";
            this.playerDetected = true;
        }
        else{
            this.state = "idle";
            this.playerDetected = false;
        }
    }

    public abstract void movement(Player player, boolean leftCheck, boolean rightCheck, Terrain terrain);
}
