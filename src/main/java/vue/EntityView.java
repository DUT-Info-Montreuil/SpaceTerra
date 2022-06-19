package vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.Entity;

import java.util.HashMap;

public class EntityView {
    private ImageView imgView;
    private String currAction;
    private Pane pane;

    public EntityView(Pane pane){
        this.imgView = new ImageView();
        this.pane = pane;
        this.currAction = " ";
    }

    public void addEntity(Entity entity) {
        imgView = new ImageView();
        imgView.setId(entity.getId());
        if (entity != null) {
            for(String state : entity.getActions()){
                try{ // Gets image eg : Bingus_idle.gif
                    imgView = new ImageView(new Image(String.valueOf(getClass().getResource("/Sprites/Entities/" + entity.getClass().getSimpleName() + "/" + entity.getClass().getSimpleName() + "_" + state + ".gif"))));
                }catch (IllegalArgumentException e){
                    System.out.println("Couldn't find Image");
                }
                try{ // Gets sprites if they have a right version eg : Player_idle_right.gif
                    imgView = new ImageView(new Image(String.valueOf(getClass().getResource("/Sprites/Entities/" + entity.getClass().getSimpleName() + "/" + entity.getClass().getSimpleName() + "_" + state + "right.gif"))));
                }catch (IllegalArgumentException e){
                    System.out.println("Couldn't find Image");
                }
                try{ // Gets sprites if they have a left version eg : Player_run_left.gif
                    imgView = new ImageView(new Image(String.valueOf(getClass().getResource("/Sprites/Entities/" + entity.getClass().getSimpleName() + "/" + entity.getClass().getSimpleName() + "_" + state + "left.gif"))));
                }catch (IllegalArgumentException e){
                    System.out.println("Couldn't find Image");
                }
            }
        }
        this.imgView.xProperty().bind(entity.getHitbox().xProperty().subtract(entity.getHitbox().getWidth()* 3/4));
        this.imgView.yProperty().bind(entity.getHitbox().yProperty().subtract(entity.getHitbox().getHeight()/3-2));
        this.imgView.setId(entity.getId());
        //System.out.println(images.size());
        pane.getChildren().add(imgView);
    }

    public void deleteEntity(Entity entity) {
        pane.getChildren().remove(pane.lookup("#E" + entity.getId()));
    }
/*
    public void startTimeline(){
        timeline = new Timeline(new KeyFrame(Duration.millis(16.33), actionEvent -> {
            if(!currAction.equals(.getAction())){
                currAction = ent.getAction();
                //imgView.setImage(imgView.get(currAction));
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
 */
}