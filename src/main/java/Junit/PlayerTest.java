package Junit;


import modele.JsonGameLoader;
import modele.Player;
import modele.Terrain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlayerTest{

    private final Terrain testTerrain = new Terrain(new JsonGameLoader("src/main/resources/Map/bigTest.json"));
    private final Player testPlayer = new Player(100, 2080, testTerrain);

    @Test
    @DisplayName("Speed changes when sprint")
    public void sprintTest(){
        int walkSpeed = 7;
        int sprintSpeed = 14;
        testPlayer.setRunning(false);
        testPlayer.setRunning(true);
        Assertions.assertEquals(sprintSpeed, testPlayer.getSpeed());
    }

    @Test
    @DisplayName("Movement Test")
    public void moveTest(){
        int firstPosX = (int)testPlayer.getHitbox().getX();
        testPlayer.movement(null, false, true);
        Assertions.assertEquals(firstPosX + testPlayer.getSpeed(), (int)testPlayer.getHitbox().getX());

        int secondPosX = (int)testPlayer.getHitbox().getX();
        testPlayer.movement(null, true, false);
        Assertions.assertEquals(secondPosX - testPlayer.getSpeed(), (int)testPlayer.getHitbox().getX());
    }

    @Test
    @DisplayName("Ground collisions test")
    public void groundTest(){
        assertTrue(testPlayer.isGrounded());
        for(int i = 0; i < 10; i++)
            testPlayer.jump();
        assertFalse(testPlayer.isGrounded());
    }
}
