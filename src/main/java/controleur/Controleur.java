package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import modele.Block;
import modele.Terrain;
import modele.Player;
import vue.PlayerView;
import vue.TerrainView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    private TerrainView terrainView;
    private Terrain terrain;
    private Timeline timeline;
    private Player player;
    private KeyHandler keyHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scene scene = new Scene(panneauDeJeu, 1000,1000, Color.DARKBLUE);
        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);
        terrain = new Terrain("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(terrain);
        PlayerView playerView = new PlayerView(player = new Player(), panneauDeJeu);
        playerView.displayPlayer();
        terrainView.displayCollision(false, terrain, player); // afficher ou non les collisions
        panneauDeJeu.getScene().getCamera().layoutXProperty().bind(player.xProperty().subtract(panneauDeJeu.getScene().getWidth()/2));
        panneauDeJeu.getScene().getCamera().layoutYProperty().bind(player.yProperty().subtract(panneauDeJeu.getScene().getHeight()/2));
        creerTimeline();
        keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        //terrainView.displayCollision(true, terrain, player);
    }


    public void creerTimeline() { // peut etre creer un nouveau thread pour opti ?
        timeline = new Timeline(new KeyFrame(Duration.millis(32.66), new EventHandler<ActionEvent>() { // 16.33 = 60 fps
            @Override
            public void handle(ActionEvent actionEvent) {
                if(keyHandler.isUpPressed())//mouvements a mettre avec le player
                    if(checkGroundBlock())
                        player.jump();

                    else if(player.isJumping())
                        player.jump();

                if(!keyHandler.isUpPressed())
                    if(player.isJumping())
                        player.stopJump();

                if(keyHandler.isRightPressed() || keyHandler.isLeftPressed())
                    player.horizontalMovement(keyHandler.isLeftPressed() && !(checkSideBlock() == -1), keyHandler.isRightPressed() && !(checkSideBlock() == 1));

                checkSideBlock(); // empeche le joueur de re rentrer dans un block apres s'etre fait sortir. aka enpeche de spammer le saut en se collant a un mur
                if(!checkGroundBlock() && !player.isJumping())
                    player.applyGrav();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public boolean checkGroundBlock(){
        for (Block b: terrain.getSolidBlocks())
            if(player.isGrounded(b)){
                return true;
            }
        return false;
    }

    // J'utilise des int parce que c'est plus leger que des string donc niveau opti c'est un peu mieu (meme si la différence est minime)
    public int checkSideBlock(){ // -1 = left, 1 = right, 0 = none
        for(Block b : terrain.getSolidBlocks()){
            if(player.sideCollisions(b) == 1){
                return 1;
            }
            else if (player.sideCollisions(b) == -1){
                return -1;
            }
        }
        return 0;
    }
}
