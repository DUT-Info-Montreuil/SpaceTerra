package modele;

import java.util.ArrayList;

import static modele.Environment.player;
import static modele.Environment.terrain;

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
        if(Math.abs(getHitbox().getX() - player.getHitbox().getX()) < 10){
            if(getHitbox().getY() > player.getHitbox().getY()){
                this.jump();
                Block bHautGauche =  terrain.getBlock((int)this.getHitbox().getX(), (int)this.getHitbox().getY());
                Block bHautMilieu =  terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth()/2, (int)this.getHitbox().getY());
                Block bHautDroite =  terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth(), (int)this.getHitbox().getY());
                System.out.println(bHautDroite);
                if(bHautGauche != null || bHautGauche != null || bHautMilieu != null){
                    if(bHautGauche != null){
                        bHautGauche.setHealth(0);
                        terrain.checkDestroyedBlock(bHautGauche);
                    }
                    if (bHautDroite != null){
                        bHautDroite.setHealth(0);
                        terrain.checkDestroyedBlock(bHautDroite);
                    }
                    if(bHautMilieu != null){
                        bHautMilieu.setHealth(0);
                        terrain.checkDestroyedBlock(bHautMilieu);
                    }
                }
            }
            else {
                if(!player.isInvicible() && getAttackCooldown() > 0){
                    player.decreaseHealth(5);
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
                player.launchInvicibleCooldown();
            }
        }
        else {
            huntingY();
            Block bBasGauche = terrain.getBlock((int)this.getHitbox().getX() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY() + this.getHitbox().getHeight() / 2);
            Block bHautGauche = terrain.getBlock((int)this.getHitbox().getX() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY());

            Block bBasDroite = terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY() + this.getHitbox().getHeight() / 2);
            Block bHautDroite = terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth() + (this.getSpeed() * this.getIdleDirection()), (int)this.getHitbox().getY());
            if (getHitbox().getY() <= player.getHitbox().getY() + 220) {
                if ((bBasGauche != null && bBasGauche.getHitbox().isSolid()) || (bHautGauche != null && bHautGauche.getHitbox().isSolid()) || (bBasDroite != null && bBasDroite.getHitbox().isSolid()) || (bHautDroite != null && bHautDroite.getHitbox().isSolid())) {
                    if (bBasGauche != null) {
                        bBasGauche.setHealth(0);
                        terrain.checkDestroyedBlock(bBasGauche);
                    }
                    if (bHautGauche != null) {
                        bHautGauche.setHealth(0);
                        terrain.checkDestroyedBlock(bHautGauche);
                    }
                    if (bHautDroite != null) {
                        bHautDroite.setHealth(0);
                        terrain.checkDestroyedBlock(bHautDroite);
                    }
                    if (bBasDroite != null) {
                        bBasDroite.setHealth(0);
                        terrain.checkDestroyedBlock(bBasDroite);
                    }
                } else {
                    this.moveX(getIdleDirection());
                }
            }
        }
    }


    @Override
    public void huntingY() {
        if(getHitbox().getY() <= player.getHitbox().getY() + 200){
            Block bBasGauche =  terrain.getBlock((int)this.getHitbox().getX(), (int)this.getHitbox().getY() + this.getHitbox().getHeight() + 32);
            Block bBasDroite =  terrain.getBlock((int)this.getHitbox().getX() + this.getHitbox().getWidth(), (int)this.getHitbox().getY() + this.getHitbox().getHeight());
            if(bBasGauche != null || bBasGauche != null){
                if(bBasGauche != null){
                    bBasGauche.setHealth(0);
                    terrain.checkDestroyedBlock(bBasGauche);
                }
                if (bBasDroite != null){
                    bBasDroite.setHealth(0);
                    terrain.checkDestroyedBlock(bBasDroite);
                }
            }
        }
    }

    @Override
    public void action() {
        this.jump();
    }


}


