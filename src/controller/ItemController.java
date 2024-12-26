package controller;

import model.Item;

import java.util.ArrayList;

public class ItemController {
    private final ArrayList<Item> itemList = new ArrayList<>();
    private static ItemController itemControllerInstance;

    public static ItemController getInstance() {
            if (itemControllerInstance == null) {
                itemControllerInstance = new ItemController();
            }

            return itemControllerInstance;
    }

    public boolean addItem(Item item) {

        return itemList.add(item);
    }

    public Item removeItem(Item item) {
        int itemIndex = searchIndex(item);

        if (itemIndex != -1) {
            return itemList.remove(itemIndex);

        }

        return null;
    }

    public String getNextItemID() {
        return String.format("ITM#%05d", itemList.size()+1);
    }

    private int searchIndex(Item item) {
        Item[] itemArray = getItems();


        for (int i = 0; i < itemArray.length; i++) {
            if (itemArray[i].getId().equalsIgnoreCase(item.getId())) {
                return i;
            }
        }

        return -1;
    }

    public Item[] getItems() {
        return itemList.size() != 0 ? itemList.toArray(new Item[0]) : null;

    }


    public void printItems() {
        Item[] itemArray = getItems();

        for (Item item : itemArray) {
            System.out.printf("%s | %s %n", item.getId(), item.getName());

        }
    }

    public void increaseQuantity(Item item) {
        int index = searchIndex(item);

        if (index != -1) {
            item.setQuantity(item.getQuantity()+1);
            itemList.set(index, item);
        }
    }

    public void decreaseQuantity(Item item) {
        int index = searchIndex(item);

        if (index != -1) {
            item.setQuantity(item.getQuantity()-1);
            itemList.set(index, item);
        }
    }
}
