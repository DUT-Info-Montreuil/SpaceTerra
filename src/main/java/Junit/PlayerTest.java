package Junit;


import modele.JsonGameLoader;
import modele.Player;
import modele.Terrain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlayerTest{

    private Terrain testTerrain = new Terrain(new JsonGameLoader("src/main/resources/Map/bigTest.json"));
    private Player testPlayer = new Player(100, 2030, testTerrain);

    @Test
    @DisplayName("Speed changes when sprint")
    public void sprintTest(){
        int walkSpeed = 7;
        int sprintSpeed = 14;
        testPlayer.setRunning(false);
        testPlayer.setRunning(true);
        assertEquals(sprintSpeed, testPlayer.getSpeed());
    }

    @Test
    @DisplayName("Movement Test")
    public void moveTest(){
        int firstPosX = (int)testPlayer.getHitbox().getX();
        testPlayer.movement(null, false, true);
        assertEquals(firstPosX + testPlayer.getSpeed(), (int)testPlayer.getHitbox().getX());

        int secondPosX = (int)testPlayer.getHitbox().getX();
        testPlayer.movement(null, true, false);
        assertEquals(secondPosX - testPlayer.getSpeed(), (int)testPlayer.getHitbox().getX());
    }
}
