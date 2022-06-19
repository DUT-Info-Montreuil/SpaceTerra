package modele;

import static controleur.Controler.player;
import static controleur.Controler.playerMouse;

public class Tool extends Item{

    private int pickPower;
    private int axePower;

    public Tool(int id) {
        super(id, 1);
        setStats();
    }

    private void setStats() {
        if(getTypeItem().name().equalsIgnoreCase("WoodPick")) {
            pickPower = 4;
            axePower = 2;
        } else if (getTypeItem().name().equalsIgnoreCase("StonePick")) {
            pickPower = 6;
            axePower = 3;
        } else if(getTypeItem().name().equalsIgnoreCase("IronPick")){
            pickPower = 8;
            axePower = 4;
        } else if(getTypeItem().name().equalsIgnoreCase("WoodAxe")) {
            axePower = 4;
            pickPower = 2;
        } else if (getTypeItem().name().equalsIgnoreCase("StoneAxe")) {
            axePower = 6;
            pickPower = 3;
        } else if(getTypeItem().name().equalsIgnoreCase("IronAxe")){
            axePower = 8;
            pickPower = 4;
        }
    }

    @Override
    public void use() {
        System.out.println("used Tool");
        player.breakBlock(playerMouse.getX(), playerMouse.getY(), pickPower, axePower);
    }
}
