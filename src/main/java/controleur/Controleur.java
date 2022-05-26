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
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;
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
        creerTimeline();
        KeyHandler keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
    }

    public void creerJoueur() {
        player = new Player();
        player.setXProperty(10);
        player.setYProperty(2063);

        ImageView spriteJoueur = new ImageView(player.getImage());
        spriteJoueur.xProperty().bind(player.getXProperty());
        spriteJoueur.yProperty().bind(player.getYProperty());
        panneauDeJeu.getChildren().add(spriteJoueur);
    }

    public void creerTimeline() {
        panneauDeJeu.getScene().getCamera().layoutXProperty().bind(player.getXProperty().subtract(panneauDeJeu.getScene().getWidth()/2));
        panneauDeJeu.getScene().getCamera().layoutYProperty().bind(player.getYProperty().subtract(panneauDeJeu.getScene().getHeight()/2));

        timeline = new Timeline(new KeyFrame(Duration.millis(16.33), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Circle c = new Circle();
                c.setRadius(2);
                c.setFill(Color.RED);
                c.setLayoutX(player.getXProperty().intValue());
                c.setLayoutY(player.getYProperty().intValue());
                panneauDeJeu.getChildren().add(c);
                if(KeyHandler.rightPressed || KeyHandler.leftPressed){
                    player.horizontalMovement(KeyHandler.leftPressed, KeyHandler.rightPressed);
                }

                if (KeyHandler.upPressed){
                    player.jump();
                    if (player.isGrounded()) {
                        player.setVitesseY(0);
                        KeyHandler.upPressed = false;
                    }
                }
                else if (player.isGrounded()) {
                    player.setVitesseY(6);
                }


            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
