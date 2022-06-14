package modele;

import controleur.Controleur;

public class Florb extends Enemy{

    private int safeHeight;

    public Florb(int x, int y, Terrain terrain) {
        super(10, 6, new Hitbox(22,16,x,y),"/Sprites/Enemies/Florb/Florb.gif", 200, 3, terrain, 5);
        this.setFlying(true);
        this.safeHeight = 1950;
    }


    @Override
    public void idle() {

    }

    @Override
    public void hunting() {

    }

    @Override
    public void attack() {

    }
}


