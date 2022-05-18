package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

public class Block {
    private int x;
    private int y;

    private final int insideOffset = 10;

    private Tile tile;
    private int hitX; //
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

}
