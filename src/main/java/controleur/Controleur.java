package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import modele.*;
import modele.Block;
import modele.TerrainData;
import modele.Player;
import vue.PlayerView;
import vue.TerrainView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    private TerrainView terrainView;
    private TerrainData terrainData;
    private Terrain terrain;
    private Timeline timeline;

    private Timeline timelineClick;
    private Player player;
    private KeyHandler keyHandler;
    private ArrayList<Entity> entities;

    private MouseHandler mouseHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entities = new ArrayList<>();
        Scene scene = new Scene(panneauDeJeu, 1000, 1000, Color.DARKBLUE);
        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);
        terrainData = new TerrainData("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(terrainData);
        createBingus();
        terrainView.readEntity();
        PlayerView playerView = new PlayerView(player = new Player(10,2030), panneauDeJeu);
        entities.add(player);
        playerView.displayPlayer();
        terrainView.displayCollision(false, true, true, terrainData, player); // afficher ou non les collisions
        panneauDeJeu.getScene().getCamera().layoutXProperty().bind(player.getHitbox().getX().subtract(panneauDeJeu.getScene().getWidth() / 2));
        panneauDeJeu.getScene().getCamera().layoutYProperty().bind(player.getHitbox().getY().subtract(panneauDeJeu.getScene().getHeight() / 2));
        createTimelines();
        keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        mouseHandler = new MouseHandler(panneauDeJeu);
        mouseHandler.mouseManager();
        breakingManager();

        //terrainView.displayCollision(true, terrain, player);
        terrain = new Terrain(terrainData);
        terrainView.debugChunks(terrain.getChunks());
    }


    public void createBingus() {
        Bingus bingus = new Bingus(10, 2030);
        terrainView.addEntite(bingus);
        entities.add(bingus);
    }

    public void createTimelines() { // peut etre creer un nouveau thread pour opti ?
        // 16.33 = 60 fps
        timeline = new Timeline
                (new KeyFrame(Duration.millis(32.66), actionEvent -> {
                    playerMovement();
                    entityLoop();
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        timelineClick = new Timeline
                (new KeyFrame(Duration.millis(1000), actionEvent -> {
                    if (mouseHandler.isHasClickedLeft()) {
                        checkOnClicked();
                    }
                }));
        timelineClick.setCycleCount(Timeline.INDEFINITE);
        timelineClick.play();

    }


    public int checkSideBlock(Entity ent) { // -1 = left, 1 = right, 0 = none
        for (Block b : terrainData.getSolidBlocks()) {
            if (ent.sideCollisions(b) == 1) {
                return 1;
            } else if (ent.sideCollisions(b) == -1) {
                return -1;
            }
        }
        return 0;
    }

    public boolean checkGroundBlock(Entity ent) {
        for (Block b : terrainData.getSolidBlocks())
            if (ent.isGrounded(b)) {
                return true;
            }
        return false;
    }

    public boolean checkDistanceBlock(Entity ent, Block b){
        System.out.println(ent.distanceToBlock(b));
            if (ent.distanceToBlock(b) < 4) {
                return true;
            }
        return false;
    }


    public void playerMovement() {
        if (keyHandler.isUpPressed())//mouvements a mettre avec le player
            if (checkGroundBlock(player))
                player.jump();

            else if (player.isJumping())
                player.jump();

        if (!keyHandler.isUpPressed())
            if (player.isJumping())
                player.stopJump();

        if (keyHandler.isRightPressed() || keyHandler.isLeftPressed())
            player.movement(null, keyHandler.isLeftPressed() && !(checkSideBlock(player) == -1), keyHandler.isRightPressed() && !(checkSideBlock(player) == 1));
    }

    public void entityLoop() {
        for (Entity ent : entities) {
            if (ent instanceof Player)
                checkSideBlock(player); // empeche le joueur de re rentrer dans un block apres s'etre fait sortir. aka enpeche de spammer le saut en se collant a un mur
            else {
                ent.movement(player, (checkSideBlock(ent) != -1), (checkSideBlock(ent) != 1));
                checkSideBlock(ent);
            }
            if (!checkGroundBlock(ent)) {
                if (ent instanceof Player) {
                    if (!player.isJumping()) {
                        player.applyGrav();
                    }
                } else {
                    ent.applyGrav();
                }
            }
        }
    }

    public void breakingManager() {
        this.terrainData.getBlocks().addListener((ListChangeListener<Block>) change -> {
            while (change.next()) {
                for (Block b : change.getRemoved()) {
                    this.terrainView.deleteBlock(b);
                }
            }
        });
    }

    public void checkOnClicked() {
        ArrayList<Block> deletedBlocks = new ArrayList<>();
        for (Block b : terrainData.getBlocks()) {
            if (mouseHandler.getMouseX() < b.getHitX() + b.getTile().getHitbox().getWidth() && mouseHandler.getMouseX() > b.getHitX() && mouseHandler.getMouseY() < b.getHitY() + b.getTile().getHitbox().getHeight() && mouseHandler.getMouseY() > b.getHitY()) {
                if(checkDistanceBlock(player, b)){
                    System.out.println("ok");
                    b.setPvs(b.getPvs() - 1);
                    System.out.println(b.getPvs());
                    if (b.getPvs() <= 0) {
                        deletedBlocks.add(b);
                    }
                    break;
                }
                /*
                Rectangle r = new Rectangle(b.getHitX(), b.getHitY(), b.getTile().getHitbox().getWidth(), b.getTile().getHitbox().getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                panneauDeJeu.getChildren().add(r);
                */
            }
        }
        terrainData.deleteBlock(deletedBlocks);
        terrainData.deleteSolidBlock(deletedBlocks);
    }
}
