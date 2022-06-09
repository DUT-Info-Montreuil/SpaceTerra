package modele;

public class Block {
    private int x;
    private int y;

    private String ressource;

    private final int insideOffset = 10;

    private Tile tile;

    private int hitX; // hitBox pos in relation to its Tile
    private int hitY;
    private static int idCount = 0;
    private String id;

    private Terrain terrain;

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

    public Block(Tile tile, int x, int y, Terrain terrain) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        hitX = x + tile.getHitbox().getX().intValue();
        hitY = y + tile.getHitbox().getY().intValue();
        this.id = "block" + idCount++;
        this.pvs = 1;
        this.terrain = terrain;
        ressource = this.getTile().getRessource();
    }

    public Block(ItemBlock item, int x, int y, Terrain terrain){
        this.terrain = terrain;
        this.x = x;
        this.y = y;
        if(item.getTypeItem().name().equalsIgnoreCase("Dirt")) {
            this.tile = terrain.getTileset().getTiles().get(18);
        } else if (item.getTypeItem().name().equalsIgnoreCase("Wood")) {
            this.tile = terrain.getTileset().getTiles().get(35);
        }
        hitX = x + tile.getHitbox().getX().intValue();
        hitY = y + tile.getHitbox().getY().intValue();
        this.id = "block" + idCount++;
        ressource = this.getTile().getRessource();
        this.pvs = 10;

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

    public Item getRessource(){
        ItemBlock itemBlock = null;
        try {
            //System.out.println(this.getTile().getRessource());
            if (this.getTile().getRessource().equals("dirt"))
                itemBlock = new ItemBlock(0);
            else if (this.getTile().getRessource().equals("wood"))
                itemBlock = new ItemBlock(1);
        }catch (Exception e) {

        }
        return itemBlock;
    }

}
