package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Terrain {

    private JSONObject map;
    private ArrayList<Layer> layers;
    private ObservableList<Block> blocks;

    private ObservableList<Block> solidBlocks;
    private Tileset tileSet;
    private int height, width;

    public ObservableList<Block> getBlocks() {
        return blocks;
    }


    public Terrain(String mapPath) {
        blocks = FXCollections.observableArrayList();
        solidBlocks = FXCollections.observableArrayList();
        for (int i = 0; i < 250000; i++) {
            blocks.add(null);
        }
        loadMap(mapPath);
        parseLayers(this.map);
        height = ((Long) map.get("height")).intValue();
        width = ((Long) map.get("width")).intValue();
        tileSet = new Tileset((JSONObject) ((JSONArray) map.get("tilesets")).get(0));
        loadBlocks();
    }


    public void loadMap(String mapPath) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //  src/main/resources/Map/map.json
        try (FileReader reader = new FileReader(mapPath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            map = (JSONObject) obj;
            //System.out.println(map);
            System.out.println("Map loaded");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseLayers(JSONObject map) {
        layers = new ArrayList<>();
        JSONArray layersData = (JSONArray) map.get("layers");

        Iterator<JSONObject> layersIterator = layersData.iterator();

        while (layersIterator.hasNext()) {
            JSONObject currentLayer = layersIterator.next();
            layers.add(new Layer(currentLayer));
        }
        System.out.println("All layers loaded");
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public Tileset getTileset() {
        return tileSet;
    }

    public ObservableList<Block> getSolidBlocks() {
        return solidBlocks;
    }

    public void loadBlocks() {
        for (int l = 0; l < layers.size(); ++l) {
            int i = 0;
            if (((Layer) layers.get(l)).getIsVisible()) {
                Layer currentLayer = (Layer) layers.get(l);
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
                            Iterator var9 = tileSet.getTiles().iterator();

                            while (var9.hasNext()) {
                                Tile currTile = (Tile) var9.next();

                                if (data[t] == currTile.getId()) {
                                    Block b = new Block(currTile, x, y);
                                    blocks.set(i, b);
                                    System.out.println(i);
                                    if (b.getTile().getHitbox().isSolid()) {
                                        solidBlocks.add(b);
                                    }
                                }
                            }
                            //System.out.println(blocks.get(t));
                        }
                        i++;
                        x += 32;
                        ++currentWidth;
                    }
                } catch (NullPointerException var12) {
                    System.out.println("null");
                }
            }
            System.out.println(i);
            //System.out.println(blocks);
        }
    }

    public void deleteBlock(Block block) {
        System.out.println(this.getBlocks().indexOf(block));
        this.getBlocks().set(this.getBlocks().indexOf(block), null);
    }

    public void deleteSolidBlock(Block block) {
        this.getSolidBlocks().set(this.getSolidBlocks().indexOf(block), null);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Block getBlock(int x, int y) {
        System.out.println("y : " + y / 32);
        System.out.println("x : " + x / 32);
        System.out.println(y / 32 * 500 + x / 32);
        System.out.println(this.getBlocks().get(y / 32 * this.getWidth() + x / 32));
        Block b1 = null;
        for (Block b : this.blocks) {
            if (b != null) {
                if (x < b.getHitX() + b.getTile().getHitbox().getWidth() && x > b.getHitX() && y < b.getHitY() + b.getTile().getHitbox().getHeight() && y > b.getHitY()) {
                    b1 = b;
                }
            }
        }
        System.out.println(this.getBlocks().indexOf(b1));
        System.out.println(blocks.size());


        return this.getBlocks().get(y / 32 * this.getWidth() + x / 32);
    }

}
