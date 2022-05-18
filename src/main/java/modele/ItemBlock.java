package modele;

public class ItemBlock extends Item{
    private Tile tile;
    public ItemBlock(int id, Tile tile) {
        super(id, 12);
        this.tile = tile;
    }

    @Override
    public void use() {

    }
}
