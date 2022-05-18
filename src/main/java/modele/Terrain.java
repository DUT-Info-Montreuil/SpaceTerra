package modele;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Iterator;

public class Terrain {

    private TerrainData terrainData;
    private ArrayList<Chunk> chunks;


    public Terrain(TerrainData terrainData){
        this.terrainData = terrainData;
        chunks = new ArrayList<>();
    }

    public void loadChunks(){

        /*
        for (int l = 0; l < terrainData.getLayers().size(); ++l) {
            if (((Layer) terrainData.getLayers().get(l)).getIsVisible()) {
                Layer currentLayer = (Layer) terrainData.getLayers().get(l);
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

                        Iterator tileIterator = terrainData.getTileSet().getTiles().iterator();

                        while (tileIterator.hasNext()) {
                            Tile currTile = (Tile) tileIterator.next();
                            if (data[t] == currTile.getId()) {
                                Block b = new Block(currTile, x, y);
                                blocks.add(b);
                                if(b.getTile().getHitbox().isSolid()){
                                    solidBlocks.add(b);
                                }
                            }
                        }

                        x += 32;
                        ++currentWidth;
                    }
                } catch (NullPointerException var12) {
                    System.out.println("null");
                }
            }
        }*/
    }
}
