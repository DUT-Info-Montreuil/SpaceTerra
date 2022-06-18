package Junit;


import modele.JsonGameLoader;
import modele.Player;
import modele.Terrain;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import static org.junit.Assert.assertEquals;

public class PlayerTest{

    Player testPlayer = new Player(10, 10, new Terrain(new JsonGameLoader("src/main/resources/Map/bigTest.json")));

    @Test
    @DisplayName("Speed changes when sprint")
    public void SprintTest(){
        int walkSpeed = 7;
        int sprintSpeed = 14;
        testPlayer.setRunning(false);
        testPlayer.setRunning(true);
        assertEquals(sprintSpeed, testPlayer.getSpeed());
    }
}
