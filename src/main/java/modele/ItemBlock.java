package modele;

import controleur.Controler;
import controleur.MouseHandler;

import static controleur.Controler.player;
import static controleur.Controler.playerMouse;

public class ItemBlock extends Item{
    public ItemBlock(int id) {
        super(id, 4);
    }

    @Override
    public void use() {
        if (Controler.terrain.checkDistancePosition(player, playerMouse.getX(), playerMouse.getY())) {
            Block bPlace = new Block(this, (MouseHandler.mouseX.getValue()/32) * 32, (MouseHandler.mouseY.getValue()/ 32) * 32, Controler.terrain);
            if (Controler.terrain.placeBlock((int)bPlace.getHitbox().getX(), (int)bPlace.getHitbox().getY(), bPlace)) {
                if (Controler.playerMouse.item != null){
                    Controler.playerMouse.decrementeItemQuantity(1);
                }
                else if(player.getPlayerInventory().getCurrItem() != null){
                    player.drop();
                }
            }
        }
    }
}
