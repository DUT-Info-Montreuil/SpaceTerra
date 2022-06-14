package modele;

import javafx.scene.image.Image;

public enum TypeItem {

    Dirt("Dirt", new Image("Sprites/Items/dirtItem.png")), // 0
    Wood("Wood", new Image("Sprites/Items/woodItem.png")),
    WoodStick("Wooden stick", new Image("Sprites/Items/woodenStick.png")),
    Stone("Stone", new Image("Sprites/Items/rockItem.png")),
    Coal("Coal", new Image("Sprites/Items/coalItem.png")),
    Iron("Iron", new Image("Sprites/Items/ironIngot.png")), // 5
    Gold("Gold", new Image("Sprites/Items/goldIngot.png")),
    Fluxium("Fluxium", new Image("Sprites/Items/fluxiumItem.png")),
    Vine("Vine", new Image("Sprites/Items/fluxiumItem.png")),
    WoodPick("Wooden pickaxe", new Image("Sprites/Items/Tools/woodenPickaxe.png")),
    StonePick("Stone pickaxe", new Image("Sprites/Items/Tools/stonePickaxe.png")), // 10
    IronPick("Iron pickaxe", new Image("Sprites/Items/Tools/ironPickaxe.png")),
    WoodAxe("Wooden axe", new Image("Sprites/Items/Tools/woodenAxe.png")),
    StoneAxe("Stone axe", new Image("Sprites/Items/Tools/stoneAxe.png")),
    IronAxe("Iron axe", new Image("Sprites/Items/Tools/ironAxe.png"));

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
