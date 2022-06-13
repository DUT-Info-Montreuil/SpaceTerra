package modele;

import controleur.Controleur;
/*
public class Bingus extends Enemy {

    public Bingus(int x, int y, Terrain terrain) {
        super(10, 3, new Hitbox(50,50,x,y), "/Sprites/Enemies/Bingus/Bingus.gif", 6, 3, terrain);
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
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
                        this.setIdleDirection(proba());
                        break;

                    case 1:
                        if (leftCheck && this.getIdleCooldown() <= 50 && this.isCanMove()) {
                            this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
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
                            this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                            this.setIdleCooldown(this.getIdleCooldown()-1);

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
                if (this.getHitbox().getX().intValue() < player.getHitbox().getX().intValue() - 5) {
                    if (leftCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() + this.getSpeed());
                }
                else if (this.getHitbox().getX().intValue() > player.getHitbox().getX().intValue() + 5) {
                    if (rightCheck)
                        this.getHitbox().setX(this.getHitbox().getX().intValue() - this.getSpeed());
                }



                break;

            default:
                break;
        }
        //System.out.println(getIdleCooldown());
        //System.out.println(getIdleDirection());

    }
}
*/