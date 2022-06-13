package modele;

import javafx.scene.image.Image;

public enum TypeItem {

    Dirt("Dirt", new Image("Sprites/Items/dirtItem.png")),
    Wood("Wood", new Image("Sprites/Items/woodItem.png")),
    WoodStick("Wooden stick", new Image("Sprites/Items/woodenStick.png")),
    Stone("Stone", new Image("Sprites/Items/rockItem.png")),
    Coal("Coal", new Image("Sprites/Items/coalItem.png")),
    Iron("Iron", new Image("Sprites/Items/ironIngot.png")),
    Gold("Gold", new Image("Sprites/Items/goldIngot.png")),
    Fluxium("Fluxium", new Image("Sprites/Items/fluxiumItem.png"));


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
