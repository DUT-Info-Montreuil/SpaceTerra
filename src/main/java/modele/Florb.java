package modele;

import controleur.Controleur;

public class Florb extends Enemy{

    private int strength;

    private int safeHeight = 1950;

    public Florb(int x, int y, Terrain terrain) {
        super(10, 6, new Hitbox(22,16,x,y, false),"/Sprites/Enemies/Florb/Florb.gif", 200, terrain);
        this.strength = 3;
        this.setFlying(true);
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

        switch(this.getState()){
            case "idle":
                switch(this.getIdleDirection()){
                    case 0:
                        this.setIdleDirection(Controleur.randomNum(1,3));
                        break;

                    case 1:
                        if (leftCheck && this.getIdleCooldown() <= 50 && this.isCanMove()) {
                            if (this.getHitbox().yProperty().intValue() > safeHeight)
                                this.getHitbox().setY(this.getHitbox().yProperty().intValue() - this.getSpeed());
                            this.getHitbox().setX(this.getHitbox().xProperty().intValue() + this.getSpeed());
                            this.setIdleCooldown(this.getIdleCooldown()-1);

                            if(this.getIdleCooldown() == 0) {
                                this.setCanMove(false);
                                this.setIdleCooldown(this.getIdleCooldown() + 1);
                            }
                        }
                        else if(this.getIdleCooldown() == 0) {
                            this.setCanMove(false);
                            this.setIdleCooldown(this.getIdleCooldown() + 1);
                        }
                        else if (!isCanMove() && getIdleCooldown() == 50) {
                            setCanMove(true);
                            this.setIdleDirection(0);
                        }
                        else
                            this.setIdleCooldown(this.getIdleCooldown() + 1);
                        break;

                    case 2:
                        if (rightCheck && this.getIdleCooldown() <= 50 && this.isCanMove()) {
                            this.getHitbox().setX(this.getHitbox().xProperty().intValue() - this.getSpeed());
                            this.setIdleCooldown(this.getIdleCooldown()-1);
                            if (this.getHitbox().yProperty().intValue() > safeHeight)
                                this.getHitbox().setY(this.getHitbox().yProperty().intValue() - this.getSpeed());

                            if(this.getIdleCooldown() == 0) {
                                this.setCanMove(false);
                                this.setIdleCooldown(this.getIdleCooldown() + 1);
                            }
                        }
                        else if (!isCanMove() && getIdleCooldown() == 50) {
                            setCanMove(true);
                            this.setIdleDirection(0);
                        }
                        else
                            this.setIdleCooldown(this.getIdleCooldown() + 1);
                        break;

                    case 3:
                        if(this.isCanMove()) {
                            this.setIdleCooldown(this.getIdleCooldown() - 1);
                            if(this.getIdleCooldown() == 0) {
                                this.setCanMove(false);
                                this.setIdleCooldown(this.getIdleCooldown() + 1);
                            }
                        }

                        else if (!isCanMove() && getIdleCooldown() == 50) {
                            setCanMove(true);
                            this.setIdleDirection(0);
                        }
                        else
                            this.setIdleCooldown(this.getIdleCooldown() + 1);
                }
                break;

            case "hunting":
                if (this.getHitbox().xProperty().intValue() < player.getHitbox().xProperty().intValue() - 5) {
                    if (leftCheck) {

                        if(this.getHitbox().yProperty().intValue() < player.getHitbox().yProperty().intValue() - 5)
                            this.getHitbox().setY(this.getHitbox().yProperty().intValue() + this.getSpeed());

                        else if(this.getHitbox().yProperty().intValue() > player.getHitbox().yProperty().intValue() + 5)
                            this.getHitbox().setY(this.getHitbox().yProperty().intValue() - this.getSpeed());

                        this.getHitbox().setX(this.getHitbox().xProperty().intValue() + this.getSpeed());
                    }
                }
                else if (this.getHitbox().xProperty().intValue() > player.getHitbox().xProperty().intValue() + 5) {
                    if (rightCheck) {

                        if(this.getHitbox().yProperty().intValue() < player.getHitbox().yProperty().intValue() - 5)
                            this.getHitbox().setY(this.getHitbox().yProperty().intValue() + this.getSpeed());

                        else if(this.getHitbox().yProperty().intValue() > player.getHitbox().yProperty().intValue() + 5)
                            this.getHitbox().setY(this.getHitbox().yProperty().intValue() - this.getSpeed());

                        this.getHitbox().setX(this.getHitbox().xProperty().intValue() - this.getSpeed());
                    }
                }
                break;

            default:
                break;
        }
    }
}
