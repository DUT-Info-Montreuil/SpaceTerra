package modele;

import javafx.scene.image.Image;

public enum TypeItem {

    Dirt(new Image("Sprites/Items/dirtItem.png")),
    Wood(new Image("Sprites/Items/woodItem.png"));
    private Image image;

    TypeItem(Image image){
        this.image = image;
    }

    public Image getImage(){
        return this.image;
    }

}
