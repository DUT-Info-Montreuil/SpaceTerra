package controleur;

import javafx.collections.ListChangeListener;
import modele.Block;
import modele.Slot;
import modele.Terrain;
import vue.TerrainView;

public class TerrainObservator implements ListChangeListener<Block> {

    private TerrainView terrainView;


    public TerrainObservator(TerrainView terrainView) {
        super();
        this.terrainView = terrainView;
    }

    @Override
    public void onChanged(Change<? extends Block> change) {
        while (change.next()) {
            for (Block bDeleted : change.getRemoved()) {
                this.terrainView.deleteBlock(bDeleted);
            }
            for (Block bAdded : change.getAddedSubList()){
                this.terrainView.addBlock(bAdded);
            }
        }
    }
}


