package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private ArrayList<Item> items;

    private HashMap<String, Integer> itemsQuantites;
    private int currSlot;
    private int maxInventorySize;
    private int currInventorySize;

    public ArrayList<Item> getItems() {
        return items;
    }

    public Inventory() {
        this.items = new ArrayList<>(10);
        this.currSlot = 0;
        this.maxInventorySize = 10;
        this.currInventorySize = 0;
        for (int i = 0; i < maxInventorySize; i++) {
            items.add(null);
        }
        itemsQuantites = new HashMap<>();
    }


    public int getCurrSlot() {
        return currSlot;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public Item getCurrItem() {
        try {
            System.out.println("CUUR" + currSlot);
            return items.get(currSlot);
        } catch (Exception e) {
            return null;
        }
    }

    public Item getItemFromSlot(int numSlot) {
        try {
            return items.get(numSlot);
        } catch (Exception e) {
            return null;
        }
    }

    public int getNextEmptySlot() {
        int i = 0;
        while (i < items.size() && items.get(i) != null) {
            i++;
        }

        return i;

    }

    public void setMaxInventorySize(int maxInventorySize) {
        this.maxInventorySize = maxInventorySize;
    }

    public void setCurrSlot(int currSlot) {
        this.currSlot = currSlot;
    }

    public void incrementSlot() {
        if (this.currSlot < this.maxInventorySize - 1) {
            currSlot++;
        }
    }

    public void decrementSlot() {
        if (this.currSlot > 0) {
            currSlot--;
        }
    }

    public boolean isInventoryFull() {
        if (currInventorySize >= maxInventorySize) {
            return true;
        }
        return false;
    }

    public Item removeFromSlot() {
        try {
            Item item  = items.get(currSlot);
            if(itemsQuantites.get(items.get(currSlot).getClass().toString()) > 1){
                itemsQuantites.replace(items.get(currSlot).getClass().toString(), itemsQuantites.get(items.get(currSlot).getClass().toString())-1);
            }
            else {
                itemsQuantites.remove(items.get(currSlot).getClass().toString());
                items.remove(item);
                items.add(currSlot, null);
                currInventorySize--;
            }
            return item;

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("slot vide !");
            return null;
        }
    }

    public void addIntoSlot(Item item) {
        if (!isInventoryFull()) {
            if (itemsQuantites.containsKey(item.getClass().toString())) {
                if(itemsQuantites.get(item.getClass().toString()) % item.getMaxQuantity() == 0){
                    items.set(getNextEmptySlot(), item);
                    currInventorySize++;
                    itemsQuantites.replace(item.getClass().toString(), itemsQuantites.get(item.getClass().toString())+1);
                }
                else {
                    itemsQuantites.replace(item.getClass().toString(), itemsQuantites.get(item.getClass().toString())+1);
                }

            } else {
                items.set(getNextEmptySlot(), item);
                currInventorySize++;
                itemsQuantites.put(item.getClass().toString(), 1);
            }
        } else{
            System.out.println("inventaire plein !");
        }
        System.out.println(items);
    }

}


