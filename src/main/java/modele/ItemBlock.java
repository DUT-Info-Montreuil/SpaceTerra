package modele;

import controleur.Controleur;
import controleur.MouseHandler;

public class ItemBlock extends Item{



    public ItemBlock(int id) {
        super(id, 4);
    }

    @Override
    public void use() {
       Block bPlace = new Block(this, (MouseHandler.mouseX.getValue()/32) * 32, (MouseHandler.mouseY.getValue()/ 32) * 32, Controleur.terrain);
       if (Controleur.terrain.placeBlock(bPlace.getHitX(), bPlace.getHitY(), bPlace)) {
           if(Controleur.player.getInventory().getCurrItem() != null){
               Controleur.player.drop();
           }
           else if (Controleur.playerMouse.item != null){
               Controleur.playerMouse.decremente(1);
           }

       }

    }
}
