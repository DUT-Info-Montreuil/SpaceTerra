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
import modele.Player;
import vue.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane panneauDeJeu;
    public static Environnement env;
    private TerrainView terrainView;
    PlayerMouseView playerMouseView;
    PlayerMouseObservator playerMouseObservator;
    private Timeline timelineCamera;
    private Timeline timelineGame;
    private CraftInventoryView craftInventoryView;
    private Timeline timelineInventory;

    public static Player player;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private PlayerInventoryView playerInventoryView;
    private GameCam2D camera;
    private PlayerInventoryObservator playerInventoryObservator;
    private CraftInventoryObservator craftInventoryObservator;
    private ResultSlotObservator resultSlotObservator;
    public static PlayerMouse playerMouse;
    public static DebugView debugger;
    private DeletedSlotView deletedSlotView;
    private HpBarView hpBarView;
    private boolean doOnce = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scene scene = new Scene(panneauDeJeu, 1000, 1000, Color.DARKBLUE);
        env = new Environnement();
        camera = new GameCam2D(panneauDeJeu);
        scene.setCamera(camera);
        terrainView = new TerrainView(panneauDeJeu);
        terrainView.readMap(env.getTerrain());
        terrainView.readEntity();
        env.getTerrain().getBlocks().addListener(new TerrainObservator(terrainView));

        player = new Player(3500, 2030, env.getTerrain());
        PlayerView playerView = new PlayerView(player, panneauDeJeu);
        env.getEntities().add(player);
        createEnnemies();
        playerView.displayPlayer();
        playerInventoryView = new PlayerInventoryView(panneauDeJeu);
        craftInventoryView = new CraftInventoryView(panneauDeJeu);
        playerInventoryObservator = new PlayerInventoryObservator(playerInventoryView, player.getPlayerInventory(), panneauDeJeu);
        player.getPlayerInventory().getSlots().addListener(playerInventoryObservator);
        craftInventoryObservator = new CraftInventoryObservator(craftInventoryView, player.getCraftInventory(), panneauDeJeu);
        resultSlotObservator = new ResultSlotObservator(9, player.getCraftInventory(), panneauDeJeu, craftInventoryView);
        player.getCraftInventory().getSlots().addListener(craftInventoryObservator);
        deletedSlotView = new DeletedSlotView(panneauDeJeu, playerInventoryView);

        keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        mouseHandler = new MouseHandler(panneauDeJeu);
        mouseHandler.mouseManager();

        playerMouse = new PlayerMouse(null);
        playerMouse.xProperty().bind(mouseHandler.getMouseXProperty());
        playerMouse.yProperty().bind(mouseHandler.getMouseYProperty());
        playerMouseView = new PlayerMouseView(panneauDeJeu);
        playerMouseObservator = new PlayerMouseObservator(playerMouse, playerMouseView);

        debugger = new DebugView(panneauDeJeu);

        hpBarView = new HpBarView(panneauDeJeu, 750, 100, player, 20);
        hpBarView.initialize();

        createTimelines();
    }


    public void createEnnemies() {
        Bingus bingus = new Bingus(3500, 2030, this.env.getTerrain());
        Florb florb = new Florb(3500, 2000, this.env.getTerrain());
        Bib bib = new Bib(4000, 2030, this.env.getTerrain());
        this.env.addEntities(bingus);
        this.env.addEntities(bib);
        this.env.addEntities(florb);
    }


    public void createTimelines() {
        // 16.33 = 60 fps
        timelineCamera = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
                    if (!doOnce) {
                        camera.lookAt(player.getHitbox().getX(), player.getHitbox().getY());
                        doOnce = true;
                    }

                }));
        timelineCamera.setCycleCount(Timeline.INDEFINITE);
        timelineCamera.play();

        timelineGame = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
                   //env.unTour();
                   keyPlayerMovement();
                }));
        timelineGame.setCycleCount(Timeline.INDEFINITE);
        timelineGame.play();

        timelineInventory = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
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
                        playerMouseObservator.leftPressed(player, env.getTerrain(), playerInventoryView);
                        playerMouseObservator.leftPressed(player, env.getTerrain(), craftInventoryView);
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
                    if (mouseHandler.isHasScrollUp()) {
                        player.getPlayerInventory().decrementSlot();
                        mouseHandler.setHasScrollUp(false);
                    } else if (mouseHandler.isHasScrollDown()) {
                        player.getPlayerInventory().incrementSlot();
                        mouseHandler.setHasScrollDown(false);
                    }
                    verifKeyTyped();
                    playerInventoryObservator.refreshCurrentSlotView();
                }));
        timelineInventory.setCycleCount(Timeline.INDEFINITE);
        timelineInventory.play();
    }


    public void keyPlayerMovement() {
        if (keyHandler.isSprintPressed() && !player.isRunning()) {
            player.setRunning(true);
            player.setSpeed(14);
        }

        if (!keyHandler.isSprintPressed() && player.isRunning()) {
            player.setRunning(false);
            player.setSpeed(7);
        }

        if (keyHandler.isLeftPressed()) {
            player.movement(null, keyHandler.isLeftPressed(), false);
        } else if (keyHandler.isRightPressed()) {
            player.movement(null, false, keyHandler.isRightPressed());
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
        }  else if (keyHandler.isSlotSevenTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(6);
            keyHandler.setSlotSevenTyped(false);
        }  else if (keyHandler.isSlotEightTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(7);
            keyHandler.setSlotEightTyped(false);
        }  else if (keyHandler.isSlotNineTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(8);
            keyHandler.setSlotNineTyped(false);
        }  else if (keyHandler.isSlotTenTyped()) {
            player.getPlayerInventory().setCurrSlotNumber(9);
            keyHandler.setSlotTenTyped(false);
        }

    }
}
