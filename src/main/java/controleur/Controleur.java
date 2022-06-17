package controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import modele.*;
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

    private CraftInventoryView craftInventoryView;

    private Timeline timelineClick;
    public static Player player;
    private KeyHandler keyHandler;
    private ArrayList<Entity> entities;
    private MouseHandler mouseHandler;

    private PlayerInventoryView playerInventoryView;
    private GameCam2D camera;

    private PlayerInventoryObservator playerInventoryObservator;

    private CraftInventoryObservator craftInventoryObservator;

    private ResultSlotObservator resultSlotObservator;

    public static PlayerMouse playerMouse;

    public static DebugView debugger;

    private DeletedSlotView deletedSlotView;

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
        playerInventoryView = new PlayerInventoryView(panneauDeJeu);
        craftInventoryView = new CraftInventoryView(panneauDeJeu);
        keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        mouseHandler = new MouseHandler(panneauDeJeu);
        mouseHandler.mouseManager();


        playerMouse = new PlayerMouse(null);
        playerMouse.xProperty().bind(mouseHandler.getMouseXProperty());
        playerMouse.yProperty().bind(mouseHandler.getMouseYProperty());
        playerMouseView = new PlayerMouseView(panneauDeJeu);
        playerMouseObservator = new PlayerMouseObservator(playerMouse, playerMouseView);


        terrainView.readEntity();
        debugger = new DebugView(panneauDeJeu);
        terrain.getBlocks().addListener(new TerrainObservator(terrainView));
        playerInventoryObservator = new PlayerInventoryObservator(playerInventoryView, player.getPlayerInventory(), panneauDeJeu);
        player.getPlayerInventory().getSlots().addListener(playerInventoryObservator);
        craftInventoryObservator = new CraftInventoryObservator(craftInventoryView, player.getCraftInventory(), panneauDeJeu);
        resultSlotObservator = new ResultSlotObservator(9, player.getCraftInventory(), panneauDeJeu, craftInventoryView);
        player.getCraftInventory().getSlots().addListener(craftInventoryObservator);
        HpBarView hpBarView = new HpBarView(panneauDeJeu, 750, 100, player, 20);
        hpBarView.initialize();

        deletedSlotView = new DeletedSlotView(panneauDeJeu, playerInventoryView);

        createTimelines();
    }


    public void createEnnemies() {
            //Bingus bingus = new Bingus(3500, 2030, terrain);
        Florb florb = new Florb(3500, 1980, terrain);
        Bib bib = new Bib(4000, 2030, terrain);
       // entities.add(bingus);
        entities.add(florb);
        entities.add(bib);
    }


    private boolean doOnce = false;

    public void createTimelines() {
        // 16.33 = 60 fps
        timeline = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
                    if (!doOnce) {
                        camera.lookAt(player.getHitbox().getX(), player.getHitbox().getY());
                        doOnce = true;
                    }
                    entityLoop(); // Entity loop has to happen befor player movement so that gravity and position fixes are applied before moving
                    playerMovement();

                    playerInventoryObservator.refreshCurrentSlotView();
                    if (mouseHandler.isHasScrollUp()) {
                        player.getPlayerInventory().decrementSlot();
                        mouseHandler.setHasScrollUp(false);
                    } else if (mouseHandler.isHasScrollDown()) {
                        player.getPlayerInventory().incrementSlot();
                        mouseHandler.setHasScrollDown(false);
                    }


                    verifKeyTyped();
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        timelineClick = new Timeline
                (new KeyFrame(Duration.millis(20), actionEvent -> {
                    if (mouseHandler.isHasClickedLeft()) {
                        if(playerInventoryView.isDisplay()){
                            playerMouseObservator.leftClickInventory(player.getPlayerInventory(), playerInventoryView, deletedSlotView);
                            playerMouseObservator.leftClickInventory(player.getCraftInventory(), craftInventoryView, deletedSlotView);
                            playerMouseObservator.leftClickResultSlot(resultSlotObservator.getResultSlotView(), player.getCraftInventory().getResultSlot(), player.getCraftInventory());
                            craftInventoryObservator.updateResultSlotView(player.getCraftInventory(), craftInventoryView, resultSlotObservator);
                        }
                        else {
                            playerMouseObservator.changeCurrSlot(player.getPlayerInventory(), playerInventoryView);
                        }

                        mouseHandler.setHasClickedLeft(false);
                    }
                    if (mouseHandler.isHasPressedLeft()) {
                        playerMouseObservator.leftPressed(player, terrain, playerInventoryView);
                        playerMouseObservator.leftPressed(player, terrain, craftInventoryView);
                    } else if (mouseHandler.isHasClickedRight()) {
                        if(playerInventoryView.isDisplay()) {
                            playerMouseObservator.rightClickInventory(player.getPlayerInventory(), playerInventoryView);
                            playerMouseObservator.rightClickInventory(player.getCraftInventory(), craftInventoryView);
                            playerMouseObservator.rightClickDeletedSlotView(deletedSlotView);
                            playerMouseObservator.rightClickResultSlot(resultSlotObservator.getResultSlotView(), player.getCraftInventory().getResultSlot(), player.getCraftInventory());
                            craftInventoryObservator.updateResultSlotView(player.getCraftInventory(), craftInventoryView, resultSlotObservator);
                        }

                        mouseHandler.setHasClickedRight(false);
                    }

                    if (keyHandler.isInventoryKeyTyped()) {
                        if (!playerInventoryView.isDisplay()) {
                            playerInventoryView.setDisplay(true);
                            playerInventoryView.displayAllSlotViews();
                            craftInventoryView.setDisplay(true);
                            craftInventoryObservator.updateResultSlotView(player.getCraftInventory(), craftInventoryView, resultSlotObservator);
                            deletedSlotView.display(true);
                            keyHandler.setInventoryKeyTyped(false);

                        } else {
                            playerMouseObservator.inventoryclosed(player);
                            playerInventoryView.setDisplay(false);
                            playerInventoryView.displayAllSlotViews();
                            craftInventoryView.setDisplay(false);
                            craftInventoryObservator.updateResultSlotView(player.getCraftInventory(), craftInventoryView, resultSlotObservator);
                            deletedSlotView.display(false);
                            keyHandler.setInventoryKeyTyped(false);
                        }
                    }
                    playerMouseObservator.displayItemName(player.getPlayerInventory(), playerInventoryView);
                }));
        timelineClick.setCycleCount(Timeline.INDEFINITE);
        timelineClick.play();
    }


    public void playerMovement() {
        if (keyHandler.isSprintPressed() && !player.isRunning()) {
            player.setRunning(true);
            player.setSpeed(14);
        }

        if (!keyHandler.isSprintPressed() && player.isRunning()) {
            player.setRunning(false);
            player.setSpeed(7);
        }

        if (keyHandler.isLeftPressed()) {
            player.movement(null, true, false);
        } else if (keyHandler.isRightPressed()) {
            player.movement(null, false, true);
        }

        if (keyHandler.isUpPressed()) {
            if (!player.upCollisions() && player.isGrounded()) {
                player.setGravity(5);
                player.jump();
            } else if (player.isJumping()) {
                if (player.upCollisions()) {
                    player.stopJump();
                } else {
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
            //camera.translateToLook(ent.getHitbox().getX(), ent.getHitbox().getY(), 1000);
            if (ent instanceof Player) {
            }
            //checkSideBlock(player); // empeche le joueur de re rentrer dans un block apres s'etre fait sortir. aka enpeche de spammer le saut en se collant a un mur
            else {
               // if (ent.sideLeftCollision() || ent.sideRightCollisions()) {
                    if (ent.isGrounded()) {
                        ent.setGravity(5);
                        ent.jump();
                    } else if (ent.isJumping()){
                        if(ent.upCollisions()){
                            ent.stopJump();
                        }
                    }
                        ent.jump();
               // } else {
                    if (ent.isJumping()) {
                        ent.movement(player, false, false);
                        ent.stopJump();
                    }
               // }



                ent.movement(player, false, false);
                ent.sideLeftCollision();
                ent.sideRightCollisions();
            }

            if (!ent.isGrounded()) {
                if (ent instanceof Player) {
                    if (!player.isJumping()) {
                        player.applyGrav();
                    }
                } else {
                    if (!ent.isFlying())
                        ent.applyGrav();
                }
            }
        }
    }


    public void verifKeyTyped() {
        if (keyHandler.isSlotOneTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(0);
            keyHandler.setSlotOneTyped(false);
        } else if (keyHandler.isSlotTwoTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(1);
            keyHandler.setSlotTwoTyped(false);
        } else if (keyHandler.isSlotThreeTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(2);
            keyHandler.setSlotThreeTyped(false);
        } else if (keyHandler.isSlotFourTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(3);
            keyHandler.setSlotFourTyped(false);
        } else if (keyHandler.isSlotFiveTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(4);
            keyHandler.setSlotFiveTyped(false);
        } else if (keyHandler.isSlotSixTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(5);
            keyHandler.setSlotSixTyped(false);
        } else if (keyHandler.isSlotSevenTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(6);
            keyHandler.setSlotSevenTyped(false);
        } else if (keyHandler.isSlotEightTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(7);
            keyHandler.setSlotEightTyped(false);
        } else if (keyHandler.isSlotNineTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(8);
            keyHandler.setSlotNineTyped(false);
        } else if (keyHandler.isSlotTenTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(9);
            keyHandler.setSlotTenTyped(false);
        }
    }
}
