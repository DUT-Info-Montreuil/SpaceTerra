package modele;

import javafx.scene.image.Image;

public enum TypeItemBlock {

    Dirt(new Image("Sprites/TileSet/Dirt17.png")),
    Wood(new Image("Sprites/TileSet/treeTile12.png"));
    private Image image;

    TypeItemBlock(Image image){
        this.image = image;
    }

    public Image getImage(){
        return this.image;
    }

}
