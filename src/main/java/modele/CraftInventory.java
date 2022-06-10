package modele;

public class CraftInventory  extends Inventory{

    private Slot resultSlot;

    public CraftInventory(int maxInventorySize) {
        super(maxInventorySize);
        resultSlot = new Slot(null, 0, 9);
    }

    public boolean verifRecipe(Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, Item item7, Item item8, Item item9){
        if(getSlot(0).getItem().getTypeItem().equals(item1.getTypeItem()) && getSlot(1).getItem().getTypeItem().equals(item2.getTypeItem()) && getSlot(2).getItem().getTypeItem().equals(item3.getTypeItem()) && getSlot(3).getItem().getTypeItem().equals(item4.getTypeItem()) && getSlot(4).getItem().getTypeItem().equals(item5.getTypeItem()) && getSlot(5).getItem().getTypeItem().equals(item6.getTypeItem()) && getSlot(6).getItem().getTypeItem().equals(item7.getTypeItem()) && getSlot(7).getItem().getTypeItem().equals(item8.getTypeItem()) &&  getSlot(8).getItem().getTypeItem().equals(item9)){
            return true;
        }
        return false;
    }
    public Slot getResultSlot() {
        return resultSlot;
    }

    public void setResultSlot(Slot resultSlot) {
        this.resultSlot = resultSlot;
    }

    public void verifCraft(){
        ItemBlock dirt = new ItemBlock(0);
        if(verifRecipe(null, dirt, null, null, dirt, null, null, dirt, null)){
            resultSlot = new Slot(dirt,1, resultSlot.getId());
        }
    }

}
