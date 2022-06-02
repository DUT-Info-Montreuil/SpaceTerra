package modele;

public class Block {
    private int x;
    private int y;

    private final int insideOffset = 10;

    private Tile tile;
    private int hitX; // hitBox pos in relation to its Tile
    private int hitY;
    private static int idCount = 0;
    private String id;


    public String getId() {
        return id;
    }

    private int pvs;

    public int getPvs() {
        return pvs;
    }

    public void setPvs(int pvs) {
        this.pvs = pvs;
    }

    public Block(Tile tile, int x, int y) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        hitX = x + tile.getHitbox().getX().intValue();
        hitY = y + tile.getHitbox().getY().intValue();
        this.id = "block" + idCount++;
        this.pvs = 10;
    }

    public Block(ItemBlock item, int x, int y){
        this.x = x;
        this.y = y;
        this.tile = new Tile(12, item.getImage(), )
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getTile() {
        return tile;
    }

    public int getHitX() {
        return hitX;
    }

    public int getHitY() {
        return hitY;
    }
    public int getInsideOffset() {
        return insideOffset;
    }

    public Item ressource(){
        if(this.getTile().getRessource().equals("dirt")){
            return new ItemDirt(this.getId(), this.getTile().getImage());
        }
        else if (this.getTile().getRessource().equals("wood")){
            return new ItemWood(this.getId(),  this.getTile().getImage());
        }
        else {
            return null;
        }

    }

}
