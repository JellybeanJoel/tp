package seedu.traveltrio.command.packing;

import seedu.traveltrio.model.packing.PackingList;

/**
 * Represents a command to mark a packing list item as packed.
 */
public class CheckItemCommand {
    private PackingList list;
    private int index;

    /**
     * Constructs a CheckItemCommand with the specified packing list and item index.
     *
     * @param list the packing list containing the item
     * @param index the 1-based index of the item to mark as packed
     */
    public CheckItemCommand(PackingList list, int index) {
        this.list = list;
        this.index = index;
    }

    /**
     * Executes the command to mark an item as packed.
     *
     * @return a success message indicating the item that was marked as packed
     */
    public String execute() {
        list.get(index - 1).markPacked();
        return "Marked as packed: " + list.get(index - 1).getName();
    }
}
