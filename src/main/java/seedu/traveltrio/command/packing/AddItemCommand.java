package seedu.traveltrio.command.packing;

import seedu.traveltrio.model.packing.PackingList;
import seedu.traveltrio.model.packing.PackingItem;

/**
 * Represents a command to add a new item to the packing list.
 */
public class AddItemCommand {
    private PackingList list;
    private String itemName;

    /**
     * Constructs an AddItemCommand with the specified packing list and item name.
     *
     * @param list the packing list to add the item to
     * @param itemName the name of the item to add
     */
    public AddItemCommand(PackingList list, String itemName) {
        this.list = list;
        this.itemName = itemName;
    }

    /**
     * Executes the command to add a new item to the packing list.
     *
     * @return a success message indicating the item that was added
     */
    public String execute() {
        PackingItem item = new PackingItem(itemName);
        list.addItem(item);
        return "Added item: " + itemName;
    }
}
