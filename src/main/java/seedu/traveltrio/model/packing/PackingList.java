package seedu.traveltrio.model.packing;

import java.util.ArrayList;
import java.util.List;

public class PackingList {
    private final List<PackingItem> items;

    public PackingList() {
        this.items = new ArrayList<>();
    }

    public void addItem(PackingItem item) {
        items.add(item);
    }

    public PackingItem get(int index) {
        return items.get(index);
    }

    public void remove(int index) {
        items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<PackingItem> getItems() {
        return items;
    }

    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        for (PackingItem item : items) {
            sb.append(item.toFileFormat()).append("\n");
        }
        return sb.toString();
    }
}
