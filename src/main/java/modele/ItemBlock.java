package modele;

import controleur.Controleur;
import controleur.MouseHandler;

import static controleur.Controleur.player;
import static controleur.Controleur.playerMouse;

public class ItemBlock extends Item{
    public ItemBlock(int id) {
        super(id, 4);
    }

    @Override
    public void use() {
        if (Controleur.terrain.checkDistancePosition(player, playerMouse.getX(), playerMouse.getY())) {
            Block bPlace = new Block(this, (MouseHandler.mouseX.getValue()/32) * 32, (MouseHandler.mouseY.getValue()/ 32) * 32, Controleur.terrain);
            if (Controleur.terrain.placeBlock(bPlace.getHitX(), bPlace.getHitY(), bPlace)) {
                if (Controleur.playerMouse.item != null){
                    Controleur.playerMouse.decrementeItemQuantity(1);
                }
                else if(player.getPlayerInventory().getCurrItem() != null){
                    player.drop();
                }
        }

       }
    }
}
