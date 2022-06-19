package modele;

import controleur.Controleur;

import java.util.ArrayList;

public class Bingus extends Enemy {

    public Bingus(int x, int y, Terrain terrain) {
        super(10, 3, new Hitbox(50,50,x,y, false), 20, 3, terrain, 5, false, new ArrayList<String>(){
            {
                add("idle");
                add("walk");
            }
        });
    }



    @Override
    public void attack() {
        if(Math.abs(getHitbox().getX() - Controleur.player.getHitbox().getX()) < 10){
            if(getHitbox().getY() > Controleur.player.getHitbox().getY()){
                this.jump();
                Block bHautGauche =  Controleur.terrain.getBlock((int)this.getHitbox().getX(), (int)this.getHitbox().getY());
                Block bHautMilieu =  Controleur.terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth()/2, (int)this.getHitbox().getY());
                Block bHautDroite =  Controleur.terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth(), (int)this.getHitbox().getY());
                System.out.println(bHautDroite);
                if(bHautGauche != null || bHautGauche != null || bHautMilieu != null){
                    if(bHautGauche != null){
                        bHautGauche.setHealth(0);
                        Controleur.terrain.checkDestroyedBlock(bHautGauche);
                    }
                    if (bHautDroite != null){
                        bHautDroite.setHealth(0);
                        Controleur.terrain.checkDestroyedBlock(bHautDroite);
                    }
                    if(bHautMilieu != null){
                        bHautMilieu.setHealth(0);
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
            Block bBasGauche = Controleur.terrain.getBlock((int)this.getHitbox().getX() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY() + this.getHitbox().getHeight() / 2);
            Block bHautGauche = Controleur.terrain.getBlock((int)this.getHitbox().getX() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY());

            Block bBasDroite = Controleur.terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY() + this.getHitbox().getHeight() / 2);
            Block bHautDroite = Controleur.terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY());
            if (getHitbox().getY() <= Controleur.player.getHitbox().getY() + 220) {
                if ((bBasGauche != null && bBasGauche.getHitbox().isSolid()) || (bHautGauche != null && bHautGauche.getHitbox().isSolid()) || (bBasDroite != null && bBasDroite.getHitbox().isSolid()) || (bHautDroite != null && bHautDroite.getHitbox().isSolid())) {
                    if (bBasGauche != null) {
                        bBasGauche.setHealth(0);
                        Controleur.terrain.checkDestroyedBlock(bBasGauche);
                    }
                    if (bHautGauche != null) {
                        bHautGauche.setHealth(0);
                        Controleur.terrain.checkDestroyedBlock(bHautGauche);
                    }
                    if (bHautDroite != null) {
                        bHautDroite.setHealth(0);
                        Controleur.terrain.checkDestroyedBlock(bHautDroite);
                    }
                    if (bBasDroite != null) {
                        bBasDroite.setHealth(0);
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
            if(getHitbox().getY() <= Controleur.player.getHitbox().getY() + 200){
                Block bBasGauche =  Controleur.terrain.getBlock((int)this.getHitbox().getX(), (int)this.getHitbox().getY() + this.getHitbox().getHeight() + 32);
                Block bBasDroite =  Controleur.terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth(), (int)this.getHitbox().getY() + this.getHitbox().getHeight());
                if(bBasGauche != null || bBasGauche != null){
                    if(bBasGauche != null){
                        bBasGauche.setHealth(0);
                        Controleur.terrain.checkDestroyedBlock(bBasGauche);
                    }
                    if (bBasDroite != null){
                        bBasDroite.setHealth(0);
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


