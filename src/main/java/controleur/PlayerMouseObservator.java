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
            if(!inventoryView.isShow()){
                inventory.setCurrSlotNumber(slotView.getId());
            }
            playerMouse.onSlotLeftClickedAction(inventory.getSlot(slotView.getId()), inventory);
            setItemView();
        }
        playerMouseView.getItemView().toFront();
    }

    public void leftPressed(Player player, Terrain terrain, InventoryView inventoryView){
        if(playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView) == null){
            playerMouse.playerLeftPressedAction(player, terrain);
            setItemView();
            playerMouseView.getItemView().toFront();
        }

    }

    public void initialize(){
        playerMouseView.getItemQuantityLabel().textProperty().bind(playerMouse.currentItemQuantityProperty().asString());
        playerMouseView.getItemView().layoutYProperty().bind(playerMouse.yProperty().add(15));
        playerMouseView.getItemView().layoutXProperty().bind(playerMouse.xProperty().add(15));
        //playerMouseView.getItemQuantityLabel().layoutXProperty().bind(playerMouseView.getItemView().layoutXProperty().add(playerMouseView.getItemView().getFitWidth())); // pour mettre en bas a droite de l'image
        //playerMouseView.getItemQuantityLabel().layoutYProperty().bind(playerMouseView.getItemView().layoutYProperty().add(playerMouseView.getItemView().getFitHeight()));
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

    public void rightClick(Inventory inventory, InventoryView inventoryView) {
        SlotView slotView = playerMouseView.getOnSlotClicked(playerMouse.getX(), playerMouse.getY(), inventoryView);
        if(slotView != null){
            if(!inventoryView.isShow()){
                inventory.setCurrSlotNumber(slotView.getId());
            }
            playerMouse.onSlotRightClicked(inventory.getSlot(slotView.getId()), inventory);
            setItemView();
        }
        playerMouseView.getItemView().toFront();
    }
}
