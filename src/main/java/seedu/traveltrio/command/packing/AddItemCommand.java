package seedu.traveltrio.command.packing;

import seedu.traveltrio.model.packing.PackingList;
import seedu.traveltrio.model.packing.PackingItem;

public class AddItemCommand {
    private PackingList list;
    private String itemName;

    public AddItemCommand(PackingList list, String itemName) {
        this.list = list;
        this.itemName = itemName;
    }

    public String execute() {
        PackingItem item = new PackingItem(itemName);
        list.addItem(item);
        return "Added item: " + itemName;
    }
}
