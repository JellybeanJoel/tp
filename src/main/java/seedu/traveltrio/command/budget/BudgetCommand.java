package seedu.traveltrio.command.budget;

import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.BudgetList;
import seedu.traveltrio.model.activity.Activity;

public abstract class BudgetCommand {
    protected BudgetList budgetList;
    protected ActivityList activityList;
    protected Activity activity;

    public BudgetCommand(BudgetList budgetList, ActivityList activityList, Activity activity) {
        this.budgetList = budgetList;
        this.activityList = activityList;
        this.activity = activity;
    }

    public String run() {
        if (!activityList.isTripOpen()) {
            return "Please open a trip before managing budgets.";
        } else {
            return execute();
        }
    }

    public abstract String execute();
}
