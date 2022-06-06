package modele;

import controleur.Controleur;

public class Florb extends Enemy{

    private int strength;

    private int safeHeight = 1950;

    public Florb(int x, int y, Terrain terrain) {
        super(10, 6, new Hitbox(22,16,x,y),"/Sprites/Enemies/Florb/Florb.gif", 200, terrain);
        this.strength = 3;
        this.setFlying(true);
    }


}
