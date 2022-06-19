package modele;

import javafx.scene.image.Image;

public abstract class Item {
    public int getId() {
        return id;
    }
    private TypeItem typeItem;
    private int id = -1;
    public int getMaxQuantity() {
        return maxQuantity;
    }
    private int maxQuantity;


    public Item(int id, int maxQuantity) {
        this.id = id;
        this.maxQuantity = maxQuantity;
        typeItem = TypeItem.values()[id];
    }

    public TypeItem getTypeItem() {
        return typeItem;
    }

    public abstract void use();
}
