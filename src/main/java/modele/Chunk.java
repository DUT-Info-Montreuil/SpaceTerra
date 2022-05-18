package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Chunk {
    private Hitbox hitbox;
    private ObservableList<Block> blocks;
    private ObservableList<Block> solidBlocks;
    private final int maxBlocks = 100; // Max amount of blocks per chunk
    private final int maxWidth = 10; // Max amount of blocks per line
    private final int maxHeight = 10;

    public Chunk(){
        blocks = FXCollections.observableArrayList();
        solidBlocks = FXCollections.observableArrayList();
        hitbox = new Hitbox(maxWidth * 32, maxHeight * 32, 0, 0);
    }

    public boolean isFull(){
        return blocks.size() >= maxBlocks;
    }

    public void addBlock(Block block){
        if(blocks.isEmpty()){
            hitbox.setX(block.getX());
            hitbox.setY(block.getY());
        }
        blocks.add(block);
        if(block.getTile().getHitbox().isSolid())
            solidBlocks.add(block);
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public ObservableList<Block> getBlocks() {
        return blocks;
    }

    public ObservableList<Block> getSolidBlocks() {
        return solidBlocks;
    }

    public int getMaxBlocks() {
        return maxBlocks;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }
}
