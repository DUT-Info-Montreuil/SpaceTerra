package modele;

import static controleur.Controleur.player;
import static controleur.Controleur.playerMouse;

public class Tool extends Item{

    private int pickPower;
    private int axePower;

    public Tool(int id) {
        super(id, 1);
        pickPower = 10;
        axePower = 5;
    }

    @Override
    public void use() {
        System.out.println("used Tool");
        player.breakBlock(playerMouse.getX(), playerMouse.getY(), pickPower);
    }
}
