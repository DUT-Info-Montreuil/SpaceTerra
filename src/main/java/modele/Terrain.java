package modele;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;

public class Terrain {

    private ObservableList<Block> blocks;

    private ObservableList<Block> solidBlocks;

    private ArrayList<Layer> layers;
    private ArrayList<BlockLoader> blockLoaders;
    private int height, width; // in tiles
    private int tileWidth, tileHeight;


    public Terrain(JsonGameLoader data) {
        blocks = FXCollections.observableArrayList();
        solidBlocks = FXCollections.observableArrayList();
        for (int i = 0; i < 250000; i++) {
            blocks.add(null);
        }
        loadData(data);
        loadBlocks();
    }

    public void loadData(JsonGameLoader data){
        this.layers = data.getLayers();
        this.blockLoaders = data.getTiles();
        this.height = data.getHeight();
        this.width = data.getWidth();
        this.tileWidth = data.getTileWidth();
        this.tileHeight = data.getTileHeight();
    }



    public void loadBlocks() {
        for (int l = 0; l < layers.size(); ++l) {
            int i = 0;
            if (layers.get(l).getIsVisible()) {
                Layer currentLayer = layers.get(l);
                int x = currentLayer.getxPos();
                int y = currentLayer.getyPos();
                int[] data = currentLayer.getData();
                int currentWidth = 0;
                try {
                    for (int t = 0; t < data.length; ++t) {
                        if (currentWidth >= currentLayer.getWidth()) {
                            x = currentLayer.getxPos();
                            y += 32;
                            currentWidth = 0;
                        }
                        if (data[t] == 0 && l == 0) {
                            blocks.set(i, null);
                        } else {
                            Iterator tilesIterator = blockLoaders.iterator();

                            while (tilesIterator.hasNext()) {
                                BlockLoader currBlockLoader = (BlockLoader) tilesIterator.next();

                                if (data[t] == currBlockLoader.getId()) {
                                    Block b = new Block(currBlockLoader, x, y);
                                    blocks.set(i, b);
                                    System.out.println("Loaded Block : " + i);
                                    if (b.getHitbox().isSolid()) {
                                        solidBlocks.add(b);
                                    }
                                }
                            }
                        }
                        i++;
                        x += 32;
                        ++currentWidth;
                    }
                } catch (NullPointerException e) {
                    System.out.println("null");
                }
            }
            System.out.println(i);
        }
    }

    public void deleteBlock(Block block) {
        try{
            this.getBlocks().set(this.getBlocks().indexOf(block), null);
        } catch (IndexOutOfBoundsException e){

        }

    }

    public void deleteSolidBlock(Block block) {
        try{
        this.getSolidBlocks().set(this.getSolidBlocks().indexOf(block), null);
        } catch (IndexOutOfBoundsException e){

        }
    }

    public Block getBlock(int x, int y) {
        return this.getBlocks().get(getIndex(x, y));
    }

    public int getIndex(int x, int y) {
        return (y / 32 * width + x / 32);
    }

    public boolean placeBlock(int x, int y, Block bPlace) {
        Block bPos = this.getBlock(x, y);
        if (bPos == null) {
            this.getBlocks().set(this.getIndex(x, y), bPlace);
            if (bPlace.getHitbox().isSolid()) {
                this.getSolidBlocks().add(bPlace);
            }
            return true;
        }
        return false;
    }

    public boolean checkDestroyedBlock(Block b){
            if (b.getHealth() <= 0) {
                Controleur.terrain.deleteBlock(b);
                if (b.getHitbox().isSolid()) {
                    Controleur.terrain.deleteSolidBlock(b);
                }
                return true;
            }
            return false;
    }

    public boolean checkDistanceBlock(Entity ent, Block b) {
        return ent.distanceToBlock(b) <= ent.actionRange;
    }

    public boolean checkDistancePosition(Entity ent, int x, int y){
        return ent.distanceToPosition(x,y) <= ent.actionRange;
    }


    public ObservableList<Block> getBlocks() {
        return blocks;
    }

    public ObservableList<Block> getSolidBlocks() {
        return solidBlocks;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public ArrayList<BlockLoader> getBlocLoaders() {
        return blockLoaders;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
