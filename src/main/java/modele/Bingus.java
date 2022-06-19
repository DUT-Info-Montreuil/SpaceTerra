package modele;

import controleur.Controleur;

import java.util.ArrayList;

public class Bingus extends Enemy {

    public Bingus(int x, int y, Terrain terrain) {
        super(10, 3, new Hitbox(50,50,x,y, false), 200, terrain, new ArrayList<String>(){
            {
                add("idle");
                add("walk");
            }
        });
        this.strenght = 3;
    }



    @Override
    public void attack() {
        if(Math.abs(getHitbox().getX().intValue() - Controleur.player.getHitbox().getX().intValue()) < 10){
            if(getHitbox().getY().intValue() > Controleur.player.getHitbox().getY().intValue()){
                this.jump();
                Block bHautGauche =  Controleur.terrain.getBlock(this.getHitbox().getX().intValue(), this.getHitbox().getY().intValue());
                Block bHautMilieu =  Controleur.terrain.getBlock(this.getHitbox().getX().intValue() + this.getHitbox().getWidth()/2, this.getHitbox().getY().intValue());
                Block bHautDroite =  Controleur.terrain.getBlock(this.getHitbox().getX().intValue() + this.getHitbox().getWidth(), this.getHitbox().getY().intValue());
                System.out.println(bHautDroite);
                if(bHautGauche != null || bHautGauche != null || bHautMilieu != null){
                    if(bHautGauche != null){
                        bHautGauche.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bHautGauche);
                    }
                    if (bHautDroite != null){
                        bHautDroite.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bHautDroite);
                    }
                    if(bHautMilieu != null){
                        bHautMilieu.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bHautMilieu);
                    }
                }
            }
            else {
                if(!Controleur.player.isInvicible() && getAttackCooldown() > 0){
                    Controleur.player.decreaseHealth(5);
                    setAttackCooldown(getAttackCooldown() - 1000);
                }
                else {
                    setCanAttack(false);
                    if (getAttackCooldown() < 1000 && !isCanAttack()) {
                        setAttackCooldown(getAttackCooldown() + 5);
                    } else {
                        setCanAttack(true);
                    }
                }
                Controleur.player.launchInvicibleCooldown();
            }
        }
        else {
            huntingY();
            Block bBasGauche = Controleur.terrain.getBlock(this.getHitbox().getX().intValue() + (this.getSpeed() * this.getIdleDirection()), this.getHitbox().getY().intValue() + this.getHitbox().getHeight() / 2);
            Block bHautGauche = Controleur.terrain.getBlock(this.getHitbox().getX().intValue() + (this.getSpeed() * this.getIdleDirection()), this.getHitbox().getY().intValue());

            Block bBasDroite = Controleur.terrain.getBlock(this.getHitbox().getX().intValue() + this.getHitbox().getWidth() + (this.getSpeed() * this.getIdleDirection()), this.getHitbox().getY().intValue() + this.getHitbox().getHeight() / 2);
            Block bHautDroite = Controleur.terrain.getBlock(this.getHitbox().getX().intValue() + this.getHitbox().getWidth() + (this.getSpeed() * this.getIdleDirection()), this.getHitbox().getY().intValue());
            if (getHitbox().getY().intValue() <= Controleur.player.getHitbox().getY().intValue() + 220) {
                if ((bBasGauche != null && bBasGauche.getTile().getHitbox().isSolid()) || (bHautGauche != null && bHautGauche.getTile().getHitbox().isSolid()) || (bBasDroite != null && bBasDroite.getTile().getHitbox().isSolid()) || (bHautDroite != null && bHautDroite.getTile().getHitbox().isSolid())) {
                    if (bBasGauche != null) {
                        bBasGauche.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bBasGauche);
                    }
                    if (bHautGauche != null) {
                        bHautGauche.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bHautGauche);
                    }
                    if (bHautDroite != null) {
                        bHautDroite.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bHautDroite);
                    }
                    if (bBasDroite != null) {
                        bBasDroite.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bBasDroite);
                    }
                } else {
                    this.moveX(getIdleDirection());
                }
            }
        }

    }


    @Override
    public void huntingY() {
            if(getHitbox().getY().intValue() <= Controleur.player.getHitbox().getY().intValue() + 200){
                Block bBasGauche =  Controleur.terrain.getBlock(this.getHitbox().getX().intValue(), this.getHitbox().getY().intValue() + this.getHitbox().getHeight() + 32);
                Block bBasDroite =  Controleur.terrain.getBlock(this.getHitbox().getX().intValue() + this.getHitbox().getWidth(), this.getHitbox().getY().intValue() + this.getHitbox().getHeight());
                if(bBasGauche != null || bBasGauche != null){
                    if(bBasGauche != null){
                        bBasGauche.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bBasGauche);
                    }
                    if (bBasDroite != null){
                        bBasDroite.setPvs(0);
                        Controleur.terrain.checkDestroyedBlock(bBasDroite);
                    }
                }


        }

    }

    @Override
    public void action() {
        this.jump();
    }


}


