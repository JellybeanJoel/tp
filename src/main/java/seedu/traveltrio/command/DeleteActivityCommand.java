package seedu.traveltrio.command;


import seedu.traveltrio.model.Activity;
import seedu.traveltrio.model.ActivityList;

public class DeleteActivityCommand extends ActivityCommand {
    private final int index;

    public DeleteActivityCommand(ActivityList activityList, int index) {
        super(activityList);
        this.index = index;
    }

    public String execute(String tripName) {
        int zeroBasedIndex = index - 1;

        if (zeroBasedIndex < 0 || zeroBasedIndex >= activityList.size()) {
            return "Oops! Invalid activity index provided.";
        }

        assert activityList.isTripOpen() : "Cannot delete an activity if no trip is open.";

        int sizeBefore = activityList.size();
        Activity removedActivity = activityList.remove(zeroBasedIndex);

        assert activityList.size() == sizeBefore - 1 : "Activity list size should decrease by 1.";

        return "Activity deleted:\n\n" + removedActivity.getName() + "\n";
    }
}

