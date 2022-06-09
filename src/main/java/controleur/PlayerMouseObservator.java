package controleur;

import modele.*;
import vue.InventoryView;
import vue.PlayerMouseView;
import vue.SlotView;

public class PlayerMouseObservator {
    private PlayerMouse playerMouse;
    private PlayerMouseView playerMouseView;

    public PlayerMouseObservator(PlayerMouse playerMouse, PlayerMouseView playerMouseView) {
        this.playerMouse = playerMouse;
        this.playerMouseView = playerMouseView;
        initialize();
    }

    public void leftClick(Inventory inventory, InventoryView inventoryView){
        SlotView slotView = playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(slotView != null){
            inventory.setCurrSlotNumber(slotView.getId());
            playerMouse.onSlotLeftClickedAction(inventory.getSlot(slotView.getId()), inventory);
            setItemView();
        }
    }

    public void leftPressed(Player player, Terrain terrain, InventoryView inventoryView){
        if(playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView) == null){
            playerMouse.playerClickAction(player, terrain);
            setItemView();
        }

    }

    public void initialize(){
        playerMouseView.getItemQuantityLabel().textProperty().bind(playerMouse.currentItemQuantityProperty().asString());
        playerMouseView.getItemQuantityLabel().layoutYProperty().setValue(MouseHandler.mouseY.getValue() + 34);
        playerMouseView.getItemQuantityLabel().layoutXProperty().setValue(MouseHandler.mouseX.getValue() + 34);
        playerMouseView.getItemView().layoutYProperty().bind(playerMouse.yProperty().add(32));
        playerMouseView.getItemView().layoutXProperty().bind(playerMouse.xProperty().add(32));
    }



    public void setItemView(){
        try {
            playerMouseView.getItemView().setImage(playerMouse.getItem().getTypeItem().getImage());
            playerMouseView.displayItemQuantityLabel(true);
        }
        catch (NullPointerException e){
            playerMouseView.getItemView().setImage(null);
            playerMouseView.displayItemQuantityLabel(false);

        }

    }

}
