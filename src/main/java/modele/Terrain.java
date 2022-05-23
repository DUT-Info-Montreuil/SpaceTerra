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
        loadChunks();
    }

    public void createChunks(){
        Chunk chunk = null;
        int y = 0;
        while (y < terrainData.getHeight() * terrainData.getTileHeight()){
            int x = 0;
            while(x < terrainData.getWidth() * terrainData.getTileWidth()){
                chunk = new Chunk(x, y, terrainData.getTileWidth(), terrainData.getTileHeight());
                chunks.add(chunk);
                x += chunk.getMaxWidth() * terrainData.getTileWidth();
            }
            y += chunk.getMaxHeight() * terrainData.getTileHeight();
        }
    }

    public void loadChunks(){
        createChunks();
        for(int c = 0; c < chunks.size(); c++){
            Chunk chunk = chunks.get(c);
            for(Layer layer : terrainData.getLayers()){
                int[][] data = new int[layer.getHeight()][layer.getWidth()];
                for(int y = 0; y < layer.getHeight(); y++){
                    for(int x = 0; x < layer.getWidth(); x++){
                        data[y][x] = layer.getData()[y * layer.getWidth() + x];
                    }
                }
                System.out.println(data.length * data[0].length + " / " + layer.getData().length);
                int dataPosY;
                int yPixels = chunk.getHitbox().getY().intValue();
                for(dataPosY = chunk.getHitbox().getY().intValue() / getTerrainData().getTileHeight(); dataPosY < (chunk.getHitbox().getY().intValue() / getTerrainData().getTileHeight()) + chunk.getMaxHeight(); dataPosY++){
                    yPixels += terrainData.getTileHeight();
                }
                /*
                int yPixels;
                for(yPixels = chunk.getHitbox().getY().intValue(); yPixels < chunk.getHitbox().getY().intValue() + (chunk.getMaxHeight() * terrainData.getTileHeight()); yPixels += terrainData.getTileHeight()){
                    int xPixels;
                    for (xPixels = chunk.getHitbox().getX().intValue(); xPixels < chunk.getHitbox().getX().intValue() + (chunk.getMaxWidth() * terrainData.getTileWidth()); xPixels += terrainData.getTileWidth()){

                    }
                }
                */
            }
        }
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

    public TerrainData getTerrainData() {
        return terrainData;
    }

    public ArrayList<Chunk> getChunks() {
        return chunks;
    }
}
