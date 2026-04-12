package seedu.traveltrio.command.activity;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.ActivityList;

/**
 * Abstract base class for all activity-related commands.
 * Provides common functionality and ensures a trip is open before executing.
 */
public abstract class ActivityCommand {
    protected ActivityList activityList;

    /**
     * Constructs an ActivityCommand with the specified activity list.
     *
     * @param activityList the activity list to operate on
     */
    public ActivityCommand(ActivityList activityList) {
        this.activityList = activityList;
    }

    /**
     * Runs the command after verifying that a trip is currently open.
     *
     * @param tripName the name of the current trip
     * @return the result message from executing the command
     * @throws TravelTrioException if no trip is currently open
     */
    public String run(String tripName) throws TravelTrioException {
        if (!activityList.isTripOpen()) {
            throw new TravelTrioException("Please open a trip before managing activities.");
        } else {
            return execute(tripName);
        }
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param tripName the name of the current trip
     * @return the result message from the command execution
     * @throws TravelTrioException if an error occurs during execution
     */
    public abstract String execute(String tripName) throws TravelTrioException;
}
