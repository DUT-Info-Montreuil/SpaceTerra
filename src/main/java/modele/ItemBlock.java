package modele;

import controleur.Controleur;
import controleur.MouseHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ItemBlock extends Item{
    private TypeItemBlock typeItemBlock;
    public TypeItemBlock getTypeItemBlock() {
        return typeItemBlock;
    }

    public ItemBlock(int id) {
        super(id, 4);
        typeItemBlock = TypeItemBlock.values()[id];
    }

    @Override
    public void use() {
       Block bPlace = new Block(this, (MouseHandler.mouseX.getValue()/32) * 32, (MouseHandler.mouseY.getValue()/ 32) * 32, Controleur.terrain);
       if(Controleur.terrain.placeBlock(bPlace.getHitX(), bPlace.getHitY(), bPlace)){
           Controleur.player.drop();
       }

    }
}
