package modele;

public class Block {
    private int gridX;
    private int gridY;
    private int dataId;

    private String ressource;

    private Hitbox hitbox;

    private static int idCount = 0;
    private String id;

    private int health;
    private int pickDef;
    private int axeDef;



    public Block(BlocLoader blocLoader, int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        hitbox = new Hitbox(blocLoader.getHitbox().getWidth(), blocLoader.getHitbox().getHeight(), gridX + blocLoader.getHitbox().xProperty().intValue(), gridY + blocLoader.getHitbox().yProperty().intValue(), true);
        this.id = "block" + idCount++;
        ressource = blocLoader.getRessource();
        this.dataId = blocLoader.getId();
        resourceStats();
    }

    public Block(ItemBlock item, int gridX, int gridY, Terrain terrain){
        this.gridX = gridX;
        this.gridY = gridY;
        BlocLoader blocLoader = null;
        if(item.getTypeItem().name().equalsIgnoreCase("Dirt")) {
            blocLoader = terrain.getBlocLoaders().get(18);
        } else if (item.getTypeItem().name().equalsIgnoreCase("Wood")) {
            blocLoader = terrain.getBlocLoaders().get(35);
        } else if(item.getTypeItem().name().equalsIgnoreCase("Stone")){
            blocLoader = terrain.getBlocLoaders().get(42);
        }
        hitbox = new Hitbox(blocLoader.getHitbox().getWidth(), blocLoader.getHitbox().getHeight(), gridX + blocLoader.getHitbox().getX(), gridY + blocLoader.getHitbox().getY(), true);

        this.id = "block" + idCount++;
        dataId = blocLoader.getId();
        ressource = blocLoader.getRessource();
        resourceStats();
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getId() {
        return id;
    }

    public String getRessource() {
        return ressource;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public int getDataId() {
        return dataId;
    }
}
