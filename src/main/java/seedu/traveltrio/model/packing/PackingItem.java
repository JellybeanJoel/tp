package seedu.traveltrio.model.packing;

public class PackingItem {
    private String name;
    private boolean isPacked;

    public PackingItem(String name) {
        this.name = name;
        this.isPacked = false;
    }

    public String getName() {
        return name;
    }

    public boolean isPacked() {
        return isPacked;
    }

    public void markPacked() {
        this.isPacked = true;
    }

    public void markUnpacked() {
        this.isPacked = false;
    }

    public String toFileFormat() {
        return (isPacked ? "1" : "0") + "|" + name;
    }
    
    @Override
    public String toString() {
        return (isPacked ? "[X] " : "[ ] ") + name;
    }
}
