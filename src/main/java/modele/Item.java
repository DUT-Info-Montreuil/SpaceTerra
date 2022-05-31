package modele;

public abstract class Item {
    public String getId() {
        return id;
    }

    private String id;

    public int getMaxQuantity() {
        return maxQuantity;
    }

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
