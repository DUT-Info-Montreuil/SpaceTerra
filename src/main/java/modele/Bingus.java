package modele;

import controleur.Controleur;

public class Bingus extends Enemy {

    private int strenght;
    public Bingus(int x, int y, Terrain terrain) {
        super(10, 3, new Hitbox(50,50,x,y), "/Sprites/Enemies/Bingus/Bingus.gif", 200, terrain);
        this.strenght = 3;
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

        //System.out.println(getIdleCooldown());
        //System.out.println(getIdleDirection());


}
