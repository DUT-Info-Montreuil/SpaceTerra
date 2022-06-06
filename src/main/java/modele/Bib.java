package modele;

import controleur.Controleur;

public class Bib extends Enemy{

    private int strength;

    public Bib(int x, int y, Terrain terrain) {
        super(5, 6, new Hitbox(14,18, x, y), "/Sprites/Enemies/Bib/BibIdle.gif", 250, terrain);
        this.strength = 1;
    }


}
