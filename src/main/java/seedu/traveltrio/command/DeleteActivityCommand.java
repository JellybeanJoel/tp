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

        Activity removedActivity = activityList.remove(zeroBasedIndex);

        return "Activity deleted:\n\n" + removedActivity.getName() + "\n";
    }
}

