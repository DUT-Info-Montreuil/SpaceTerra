package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Entity> entities;

    private Terrain terrain;

    public Environnement(Terrain terrain) {
        entities = FXCollections.observableArrayList();
        this.terrain = terrain;
    }

    public void init() {
        Player player = new Player(3500, 2030, terrain);
        Moobius moobius = new Moobius(terrain,4000, 2030);
        Bingus bingus = new Bingus(15000, 2030, terrain);
        Florb florb = new Florb(10000, 1980, terrain);
        Bib bib = new Bib(5000, 2030, terrain);
        this.getEntities().add(player);
        this.getEntities().add(bingus);
        this.getEntities().add(florb);
        this.getEntities().add(bib);
        this.getEntities().add(moobius);
    }

    public Player getPlayer() {
        return (Player) this.getEntities().get(0);
    }

    public ObservableList<Entity> getEntities() {
        return this.entities;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void deleteEntity(Entity entity) {
        this.entities.remove(entity);
    }
}
