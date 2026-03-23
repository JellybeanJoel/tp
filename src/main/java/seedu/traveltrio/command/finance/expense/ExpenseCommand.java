package seedu.traveltrio.command.finance.expense;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.BudgetList;

public abstract class ExpenseCommand {
    protected BudgetList budgetList;
    protected ActivityList activityList;

    public ExpenseCommand(BudgetList budgetList, ActivityList activityList) {
        this.budgetList = budgetList;
        this.activityList = activityList;
    }

    public abstract String execute() throws TravelTrioException;

}
