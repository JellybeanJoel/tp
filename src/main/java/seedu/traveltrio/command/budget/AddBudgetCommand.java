package seedu.traveltrio.command.budget;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.BudgetList;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.budget.Budget;

public class AddBudgetCommand extends BudgetCommand {
    private final double totalBudget;

    public AddBudgetCommand(BudgetList budgetList, ActivityList activityList, Activity activity, double totalBudget) {
        super(budgetList, activityList , activity);
        this.totalBudget = totalBudget;
    }

    @Override
    public String execute() {
        if (activity == null) {
            return "Please select an activity to add a budget for.";
        }
        Budget newBudget = new Budget(totalBudget, activity);
        budgetList.addBudget(activity, newBudget);
        return "Added budget for " + activity.getName() + ": $" + String.format("%.2f", totalBudget);
    }
    
}
