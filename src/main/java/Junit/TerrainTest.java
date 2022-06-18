package Junit;

import modele.JsonGameLoader;
import modele.Terrain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TerrainTest {

    private JsonGameLoader testLoader = new JsonGameLoader("src/main/resources/Map/bigTest.json");
    private Terrain testTerrain = new Terrain(testLoader);

    @Test
    @DisplayName("Loader loads data into terrain test")
    public void dataTest(){
        assertEquals(testTerrain.getWidth(), testLoader.getWidth());
        assertEquals(testTerrain.getHeight(), testLoader.getHeight());
        assertEquals(testTerrain.getLayers(), testLoader.getLayers());
        assertEquals(testTerrain.getTileHeight(), testLoader.getTileHeight());
        assertEquals(testTerrain.getTileWidth(), testLoader.getTileWidth());
    }

    @Test
    @DisplayName("Terrain blocks are made")
    public void blockListTest(){
        assertEquals(testTerrain.getWidth() * testTerrain.getHeight() * testTerrain.getLayers().size(), testTerrain.getBlocks().size());
    }

    @Test
    @DisplayName("Blocks get deleted when given a position in pixel")
    public void deleteBlockTest(){
        assertNotNull(testTerrain.getBlock(10, 2130));
        testTerrain.deleteBlock(testTerrain.getBlock(10, 2130));
        assertNull(testTerrain.getBlock(10, 2130));
    }
}
