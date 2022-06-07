package modele;

import javafx.scene.image.Image;

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

    }
}
