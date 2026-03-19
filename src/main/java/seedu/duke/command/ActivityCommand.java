package seedu.duke.command;

import seedu.duke.model.ActivityList;

public abstract class ActivityCommand {

    protected ActivityList activityList;

    public ActivityCommand(ActivityList activityList) {
        this.activityList = activityList;
    }

    public abstract String execute(String tripName);

}
