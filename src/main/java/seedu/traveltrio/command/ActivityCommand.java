package seedu.traveltrio.command;

import seedu.traveltrio.model.ActivityList;


public abstract class ActivityCommand {
    protected ActivityList activityList;

    public ActivityCommand(ActivityList activityList) {
        this.activityList = activityList;
    }

    public String run(String tripName) {
        if (!activityList.isTripOpen()) {
            return "Please open a trip before managing activities.";
        } else {
            return execute(tripName);
        }
    }

    public abstract String execute(String tripName);
}
