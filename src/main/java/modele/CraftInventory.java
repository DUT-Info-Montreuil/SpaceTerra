package modele;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.Arrays;

public class CraftInventory  extends Inventory{

    private ObjectProperty<Slot> resultSlot;

    public CraftInventory(int maxInventorySize) {
        super(maxInventorySize);
        resultSlot = new SimpleObjectProperty<>(new Slot(null, 0, 9));

    }

    public int verifRecipe(ArrayList<Item> recipe){
        int smallestNumber = Integer.MAX_VALUE;
        for(int i = 0; i < recipe.size(); i++){

            try{
                if (getSlots().get(i).getItem().getTypeItem().equals(recipe.get(i).getTypeItem())){
                    if(getSlots().get(i).getItemQuantity() < smallestNumber){
                        smallestNumber = getSlots().get(i).getItemQuantity();
                    }
                }
            } catch (NullPointerException e){
                if(!(getSlots().get(i).getItem() == null && recipe.get(i) == null)){
                    return 0;
                }
            }

        }
        return smallestNumber;
    }

    public Slot getResultSlot() {
        return resultSlot.getValue();
    }

    public ObjectProperty<Slot> resultSlotProperty() {
        return resultSlot;
    }

    public void setResultSlot(Slot resultSlot) {
        this.resultSlot.set(resultSlot);
    }

    public void verifCraft(){
        ItemBlock dirt = new ItemBlock(0);
        ItemBlock wood = new ItemBlock(1);
        ArrayList<Item> woodRecipe = new ArrayList<>(Arrays.asList(null, dirt, null, null, dirt, null, null, dirt, null));
        int nbItemCrafted = verifRecipe(woodRecipe);
        if(nbItemCrafted > 0){
            resultSlot.setValue(new Slot(wood,nbItemCrafted, getResultSlot().getId()));
        }
        else {
            resultSlot.setValue(new Slot(null, 0, getResultSlot().getId()));
        }
    }

    public void decrementResultSlot(int nb){
        for(Slot slot : getSlots()){
            if (slot.getItem() != null){
                if(slot.getItemQuantity() - nb > 0){
                    slot.decrementItemQuantity(nb);
                }
                else {
                    getSlots().set(slot.getId(), new Slot(null, 0, slot.getId()));
                }
            }
        }
        verifCraft();
    }



}
