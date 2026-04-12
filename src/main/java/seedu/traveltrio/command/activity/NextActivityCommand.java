package seedu.traveltrio.command.activity;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;

import java.time.LocalDateTime;

/**
 * Represents a command to find and display the next upcoming activity.
 * Identifies the earliest activity scheduled after the current date and time.
 */
public class NextActivityCommand extends ActivityCommand {

    /**
     * Constructs a NextActivityCommand with the specified activity list.
     *
     * @param activityList the activity list to search for the next activity
     */
    public NextActivityCommand(ActivityList activityList) {
        super(activityList);
    }

    /**
     * Executes the command to find the next upcoming activity.
     * Compares each activity's start date and time against the current time.
     *
     * @param tripName the name of the current trip
     * @return a formatted display of the next upcoming activity, or a message if none found
     * @throws TravelTrioException if an error occurs during execution
     */
    @Override
    public String execute(String tripName) throws TravelTrioException {

        if (activityList.isEmpty()) {
            return "No activities found in this trip.";
        }

        LocalDateTime now = LocalDateTime.now();

        Activity nextActivity = null;
        LocalDateTime earliestTime = null;

        for (Activity activity : activityList.getAll()) {
            LocalDateTime activityTime = activity.getStartDateTime();

            if (activityTime == null) {
                continue;
            }

            if (activityTime.isAfter(now)) {
                if (earliestTime == null || activityTime.isBefore(earliestTime)) {
                    earliestTime = activityTime;
                    nextActivity = activity;
                }
            }
        }

        if (nextActivity == null) {
            return "No upcoming activities found.";
        }

        return "Next activity for " + tripName + ":\n\n"
                + nextActivity.formatForDisplay();
    }
}
