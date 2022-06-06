package modele;

import javafx.scene.image.Image;

public abstract class Item {
    public int getId() {
        return id;
    }

    private int id;

    public int getMaxQuantity() {
        return maxQuantity;
    }

    private int maxQuantity;


    public Item(int id, int maxQuantity) {
        this.id = id;
        this.maxQuantity = maxQuantity;
    }

    public abstract void use();
}
