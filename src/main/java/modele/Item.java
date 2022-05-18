package modele;

public abstract class Item {
    private String id;
    private int maxQuantity;

    private Tile tile;


    public Item(String id, int maxQuantity, Tile tile) {
        this.id = id;
        this.maxQuantity = maxQuantity;
        this.tile = tile;
    }

    public abstract void use();
    public Tile getTile() {
        return tile;
    }
}
