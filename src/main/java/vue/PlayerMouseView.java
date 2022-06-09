package vue;

public class PlayerMouseView {

    public PlayerMouseView() {
    }

    public SlotView getOnSlotClicked(int x, int y, InventoryView inventoryView){
        for (int i = 0; i < 10; i++){
            if(inventoryView.getSlotViews().get(i).getEmptySlotRectangle().contains(x, y)){
                return inventoryView.getSlotViews().get(i);
            }
        }
        return null;
    }
}
