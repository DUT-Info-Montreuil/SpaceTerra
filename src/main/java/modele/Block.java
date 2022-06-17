package modele;

public class Block {
    private int x;
    private int y;

    private String ressource;


    private Tile tile;

    private int hitX; // hitBox pos in relation to its Tile
    private int hitY;
    private static int idCount = 0;
    private String id;

    private Terrain terrain;

    public String getId() {
        return id;
    }

    private int health;
    private int pickDef;
    private int axeDef;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Block(Tile tile, int x, int y, Terrain terrain) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        hitX = x + tile.getHitbox().getX().intValue();
        hitY = y + tile.getHitbox().getY().intValue();
        this.id = "block" + idCount++;
        this.terrain = terrain;
        ressource = this.getTile().getRessource();
        resourceStats();
    }

    public Block(ItemBlock item, int x, int y, Terrain terrain){
        this.terrain = terrain;
        this.x = x;
        this.y = y;
        if(item.getTypeItem().name().equalsIgnoreCase("Dirt")) {
            this.tile = terrain.getTileset().getTiles().get(18);
        } else if (item.getTypeItem().name().equalsIgnoreCase("Wood")) {
            this.tile = terrain.getTileset().getTiles().get(35);
        } else if(item.getTypeItem().name().equalsIgnoreCase("Stone")){
            this.tile = terrain.getTileset().getTiles().get(42);
        }
        hitX = x + tile.getHitbox().getX().intValue();
        hitY = y + tile.getHitbox().getY().intValue();
        this.id = "block" + idCount++;
        ressource = this.getTile().getRessource();
        resourceStats();
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

    public Item getRessourceAsItem(){
        Item item = null;
        try {
            switch(ressource){
                case "dirt":
                    item = new ItemBlock(0);
                    break;
                case "wood":
                    item = new ItemBlock(1);
                    break;
                case "leaf":
                    item = new CraftResource(8);
                    break;
                case "stone":
                    item = new ItemBlock(3);
                    break;
                case "coal":
                    item = new CraftResource(4);
                    break;
                case "iron":
                    item = new CraftResource(5);
                    break;
                case "gold":
                    item = new CraftResource(6);
                    break;
                case "fluxium":
                    item = new CraftResource(7);
                    break;
                default:
                    break;
            }
        }catch (Exception e) {}
        return item;
    }

    public void resourceStats(){
        health = 20;
        switch(ressource){
            case "dirt": case "leaf":
                pickDef = 1;
                axeDef = 1;
                break;
            case "wood":
                pickDef = 10;
                axeDef = 1;
                break;
            case "stone":
                pickDef = 2;
                axeDef = 20;
                break;
            case "coal":
                pickDef = 3;
                axeDef = 20;
                break;
            case "iron":
                pickDef = 4;
                axeDef = 20;
                break;
            case "gold":
                pickDef = 5;
                axeDef = 20;
                break;
            case "fluxium":
                pickDef = 6;
                axeDef = 20;
                break;
            default:
                pickDef = 9999;
                axeDef = 9999;
                break;
        }
    }

    public int getPickDef() {
        return pickDef;
    }

    public int getAxeDef() {
        return axeDef;
    }
}
