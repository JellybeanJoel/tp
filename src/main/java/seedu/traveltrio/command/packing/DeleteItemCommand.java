package seedu.traveltrio.command.packing;

import seedu.traveltrio.model.packing.PackingList;

public class DeleteItemCommand {
    private PackingList list;
    private int index;

    public DeleteItemCommand(PackingList list, int index) {
        this.list = list;
        this.index = index;
    }

    public String execute() {
        String name = list.get(index - 1).getName();
        list.remove(index - 1);
        return "Removed item: " + name;
    }
}
