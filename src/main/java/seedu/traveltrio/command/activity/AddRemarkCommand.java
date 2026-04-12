package seedu.traveltrio.command.activity;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;

/**
 * Represents a command to add a remark to an existing activity.
 * Allows users to attach notes or reminders to specific activities.
 */
public class AddRemarkCommand extends ActivityCommand {

    private final int index;
    private final String remark;

    /**
     * Constructs an AddRemarkCommand with the specified activity list, index, and remark.
     *
     * @param activityList the activity list containing the activity to update
     * @param index the 1-based index of the activity to add a remark to
     * @param remark the remark text to add to the activity
     */
    public AddRemarkCommand(ActivityList activityList, int index, String remark) {
        super(activityList);
        this.index = index;
        this.remark = remark;
    }

    /**
     * Executes the command to add a remark to an activity.
     * Validates the index before updating the activity.
     *
     * @param tripName the name of the current trip
     * @return a success message displaying the activity name and added remark
     * @throws TravelTrioException if the specified index is invalid
     */
    @Override
    public String execute(String tripName) throws TravelTrioException {

        if (index < 1 || index > activityList.size()) {
            throw new TravelTrioException("Invalid activity index.");
        }

        Activity activity = activityList.get(index - 1);
        activity.setRemark(remark);

        return "===========================================================\n"
                + "Remark added to activity in " + tripName + ":\n\n"
                + activity.getName() + "\n"
                + "Remark: " + remark + "\n"
                + "===========================================================";
    }
}
