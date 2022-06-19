package modele;

import controleur.Controleur;
import controleur.MouseHandler;

import static controleur.Controleur.playerMouse;
import static modele.Environment.player;
import static modele.Environment.terrain;

public class ItemBlock extends Item{
    public ItemBlock(int id) {
        super(id, 4);
    }

    @Override
    public void use() {
        if (terrain.checkDistancePosition(player, playerMouse.getX(), playerMouse.getY())) {
            Block bPlace = new Block(this, (MouseHandler.mouseX.getValue()/32) * 32, (MouseHandler.mouseY.getValue()/ 32) * 32, terrain);
            if (terrain.placeBlock((int)bPlace.getHitbox().getX(), (int)bPlace.getHitbox().getY(), bPlace)) {
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
