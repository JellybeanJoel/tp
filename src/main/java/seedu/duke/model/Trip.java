package seedu.duke.model;

public class Trip {
    private String destination;
    private String startDate;
    private String endDate;
    private final ActivityList activities;
    private boolean isOpen;

    public Trip(String destination, String startDate, String endDate) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isOpen = false;
        this.activities = new ActivityList(this);
    }

    public String getDestination() {
        return destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public ActivityList getActivities() {
        return activities;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return destination + " (" + startDate + " to " + endDate + ")";
    }

    public String formatForList() {
        String result = destination + "\n";
        result += "   📅 Start: " + startDate + "\n";
        result += "   📅 End:   " + endDate;
        return result;
    }
}
