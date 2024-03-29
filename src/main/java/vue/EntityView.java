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

    private HashMap<String, Image> images;
    private Entity ent;
    private ImageView imgView;
    private String currAction = "";
    private Pane pane;

    public EntityView(Entity entity, Pane pane){
        ent = entity;
        imgView = new ImageView();
        images = new HashMap<>();
        for(String state : ent.getActions()){
            try{ // Gets image eg : Bingus_idle.gif
                images.put(state, new Image(String.valueOf(getClass().getResource("/Sprites/Entities/" + ent.getClass().getSimpleName() + "/" + ent.getClass().getSimpleName() + "_" + state + ".gif"))));
            }catch (IllegalArgumentException e){
                System.out.println("Couldn't find Image");
            }
            try{ // Gets sprites if they have a right version eg : Player_idle_right.gif
                images.put(state, new Image(String.valueOf(getClass().getResource("/Sprites/Entities/" + ent.getClass().getSimpleName() + "/" + ent.getClass().getSimpleName() + "_" + state + "right.gif"))));
            }catch (IllegalArgumentException e){
                System.out.println("Couldn't find Image");
            }
            try{ // Gets sprites if they have a left version eg : Player_run_left.gif
                images.put(state, new Image(String.valueOf(getClass().getResource("/Sprites/Entities/" + ent.getClass().getSimpleName() + "/" + ent.getClass().getSimpleName() + "_" + state + "left.gif"))));
            }catch (IllegalArgumentException e){
                System.out.println("Couldn't find Image");
            }
        }
        this.imgView.xProperty().bind(ent.getHitbox().xProperty().subtract(images.get("idle").getWidth()/2 - ent.getHitbox().getWidth() / 2));
        this.imgView.yProperty().bind(ent.getHitbox().yProperty().subtract(images.get("idle").getHeight() - ent.getHitbox().getHeight()));
        System.out.println(images.size());
        pane.getChildren().add(imgView);
        imgView.setImage(images.get(ent.getAction()));
        ent.actionProperty().addListener(property -> {
            imgView.setImage(images.get(((StringProperty)property).getValue()));
        });
    }
}