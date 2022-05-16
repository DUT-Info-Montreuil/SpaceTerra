package modele;

public class Bingus extends Entite{

    private int force;

    public Bingus(int x, int y) {
        super(10, 10, new Hitbox(0,0,x,y), "/Sprites/Enemies/YinYang.png");
    }

    @Override
    public void deplacement() {

    }
}
