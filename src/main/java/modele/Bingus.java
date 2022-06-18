package modele;

import controleur.Controleur;

import java.util.ArrayList;

public class Bingus extends Enemy {

    private int strenght;
    public Bingus(int x, int y, Terrain terrain) {
        super(10, 3, new Hitbox(50,50,x,y, false), 200, terrain, new ArrayList<String>(){
            {
                add("idle");
                add("walk");
            }
        });
        this.strenght = 3;
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
                        this.setIdleDirection(Controleur.randomNum(1,3));
                        break;

                    case 1:
                        if (leftCheck && this.getIdleCooldown() <= 50 && this.isCanMove()) {
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

                        if(isCanMove())
                            this.setAction(getActions().get(1));
                        else
                            this.setAction(getActions().get(0));
                        break;

                    case 2:
                        if (rightCheck && this.getIdleCooldown() <= 50 && this.isCanMove()) {
                            this.getHitbox().setX(this.getHitbox().xProperty().intValue() - this.getSpeed());
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

                        if(isCanMove())
                            this.setAction(getActions().get(1));
                        else
                            this.setAction(getActions().get(0));
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

                        if(isCanMove())
                            this.setAction(getActions().get(1));
                        else
                            this.setAction(getActions().get(0));
                }
                break;

            case "hunting":
                this.setAction(getActions().get(1));

                if (this.getHitbox().xProperty().intValue() < player.getHitbox().xProperty().intValue() - 5) {
                    if (leftCheck)
                        this.getHitbox().setX(this.getHitbox().xProperty().intValue() + this.getSpeed());
                }
                else if (this.getHitbox().xProperty().intValue() > player.getHitbox().xProperty().intValue() + 5) {
                    if (rightCheck)
                        this.getHitbox().setX(this.getHitbox().xProperty().intValue() - this.getSpeed());
                }
                break;

            default:
                break;
        }
        //System.out.println(getIdleCooldown());
        //System.out.println(getIdleDirection());

    }
}
