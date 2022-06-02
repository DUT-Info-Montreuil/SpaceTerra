package modele;

import javafx.scene.image.Image;

public abstract class Item {
    public String getId() {
        return id;
    }

    private String id;
    private int maxQuantity;

    private Image image;


    public Item(String id, int maxQuantity, Image image) {
        this.id = id;
        this.maxQuantity = maxQuantity;
        this.image = image;
    }

    public abstract void use();
    public Image getImage() {
        return image;
    }
}
