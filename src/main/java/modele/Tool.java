package modele;

import static controleur.Controleur.player;
import static controleur.Controleur.playerMouse;

public class Tool extends Item{

    private int pickPower;
    private int axePower;

    public Tool(int id) {
        super(id, 1);
        setStats();
    }

    private void setStats() {
        if(getTypeItem().name().equalsIgnoreCase("WoodPick")) {
            pickPower = 0;
        } else if (getTypeItem().name().equalsIgnoreCase("StonePick")) {
            pickPower = 0;
        } else if(getTypeItem().name().equalsIgnoreCase("IronPick")){

        } else if(getTypeItem().name().equalsIgnoreCase("WoodAxe")) {

        } else if (getTypeItem().name().equalsIgnoreCase("StoneAxe")) {

        } else if(getTypeItem().name().equalsIgnoreCase("IronAxe")){

        }
    }

    @Override
    public void use() {
        System.out.println("used Tool");
        player.breakBlock(playerMouse.getX(), playerMouse.getY(), pickPower, axePower);
    }
}
