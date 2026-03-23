package seedu.traveltrio.command.finance.expense;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.BudgetList;

public abstract class ExpenseCommand {
    protected BudgetList budgetList;
    protected ActivityList activityList;
    protected Activity activity;

    public ExpenseCommand(BudgetList budgetList, ActivityList activityList, Activity activity) {
        this.budgetList = budgetList;
        this.activityList = activityList;
        this.activity = activity;
    }

    public abstract String execute() throws TravelTrioException;

}
