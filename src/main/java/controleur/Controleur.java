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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.*;
import modele.Block;
import modele.Terrain;
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
    private Terrain terrain;
    private Timeline timeline;
    private Player player;
    private KeyHandler keyHandler;
    private ArrayList<Entity> entities;
    private Bingus bingus;

    private MouseHandler mouseHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entities = new ArrayList<>();
        Scene scene = new Scene(panneauDeJeu, 1000,1000, Color.DARKBLUE);
        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);
        terrain = new Terrain("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(terrain);
        bingus = creerBingus();
        terrainView.readEntity();
        PlayerView playerView = new PlayerView(player = new Player(2030,10), panneauDeJeu);
        playerView.displayPlayer();
        terrainView.displayCollision(false, true, true, terrain, player); // afficher ou non les collisions
        panneauDeJeu.getScene().getCamera().layoutXProperty().bind(player.getHitbox().getX().subtract(panneauDeJeu.getScene().getWidth()/2));
        panneauDeJeu.getScene().getCamera().layoutYProperty().bind(player.getHitbox().getY().subtract(panneauDeJeu.getScene().getHeight()/2));
        creerTimeline();
        keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        mouseHandler = new MouseHandler(panneauDeJeu);
        mouseHandler.mouseManager();
        breackingManager();

        //terrainView.displayCollision(true, terrain, player);
    }




    public Bingus creerBingus(){
        Bingus bingus = new Bingus(10,2030);
        terrainView.addEntite(bingus);
        entities.add(bingus);
        return bingus;
    }

    public void creerTimeline() { // peut etre creer un nouveau thread pour opti ?
        // 16.33 = 60 fps
        timeline = new Timeline(new KeyFrame(Duration.millis(32.66), actionEvent -> {
           if (mouseHandler.isHasClickedLeft()){
                checkOnLeftClicked();
                breackingManager();
               System.out.println(mouseHandler.isHasClickedLeft());
                mouseHandler.setHasClickedLeft(false);
            }



            if(keyHandler.isUpPressed())//mouvements a mettre avec le player
                if(checkGroundBlock())
                    player.jump();

                else if(player.isJumping())
                    player.jump();

            if(!keyHandler.isUpPressed())
                if(player.isJumping())
                    player.stopJump();

            if(keyHandler.isRightPressed() || keyHandler.isLeftPressed())
                player.movement(null, keyHandler.isLeftPressed() && !(checkSideBlock() == -1), keyHandler.isRightPressed() && !(checkSideBlock() == 1));

            checkSideBlock(); // empeche le joueur de re rentrer dans un block apres s'etre fait sortir. aka enpeche de spammer le saut en se collant a un mur
            if(!checkGroundBlock() && !player.isJumping())
                player.applyGrav();
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
    public int checkSideBlock2(Entity ent){ // -1 = left, 1 = right, 0 = none
        for(Block b : terrain.getSolidBlocks()){
            if(ent.sideCollisions(b) == 1){
                return 1;
            }
            else if (ent.sideCollisions(b) == -1){
                return -1;
            }
        }
        return 0;
    }

    public boolean checkGroundBlock2(Entity ent){
        for (Block b: terrain.getSolidBlocks())
            if(ent.isGrounded(b)){
                return true;
            }
        return false;
    }

    public void breackingManager() {
        this.terrain.getBlocks().addListener((ListChangeListener<Block>) change -> {
            System.out.println("oui1");
            while (change.next()) {
                for (Block b : change.getRemoved()) {
                    this.terrainView.deleteBlock(b);

                }
            }
        });
        this.terrain.getSolidBlocks().addListener((ListChangeListener<Block>) change -> {
            while (change.next()) {
                for (Block b : change.getRemoved()) {
                    this.terrainView.deleteSolidBlock(b);
                    System.out.println("oui2");
                }
            }
        });
    }
    public void placingManager() {
        this.terrain.getBlocks().addListener((ListChangeListener<Block>) change -> {
            System.out.println("oui9");
            while (change.next()) {
                for (Block b : change.getAddedSubList()) {
                    this.terrainView.placeBlock(b);

                }
            }
        });
        this.terrain.getSolidBlocks().addListener((ListChangeListener<Block>) change -> {
            while (change.next()) {
                for (Block b : change.getAddedSubList()) {
                    this.terrainView.placeBlock(b);
                    System.out.println("oui10");
                }
            }
        });
    }

    public void checkOnLeftClicked() {
        ArrayList<Block> deletedBlocks = new ArrayList<>();
                for (Block b : terrain.getBlocks()) {
                    if (mouseHandler.getMouseX() < b.getHitX()+b.getTile().getHitbox().getWidth() && mouseHandler.getMouseX() > b.getHitX() && mouseHandler.getMouseY() < b.getHitY()+b.getTile().getHitbox().getHeight() && mouseHandler.getMouseY() > b.getHitY()) {
                        Rectangle r = new Rectangle(b.getHitX(), b.getHitY(), b.getTile().getHitbox().getWidth(), b.getTile().getHitbox().getHeight());
                        r.setFill(Color.TRANSPARENT);
                        r.setStroke(Color.BLACK);
                        panneauDeJeu.getChildren().add(r);
                        deletedBlocks.add(b);
                        System.out.println(b);
                        System.out.println("oui3");
                    }

                }
        terrain.deleteBlock(deletedBlocks);
                for (Block b : terrain.getSolidBlocks()) {
                    if (mouseHandler.getMouseX() < b.getHitX()+b.getTile().getHitbox().getWidth() && mouseHandler.getMouseX() > b.getHitX() && mouseHandler.getMouseY() < b.getHitY()+b.getTile().getHitbox().getHeight() && mouseHandler.getMouseY() > b.getHitY()) {
                        deletedBlocks.add(b);
                        System.out.println("oui4");
                    }
                }
        terrain.deleteSolidBlock(deletedBlocks);

    }

   public void checkOnRightClicked() {
        if(isBlock(mouseHandler.getMouseX(), mouseHandler.getMouseY())){
            //Block b = new Block();
        }
    }



    public boolean isBlock(int x, int y){
        for(Block b : terrain.getBlocks()){
            if(x > b.getHitX() && x < b.getHitX() + b.getTile().getHitbox().getWidth() && y > b.getHitY() && y < b.getHitY() + b.getTile().getHitbox().getHeight()){
                return true;
            }
        }
        return  false;
    }
}
