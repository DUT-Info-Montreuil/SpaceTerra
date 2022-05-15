package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;
import modele.Block;
import modele.Terrain;
import modele.Player;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scene scene = new Scene(panneauDeJeu, 1000,1000, Color.DARKBLUE);
        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);
        terrain = new Terrain("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(terrain);
        creerJoueur();
        terrainView.displayCollision(false, terrain, player); // afficher ou non les collisions
        panneauDeJeu.getScene().getCamera().layoutXProperty().bind(player.getXProperty().subtract(panneauDeJeu.getScene().getWidth()/2));
        panneauDeJeu.getScene().getCamera().layoutYProperty().bind(player.getYProperty().subtract(panneauDeJeu.getScene().getHeight()/2));
        creerTimeline();
        KeyHandler keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
    }

    public void creerJoueur() {
        player = new Player();
        player.setXProperty(10);
        player.setYProperty(2030);
        ImageView spriteJoueur = new ImageView(player.getImage());
        spriteJoueur.xProperty().bind(player.getXProperty());
        spriteJoueur.yProperty().bind(player.getYProperty());
        panneauDeJeu.getChildren().add(spriteJoueur);
    }

    public void creerTimeline() { // peut etre creer un nouveau thread pour opti ?
        timeline = new Timeline(new KeyFrame(Duration.millis(32.66), new EventHandler<ActionEvent>() { // 16.33 = 60 fps
            @Override
            public void handle(ActionEvent actionEvent) {
                if(KeyHandler.rightPressed || KeyHandler.leftPressed){
                    player.horizontalMovement(KeyHandler.leftPressed && !(getSideBlock() == -1), KeyHandler.rightPressed && !(getSideBlock() == 1));
                }
                if(KeyHandler.upPressed)
                    if(getGroundBlock())
                        player.jump();
                if(!getGroundBlock())
                    player.applyGrav();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public boolean getGroundBlock(){
        for (Block b: terrain.getSolidBlocks())
            if(player.isGrounded(b)){
                return true;
            }
        return false;
    }

    // J'utilise des int parce que c'est plus leger que des string donc niveau opti c'est un peu mieu (meme si la différence est minime)
    public int getSideBlock(){ // -1 = left, 1 = right, 0 = none
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
