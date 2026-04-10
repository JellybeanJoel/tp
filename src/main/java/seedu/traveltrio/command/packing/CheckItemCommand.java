package seedu.traveltrio.command.packing;

import seedu.traveltrio.model.packing.PackingList;

public class CheckItemCommand {
    private PackingList list;
    private int index;

    public CheckItemCommand(PackingList list, int index) {
        this.list = list;
        this.index = index;
    }

    public String execute() {
        list.get(index - 1).markPacked();
        return "Marked as packed: " + list.get(index - 1).getName();
    }
}
