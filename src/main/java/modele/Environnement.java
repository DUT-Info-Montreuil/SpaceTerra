package modele;

import controleur.Controleur;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {

    private ObservableList<Entity> entities;

    private Terrain terrain;

    public Environnement() {
        this.entities = FXCollections.observableArrayList();
        this.terrain = new Terrain("src/main/resources/Map/bigTest.json");

    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public void addEntities(Entity entity) {
        this.entities.add(entity);
    }

    public ObservableList<Entity> getEntities() {
        return this.entities;
    }

    public void unTour() {
            for (Entity ent : this.getEntities()) {
                if (!(ent instanceof Player)) {
                    if (ent.isGrounded()) {
                        ent.setGravity(5);
                        ent.jump();
                    } else if (ent.isJumping()) {
                        ent.movement(Controleur.player, !ent.sideLeftCollision(), !ent.sideRightCollisions());
                        ent.stopJump();
                        if (ent.upCollisions()) {
                            ent.stopJump();
                        }
                    }if (ent.isGrounded()) {
                        if (!ent.isFlying())
                            ent.applyGrav();
                    }
                    ent.movement(Controleur.player, !ent.sideLeftCollision(), !ent.sideRightCollisions());
                    ent.jump();
                }

                if (!Controleur.player.isGrounded() && !Controleur.player.isJumping()) {
                    Controleur.player.applyGrav();
                }

            }

    }
}
