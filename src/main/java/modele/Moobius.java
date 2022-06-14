package modele;

public class Moobius extends Animal{
    public Moobius(Terrain terrain,int x, int y) {
        super(20, 5, new Hitbox(39,31,x,y), "/Sprites/Mobs/Moobius/moobiusIdle.gif", terrain);
    }

    public void movement(Player player, boolean leftCheck, boolean rightCheck) {

    }
}
