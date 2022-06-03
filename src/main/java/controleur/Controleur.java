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
import vue.DebugView;
import vue.InventoryView;
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

    private Timeline timelineClick;
    private Player player;
    private KeyHandler keyHandler;
    private ArrayList<Entity> entities;
    private MouseHandler mouseHandler;

    private Rectangle zonePlayerBlock; //Rectangle dont la zone appartenant au joueur qui ne permet donc pas de poser de block dans celle-ci

    private Rectangle mouseBlock; //Rectangle dont la zone du block est celle ou la souris se positionne

    private Rectangle currentSlotView;
    private InventoryView inventoryView;

    private boolean isBinded;

    public static DebugView debugger;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entities = new ArrayList<>();
        Scene scene = new Scene(panneauDeJeu, 1000, 1000, Color.DARKBLUE);
        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);
        terrain = new Terrain("src/main/resources/Map/bigTest.json");
        terrainView = new TerrainView(panneauDeJeu, entities);
        terrainView.readMap(terrain);
        createEnnemies();

        PlayerView playerView = new PlayerView(player = new Player(10, 2030, terrain), panneauDeJeu);
        //PlayerView playerView = new PlayerView(player = new Player(15000, 3730), panneauDeJeu);
        //PlayerView playerView = new PlayerView(player = new Player(30, 0), panneauDeJeu);
        entities.add(player);
        playerView.displayPlayer();
        //panneauDeJeu.getScene().getCamera().layoutXProperty().setValue(0);
        panneauDeJeu.getScene().getCamera().layoutXProperty().setValue(player.getHitbox().getX().getValue());
        panneauDeJeu.getScene().getCamera().layoutYProperty().bind(player.getHitbox().getY().subtract(panneauDeJeu.getScene().getHeight() / 2));
        createTimelines();
        inventoryView = new InventoryView(player.getInventory(), panneauDeJeu);
        keyHandler = new KeyHandler(panneauDeJeu);
        keyHandler.keyManager();
        mouseHandler = new MouseHandler(panneauDeJeu);
        mouseHandler.mouseManager();
        breakingManager();
        rectanglesManager();
        isBinded = true;
        terrainView.readEntity();
        debugger = new DebugView(panneauDeJeu);
    }

    public void cameraManager() {
        if (isBinded && panneauDeJeu.getScene().getCamera().getBoundsInLocal().getMinX() > player.getHitbox().getX().getValue() - (panneauDeJeu.getScene().getWidth() / 2) - 10) {
            panneauDeJeu.getScene().getCamera().layoutXProperty().unbind();
            isBinded = false;
        } else if (isBinded && panneauDeJeu.getBoundsInLocal().getMaxX() < player.getHitbox().getX().getValue() + (panneauDeJeu.getScene().getWidth() / 2) + 10) {
            panneauDeJeu.getScene().getCamera().layoutXProperty().unbind();
            isBinded = false;
        } else {
            panneauDeJeu.getScene().getCamera().layoutXProperty().bind(player.getHitbox().getX().subtract(panneauDeJeu.getScene().getWidth() / 2));
            isBinded = true;
        }
        if (isBinded && panneauDeJeu.getScene().getCamera().getBoundsInLocal().getMinY() > player.getHitbox().getY().getValue() - (panneauDeJeu.getScene().getHeight() / 2)) {
            panneauDeJeu.getScene().getCamera().layoutYProperty().unbind();
            isBinded = false;
        } else if (isBinded && panneauDeJeu.getBoundsInLocal().getMaxY() < player.getHitbox().getY().getValue() + (panneauDeJeu.getScene().getHeight() / 2) + 20) {
            panneauDeJeu.getScene().getCamera().layoutYProperty().unbind();
            isBinded = false;
        } else {
            panneauDeJeu.getScene().getCamera().layoutYProperty().bind(player.getHitbox().getY().subtract(panneauDeJeu.getScene().getHeight() / 2));
            isBinded = true;
        }
    }

    public void rectanglesManager() {
        mouseBlock = new Rectangle();
        mouseBlock.setWidth(32);
        mouseBlock.setHeight(32);
        mouseBlock.xProperty().bind(mouseHandler.getMouseXProperty().divide(32).multiply(32));
        mouseBlock.yProperty().bind(mouseHandler.getMouseYProperty().divide(32).multiply(32));
        mouseBlock.setFill(Color.TRANSPARENT);
        mouseBlock.setStroke(Color.TRANSPARENT);
        currentSlotView = new Rectangle();
        currentSlotView.setFill(Color.TRANSPARENT);
        currentSlotView.setStroke(Color.YELLOW);
        currentSlotView.setWidth(34);
        currentSlotView.setHeight(34);
        currentSlotView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(99 + 32 * player.getInventory().getCurrSlot()));
        currentSlotView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(99));
        panneauDeJeu.getChildren().addAll(mouseBlock, currentSlotView);
    }

    public void createEnnemies() {
        Bingus bingus = new Bingus(10, 2030, terrain);
        Florb florb = new Florb(10, 2000, terrain);
        Bib bib = new Bib(2000, 2030, terrain);
        entities.add(bingus);
        entities.add(florb);
        entities.add(bib);
    }

    public void createTimelines() {
        // 16.33 = 60 fps
        timeline = new Timeline
                (new KeyFrame(Duration.millis(16.33), actionEvent -> {
                    entityLoop(); // Entity loop has to happen befor player movement so that gravity and position fixes are applied before moving
                    playerMovement();
                    cameraManager();


                    if (mouseHandler.isHasScrollUp()) {
                        player.getInventory().incrementSlot();
                        mouseHandler.setHasScrollUp(false);
                    } else if (mouseHandler.isHasScrollDown()) {
                        player.getInventory().decrementSlot();
                        mouseHandler.setHasScrollDown(false);
                    }


                    verifKeyTyped();
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        timelineClick = new Timeline

                (new KeyFrame(Duration.millis(200), actionEvent -> {
                    //System.out.println(mouseBlock.xProperty().intValue());
                    //System.out.println(mouseBlock.yProperty().intValue());

                    if (mouseHandler.isHasPressedLeft()) {
                        checkOnLeftPressed();
                    } else if (mouseHandler.isHasClickedRight()) {
                        checkOnRightClicked();
                        //System.out.println("rclick");
                        mouseHandler.setHasClickedRight(false);
                    }

                }));
        timelineClick.setCycleCount(Timeline.INDEFINITE);
        timelineClick.play();

    }

/*
    public int checkSideBlock(Entity ent) { // -1 = left, 1 = right, 0 = none
                if (ent.sideCollisions(panneauDeJeu) == 1)
                    return 1;
                else if (ent.sideCollisions(panneauDeJeu) == -1)
                    return -1;

        return 0;
    }
*/
    public boolean checkDistanceBlock(Entity ent, Block b) {
        //  System.out.println(ent.distanceToBlock(b));
        if (ent.distanceToBlock(b) <= 4) {
            mouseBlock.setStroke(Color.GREEN);
            return true;
        }
        mouseBlock.setStroke(Color.RED);
        return false;
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
                } else {
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

    public void breakingManager() {
        this.terrain.getBlocks().addListener((ListChangeListener<Block>) change -> {
            while (change.next()) {
                for (Block b : change.getRemoved()) {
                    this.terrainView.deleteBlock(b);
                }
            }
        });
    }

    public void checkOnLeftPressed() {
        Block b = terrain.getBlock(mouseHandler.getMouseX(), mouseHandler.getMouseY());
        //DebugView.debugPoint(mouseHandler.getMouseX(), mouseHandler.getMouseY(), Color.BLUE);
        if (b != null) {
            System.out.println("not null");
            if (checkDistanceBlock(player, b)) {
                // System.out.println("ok");
                b.setPvs(b.getPvs() - 1);
                System.out.println(b.getPvs());
                System.out.println(terrain.getBlocks().indexOf(b));
                if (b.getPvs() <= 0) {
                    terrain.deleteBlock(b);
                    if (b.getTile().getHitbox().isSolid()) {
                        terrain.deleteSolidBlock(b);
                    }
                    if (b.ressource() != null) {
                        if (!player.getInventory().isInventoryFull()) {
                            inventoryView.refreshBreak(b.ressource());
                            player.pick(b.ressource());
                            //System.out.println(player.getInventory().getItems());
                        }
                    }
                    // System.out.println(player.getInventory());
                }
            }
            /*
            Rectangle r = new Rectangle(b.getHitX(), b.getHitY(), b.getTile().getHitbox().getWidth(), b.getTile().getHitbox().getHeight());
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            panneauDeJeu.getChildren().add(r);
            */
        }
    }

    public void checkOnRightClicked() {
        Block bMouse = terrain.getBlock(mouseHandler.getMouseX(), mouseHandler.getMouseY());
        Block bPlace;
        if (bMouse == null /*&& !zonePlayerBlock.intersects(mouseBlock.getBoundsInLocal())*/) {
            // System.out.println(player.getInventory());
            Item item = player.drop();
            if (item != null) {
                //    System.out.println("Tu peux poser le block !");
                bPlace = new Block(item.getTile(), (mouseHandler.getMouseX() / 32) * 32, (mouseHandler.getMouseY() / 32) * 32);
                if (checkDistanceBlock(player, bPlace)) {
                    terrain.getBlocks().set(terrain.getIndex(mouseHandler.getMouseX(), mouseHandler.getMouseY()), bPlace);
                    inventoryView.refreshPlace();
                    if (bPlace.getTile().getHitbox().isSolid()) {
                        terrain.getSolidBlocks().add(bPlace);
                    }
                    terrainView.addBlock(terrain, bPlace);
                }

            } else {
                mouseBlock.setStroke(Color.RED);
            }
        }
        /*if (zonePlayerBlock.intersects(mouseBlock.getBoundsInLocal())) {
            mouseBlock.setStroke(Color.RED);
        }*/
    }


    public void verifKeyTyped() {
        if (keyHandler.isSlotOneTyped()) {
            player.getInventory().setCurrSlot(0);
            keyHandler.setSlotOneTyped(false);
        } else if (keyHandler.isSlotTwoTyped()) {
            player.getInventory().setCurrSlot(1);
            keyHandler.setSlotTwoTyped(false);
        } else if (keyHandler.isSlotThreeTyped()) {
            player.getInventory().setCurrSlot(2);
            keyHandler.setSlotThreeTyped(false);
        } else if (keyHandler.isSlotFourTyped()) {
            player.getInventory().setCurrSlot(3);
            keyHandler.setSlotFourTyped(false);
        } else if (keyHandler.isSlotFiveTyped()) {
            player.getInventory().setCurrSlot(4);
            keyHandler.setSlotFiveTyped(false);
        } else if (keyHandler.isSlotSixTyped()) {
            player.getInventory().setCurrSlot(5);
            keyHandler.setSlotSixTyped(false);
        } else if (keyHandler.isSlotSevenTyped()) {
            player.getInventory().setCurrSlot(6);
            keyHandler.setSlotSevenTyped(false);
        } else if (keyHandler.isSlotEightTyped()) {
            player.getInventory().setCurrSlot(7);
            keyHandler.setSlotEightTyped(false);
        } else if (keyHandler.isSlotNineTyped()) {
            player.getInventory().setCurrSlot(8);
            keyHandler.setSlotNineTyped(false);
        } else if (keyHandler.isSlotTenTyped()) {
            player.getInventory().setCurrSlot(9);
            keyHandler.setSlotTenTyped(false);
        }
        currentSlotView.xProperty().bind(panneauDeJeu.getScene().getCamera().layoutXProperty().add(99 + 32 * player.getInventory().getCurrSlot()));
        currentSlotView.yProperty().bind(panneauDeJeu.getScene().getCamera().layoutYProperty().add(99));
        currentSlotView.toFront();
    }

    public static int randomNum(int min, int max) {
        if (min == max)
            return max;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return rand;
    }
}
