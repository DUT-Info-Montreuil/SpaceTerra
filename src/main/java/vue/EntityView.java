package vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Entity;

public class EntityView {

    private Pane panneauDeJeu;

    private ImageView imageView;

    public EntityView(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
        this.imageView = new ImageView();

    }
    public void addEntity(Entity entity) {
        imageView = new ImageView(entity.getImage());
        imageView.xProperty().bind(entity.getHitbox().getX().subtract(entity.getImage().getWidth()/2 - entity.getHitbox().getWidth()/2));
        imageView.yProperty().bind(entity.getHitbox().getY().subtract(entity.getImage().getHeight() - entity.getHitbox().getHeight()));
        imageView.setId(entity.getId());
        panneauDeJeu.getChildren().add(imageView);
    }

    public void deleteEntity(Entity entity) {
        panneauDeJeu.getChildren().remove(panneauDeJeu.lookup("#"+entity.getId()));
    }
}
