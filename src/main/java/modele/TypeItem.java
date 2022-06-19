package modele;

import javafx.scene.image.Image;

public enum TypeItem {

    Dirt("Dirt", "Sprites/Items/dirtItem.png"), // 0
    Wood("Wood", "Sprites/Items/woodItem.png"),
    WoodStick("Wooden stick", "Sprites/Items/woodenStick.png"),
    Stone("Stone", "Sprites/Items/rockItem.png"),
    Coal("Coal", "Sprites/Items/coalItem.png"),
    Iron("Iron", "Sprites/Items/ironIngot.png"), // 5
    Gold("Gold", "Sprites/Items/goldIngot.png"),
    Fluxium("Fluxium", "Sprites/Items/fluxiumItem.png"),
    Vine("Vine", "Sprites/Items/vineItem.png"),
    WoodPick("Wooden pickaxe", "Sprites/Items/Tools/woodenPickaxe.png"),
    StonePick("Stone pickaxe", "Sprites/Items/Tools/stonePickaxe.png"), // 10
    IronPick("Iron pickaxe", "Sprites/Items/Tools/ironPickaxe.png"),
    WoodAxe("Wooden axe", "Sprites/Items/Tools/woodenAxe.png"),
    StoneAxe("Stone axe", "Sprites/Items/Tools/stoneAxe.png"),
    IronAxe("Iron axe", "Sprites/Items/Tools/ironAxe.png");

    private String image;
    private String name;

    TypeItem(String name, String image){
        this.image = image;
        this.name = name;
    }

    public String getImage(){
        return this.image;
    }

    public String getName() {
        return name;
    }
}
