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
        ItemBlock stone = new ItemBlock(3);
        CraftResource iron = new CraftResource(5);
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

        //2 stick + 1 vine + 1 wood
        recipesName.put("woodPick", new ArrayList<>(Arrays.asList
                (null, vine, wood,
                null, stick, null,
                null, stick, null)));
        craftRecipes.put(recipesName.get("woodPick"), new Tool(9));

        //2 stick + 2 vine + 2 wood
        recipesName.put("woodAxe", new ArrayList<>(Arrays.asList
                (null, vine, wood,
                null, stick, wood,
                null, stick, null)));
        craftRecipes.put(recipesName.get("woodAxe"), new Tool(12));

        //2 stick + 1 vine + 1 stone
        recipesName.put("stonePick", new ArrayList<>(Arrays.asList
                (null, vine, stone,
                null, stick, null,
                null, stick, null)));
        craftRecipes.put(recipesName.get("stonePick"), new Tool(10));

        //2 stick + 2 vine + 2 stone
        recipesName.put("stoneAxe", new ArrayList<>(Arrays.asList
                (null, vine, stone,
                null, stick, stone,
                null, stick, null)));
        craftRecipes.put(recipesName.get("stoneAxe"), new Tool(13));

        //2 stick + 2 iron
        recipesName.put("ironPick", new ArrayList<>(Arrays.asList
                (iron, iron, iron,
                null, stick, null,
                null, stick, null)));
        craftRecipes.put(recipesName.get("ironPick"), new Tool(11));

        //2 stick + 3 iron
        recipesName.put("ironAxe", new ArrayList<>(Arrays.asList
                (null, iron, iron,
                null, stick, iron,
                null, stick, null)));
        craftRecipes.put(recipesName.get("ironAxe"), new Tool(14));
    }
}
