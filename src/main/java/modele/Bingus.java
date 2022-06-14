package modele;

import controleur.Controleur;

public class Bingus extends Enemy {

    public Bingus(int x, int y, Terrain terrain) {
        super(10, 3, new Hitbox(50,50,x,y), "/Sprites/Enemies/Bingus/Bingus.gif", 6, 3, terrain, 5);
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
