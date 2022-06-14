package controleur;

import javafx.collections.ListChangeListener;
import modele.Entity;
import vue.EntityView;

public class EntityObservator implements ListChangeListener<Entity> {

    private EntityView entityView;

    public EntityObservator(EntityView entityView) {
        super();
        this.entityView = entityView;
    }

    @Override
    public void onChanged(Change<? extends Entity> change) {

        while (change.next()) {
            for (Entity entity : change.getRemoved()) {
                entityView.deleteEntity(entity);
            }
            for (Entity entity : change.getAddedSubList()) {
               entityView.addEntity(entity);
            }
        }
    }
}
