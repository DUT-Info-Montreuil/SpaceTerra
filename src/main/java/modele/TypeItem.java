package modele;

import javafx.scene.image.Image;

public enum TypeItem {

    Dirt("Dirt", new Image("Sprites/Items/dirtItem.png")),
    Wood("Wood", new Image("Sprites/Items/woodItem.png"));
    private Image image;
    private String name;

    TypeItem(String name, Image image){
        this.image = image;
        this.name = name;
    }

    public Image getImage(){
        return this.image;
    }

    public String getName() {
        return name;
    }
}
