package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.*;
import modele.Block;
import modele.Terrain;
import modele.Player;
import vue.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    private TerrainView terrainView;
    public static Terrain terrain;
    PlayerMouseView playerMouseView;

    PlayerMouseObservator playerMouseObservator;
    private Timeline timeline;

    private Timeline timelineClick;
    public static Player player;
    private KeyHandler keyHandler;
    private ArrayList<Entity> entities;
    private MouseHandler mouseHandler;

    private InventoryView inventoryView;
    private GameCam2D camera;

    private InventoryObservator inventoryObservator;

    private PlayerMouse playerMouse;

    public static DebugView debugger;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entities = new ArrayList<>();
        Scene scene = new Scene(panneauDeJeu, 1000, 1000, Color.DARKBLUE);
        camera = new GameCam2D(panneauDeJeu);
        scene.setCamera(camera);
        terrain = new Terrain("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu, entities);
        terrainView.readMap(terrain);
        createEnnemies();

        PlayerView playerView = new PlayerView(player = new Player(3500, 2030, terrain), panneauDeJeu);
        entities.add(player);
        playerView.displayPlayer();
        inventoryView = new InventoryView(panneauDeJeu);
        keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        mouseHandler = new MouseHandler(panneauDeJeu);
        mouseHandler.mouseManager();

        playerMouse = new PlayerMouse(null);
        playerMouse.xProperty().bind(mouseHandler.getMouseXProperty());
        playerMouse.yProperty().bind(mouseHandler.getMouseYProperty());
        playerMouseView = new PlayerMouseView();
        playerMouseObservator = new PlayerMouseObservator(playerMouse, playerMouseView);


        terrainView.readEntity();
        debugger = new DebugView(panneauDeJeu);
        terrain.getBlocks().addListener(new TerrainObservator(terrainView));
        inventoryObservator = new InventoryObservator(inventoryView, player.getInventory(), panneauDeJeu);
        player.getInventory().getSlots().addListener(inventoryObservator);

        createTimelines();
    }



    public void createEnnemies() {
        Bingus bingus = new Bingus(3500, 2030, terrain);
        Florb florb = new Florb(3500, 2000, terrain);
        Bib bib = new Bib(4000, 2030, terrain);
        entities.add(bingus);
        entities.add(florb);
        entities.add(bib);
    }


    private boolean doOnce = false;
    public void createTimelines() {
        // 16.33 = 60 fps
        timeline = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
                    if(!doOnce){
                        camera.lookAt(player.getHitbox().getX(), player.getHitbox().getY());
                        doOnce = true;
                    }
                    entityLoop(); // Entity loop has to happen befor player movement so that gravity and position fixes are applied before moving
                    playerMovement();

                    inventoryObservator.refreshCurrentSlotView();
                    if (mouseHandler.isHasScrollUp()) {
                        player.getInventory().decrementSlot();
                        mouseHandler.setHasScrollUp(false);
                    } else if (mouseHandler.isHasScrollDown()) {
                        player.getInventory().incrementSlot();
                        mouseHandler.setHasScrollDown(false);
                    }


                    verifKeyTyped();
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        timelineClick = new Timeline

                (new KeyFrame(Duration.millis(20), actionEvent -> {
                    if(mouseHandler.isHasClickedLeft()){
                        playerMouseObservator.leftClick(player.getInventory(), inventoryView);
                        mouseHandler.setHasClickedLeft(false);
                    }
                    if (mouseHandler.isHasPressedLeft()) {
                        playerMouseObservator.leftPressed(player, terrain, inventoryView);
                    } else if (mouseHandler.isHasClickedRight()) {
                        mouseHandler.setHasClickedRight(false);
                    }

                    if(keyHandler.isInventoryKeyTyped()){
                        if(!inventoryView.isShow()) {
                            inventoryView.setShow(true);
                            inventoryView.displayAllSlotViews();
                            keyHandler.setInventoryKeyTyped(false);
                        }
                        else {
                            inventoryView.setShow(false);
                            inventoryView.displayAllSlotViews();
                            keyHandler.setInventoryKeyTyped(false);
                        }
                    }



                }));
        timelineClick.setCycleCount(Timeline.INDEFINITE);
        timelineClick.play();

    }




    public void playerMovement() {
        if(keyHandler.isSprintPressed() && !player.isRunning()) {
            player.setRunning(true);
            player.setSpeed(20);
        }

        if(!keyHandler.isSprintPressed() && player.isRunning()) {
            player.setRunning(false);
            player.setSpeed(7);
        }

        if (keyHandler.isLeftPressed()){
            player.movement(null, keyHandler.isLeftPressed(), false);
        }

        else if (keyHandler.isRightPressed()){
            player.movement(null, false, keyHandler.isRightPressed());
        }

        if (keyHandler.isUpPressed()) {
            if (!player.upCollisions() && player.isGrounded()) {
                player.setGravity(5);
                player.jump();
            } else if (player.isJumping()) {
                if (player.upCollisions()) {
                    player.stopJump();
                }
                else{
                    player.jump();
                }
            }
        }

        if (!keyHandler.isUpPressed())
            if (player.isJumping())
                player.stopJump();

        player.sideRightCollisions();
        player.sideLeftCollision();
    }

   public void entityLoop() {
        for (Entity ent : entities) {
            if (ent instanceof Player){}
                //checkSideBlock(player); // empeche le joueur de re rentrer dans un block apres s'etre fait sortir. aka enpeche de spammer le saut en se collant a un mur
            else {
                if (ent.sideLeftCollision() || ent.sideRightCollisions()) {
                    if (ent.isGrounded()) {
                        ent.setGravity(5);
                        ent.jump();
                    }

                    else if (ent.isJumping())
                        ent.jump();
                }
                else {
                    if (ent.isJumping()) {
                        ent.movement(player, !ent.sideLeftCollision(), !ent.sideRightCollisions());
                        ent.stopJump();
                    }
                }

                ent.movement(player, !ent.sideLeftCollision(), !ent.sideRightCollisions());
                ent.sideLeftCollision();
                ent.sideRightCollisions();
            }

            if (!ent.isGrounded()) {
                if (ent instanceof Player) {
                    if (!player.isJumping()) {
                        player.applyGrav();
                    }
                } else {
                    if(!ent.isFlying())
                        ent.applyGrav();
                }
            }
        }
    }




    public void verifKeyTyped() {
        if (keyHandler.isSlotOneTyped()) {
            player.getInventory().setCurrSlotNumber(0);
            keyHandler.setSlotOneTyped(false);
        } else if (keyHandler.isSlotTwoTyped()) {
            player.getInventory().setCurrSlotNumber(1);
            keyHandler.setSlotTwoTyped(false);
        } else if (keyHandler.isSlotThreeTyped()) {
            player.getInventory().setCurrSlotNumber(2);
            keyHandler.setSlotThreeTyped(false);
        } else if (keyHandler.isSlotFourTyped()) {
            player.getInventory().setCurrSlotNumber(3);
            keyHandler.setSlotFourTyped(false);
        } else if (keyHandler.isSlotFiveTyped()) {
            player.getInventory().setCurrSlotNumber(4);
            keyHandler.setSlotFiveTyped(false);
        } else if (keyHandler.isSlotSixTyped()) {
            player.getInventory().setCurrSlotNumber(5);
            keyHandler.setSlotSixTyped(false);
        } else if (keyHandler.isSlotSevenTyped()) {
            player.getInventory().setCurrSlotNumber(6);
            keyHandler.setSlotSevenTyped(false);
        } else if (keyHandler.isSlotEightTyped()) {
            player.getInventory().setCurrSlotNumber(7);
            keyHandler.setSlotEightTyped(false);
        } else if (keyHandler.isSlotNineTyped()) {
            player.getInventory().setCurrSlotNumber(8);
            keyHandler.setSlotNineTyped(false);
        } else if (keyHandler.isSlotTenTyped()) {
            player.getInventory().setCurrSlotNumber(9);
            keyHandler.setSlotTenTyped(false);
        }
    }

    public static int randomNum(int min, int max) {
        if (min == max)
            return max;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return rand;
    }
}
