package modele;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.*;

public class CraftInventory  extends Inventory{

    private ObjectProperty<Slot> resultSlot;

    private HashMap<String, ArrayList<Item>> recipesName;
    private HashMap<ArrayList<Item>, Item> craftRecipes;

    private int nbItemCrafted;

    public CraftInventory(int maxInventorySize) {
        super(maxInventorySize);
        resultSlot = new SimpleObjectProperty<>(new Slot(null, 0, 9));
        buildRecipes();
    }

    public int verifRecipe(ArrayList<Item> recipe){
        nbItemCrafted = Integer.MAX_VALUE;
        for(int i = 0; i < recipe.size(); i++){

            try{
                if (!(getSlots().get(i).getItem().getTypeItem().equals(recipe.get(i).getTypeItem()))){
                    nbItemCrafted = 0;
                    return 0;
                }
                else {
                    if(getSlots().get(i).getItemQuantity() < nbItemCrafted){
                        nbItemCrafted = getSlots().get(i).getItemQuantity();
                    }
                }
            } catch (NullPointerException e){
                if(!(getSlots().get(i).getItem() == null && recipe.get(i) == null)){
                    nbItemCrafted = 0;
                    return 0;
                }
            }

        }
        return nbItemCrafted;
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
        try{
            ArrayList<Item> goodRecipe = (craftRecipes.keySet().stream().filter(currentRecipe -> verifRecipe(currentRecipe) > 0).findFirst()).get();
            Item itemCrafted = craftRecipes.get(goodRecipe);
            resultSlot.setValue(new Slot(itemCrafted,nbItemCrafted, getResultSlot().getId()));
        } catch (NoSuchElementException e){
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

    public void transferInventoryToPlayer(Player player){
        for(Slot slot : getSlots()){
            try{
                player.pick(slot.getItem(), slot.getItemQuantity());
                getSlots().set(slot.getId(), new Slot(null, 0, slot.getId()));
            } catch (NullPointerException e){

            }


        }
    }

    public void buildRecipes(){
        ItemBlock wood = new ItemBlock(1);
        CraftResource stick = new CraftResource(2);
        CraftResource vine = new CraftResource(8);

        craftRecipes = new HashMap<>();
        recipesName = new HashMap<>();

        // 3 woods = 1 stick
        recipesName.put("stick1", new ArrayList<>(Arrays.asList
                (null, wood, null,
                null, wood, null,
                null, wood, null))); // maybe declare ressources for crafts above so it doesn't load many ?
        craftRecipes.put(recipesName.get("stick1"), stick);

        //1 stick + 1 vine + 1 wood
        recipesName.put("woodPick", new ArrayList<>(Arrays.asList
                (null, null, null,
                null, vine, wood,
                null, stick, null))); // maybe declare ressources for crafts above so it doesn't load many ?
        craftRecipes.put(recipesName.get("woodPick"), new CraftResource(9)); // declare new results so it doesn't have two of the same item in different slots since it's passed down ?

    }
}
