package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private double vmarche;
    private double vitesseY;

    private static double g = 0.1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain = new Terrain("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(terrain);
        creerJoueur();
        creerTimeline();
        KeyHandler keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        vitesseY = 5;
        vmarche = 10;
    }

    public void creerJoueur() {
        player = new Player();
        player.setXProperty(10);
        player.setYProperty(80);

        ImageView spriteJoueur = new ImageView(player.getImage());
        spriteJoueur.xProperty().bind(player.getXProperty());
        spriteJoueur.yProperty().bind(player.getYProperty());
        panneauDeJeu.getChildren().add(spriteJoueur);
    }

    public void creerTimeline() {

        timeline = new Timeline(new KeyFrame(Duration.millis(16.33), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(player.isGrounded());
                if(KeyHandler.rightPressed || KeyHandler.leftPressed){
                    player.horizontalMovement(KeyHandler.leftPressed, KeyHandler.rightPressed);
                }
                if (KeyHandler.upPressed){
                    player.jump();
                    //joueur.setYProperty(joueur.getYProperty().doubleValue() - vitesseY);
                    //vitesseY -= g;
                }
                //if (KeyHandler.downPressed){
                    //joueur.setYProperty(joueur.getYProperty().doubleValue() + vmarche);
                //}
                //if (joueur.getYProperty().doubleValue() > 80) {
                    //vitesseY = 0;
                //}
                //panneauDeJeu.getScene().getCamera().layoutXProperty().bind(joueur.getXProperty());
                //panneauDeJeu.getScene().getCamera().layoutYProperty().bind(joueur.getYProperty());
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

}
