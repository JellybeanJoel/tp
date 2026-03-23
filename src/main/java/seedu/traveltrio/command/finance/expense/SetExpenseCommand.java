package seedu.traveltrio.command.finance.expense;
import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.budget.BudgetList;

public class SetExpenseCommand extends ExpenseCommand{
    private final double amount;
    public SetExpenseCommand(BudgetList budgetList, ActivityList activityList,
                             Activity activity, double amount){
        super(budgetList, activityList, activity);
        this.amount = amount;
    }

    @Override
    public String execute() throws TravelTrioException {
        budgetList.setExpense(activity, amount);
        return "Expense set for activity: " + activity.getName() +
                ". \nActual spending: $" + String.format("%.2f", amount);
    }
}
