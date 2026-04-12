package seedu.traveltrio.command.activity;


import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.BudgetList;

/**
 * Represents a command to delete an activity from the itinerary.
 * Also removes any associated budget entries for the deleted activity.
 */
public class DeleteActivityCommand extends ActivityCommand {
    private final int index;
    private final BudgetList budgetList;

    /**
     * Constructs a DeleteActivityCommand with the specified activity list, budget list, and index.
     *
     * @param activityList the activity list to remove the activity from
     * @param budgetList the budget list to remove associated budget entries from
     * @param index the 1-based index of the activity to delete
     */
    public DeleteActivityCommand(ActivityList activityList, BudgetList budgetList, int index) {
        super(activityList);
        this.index = index;
        this.budgetList = budgetList;
    }

    /**
     * Executes the command to delete an activity from the itinerary.
     * Also removes any budget entry associated with the deleted activity.
     *
     * @param tripName the name of the current trip
     * @return a success message with the name of the deleted activity
     * @throws TravelTrioException if the specified index is invalid
     */
    public String execute(String tripName) throws TravelTrioException {
        int zeroBasedIndex = index - 1;

        if (zeroBasedIndex < 0 || zeroBasedIndex >= activityList.size()) {
            throw new TravelTrioException("Activity number " + index + " does not exist.");
        }

        assert activityList.isTripOpen() : "Cannot delete an activity if no trip is open.";

        int sizeBefore = activityList.size();
        Activity removedActivity = activityList.remove(zeroBasedIndex);
        budgetList.removeBudget(removedActivity);
        assert activityList.size() == sizeBefore - 1 : "Activity list size should decrease by 1.";


        return "Activity deleted:\n\n" + removedActivity.getName() + "\n";
    }
}

