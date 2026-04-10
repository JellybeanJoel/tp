package seedu.traveltrio.command.packing;

import seedu.traveltrio.model.packing.PackingList;

public class ListItemCommand {
    private PackingList list;

    public ListItemCommand(PackingList list) {
        this.list = list;
    }

    public String execute() {
        if (list.isEmpty()) {
            return "Packing list is empty.";
        }

        StringBuilder sb = new StringBuilder("Packing List:\n");

        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ")
                    .append(list.get(i).toString())
                    .append("\n");
        }

        return sb.toString();
    }
}
