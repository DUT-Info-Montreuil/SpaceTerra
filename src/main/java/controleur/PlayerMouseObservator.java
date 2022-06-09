package controleur;

import modele.Inventory;
import modele.PlayerMouse;
import vue.InventoryView;
import vue.PlayerMouseView;
import vue.SlotView;

public class PlayerMouseObservator {
    private PlayerMouse playerMouse;
    private PlayerMouseView playerMouseView;

    public PlayerMouseObservator(PlayerMouse playerMouse, PlayerMouseView playerMouseView) {
        this.playerMouse = playerMouse;
        this.playerMouseView = playerMouseView;
    }

    public void leftClick(Inventory inventory, InventoryView inventoryView){
        SlotView slotView = playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(slotView != null){
            inventory.setCurrSlotNumber(slotView.getId());
        }
    }


}
