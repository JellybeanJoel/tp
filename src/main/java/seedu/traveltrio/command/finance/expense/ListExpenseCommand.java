package seedu.traveltrio.command.finance.expense;

import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.budget.Budget;
import seedu.traveltrio.model.trip.Trip;

public class ListExpenseCommand extends ExpenseCommand {
    StringBuilder sb = new StringBuilder();

    protected Trip trip;

    public ListExpenseCommand(Trip openTrip) {
        super(openTrip.getBudgets(), openTrip.getActivities());
        trip = openTrip;
    }

    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Expense Comparison for ").append(trip.getName()).append(":\n\n");

        double totalPlanned = 0;
        double totalActual = 0;

        for (int i = 0; i < activityList.size(); i++) {
            Activity activity = activityList.get(i);
            Budget budget = budgetList.getBudget(activity);

            if (budget == null) {
                continue;
            }

            double planned = budget.getTotalBudget();
            double actual = budget.getAmountSpent();

            totalPlanned += planned;
            totalActual += actual;

            sb.append(i + 1).append(". ").append(activity.getName()).append("\n");
            sb.append(String.format("   Planned: $%-15.2f Actual: $%.2f%n%n", planned, actual));
        }

        sb.append("_______________________________________________\n");
        sb.append(String.format("Total Planned Budget: $%.2f%n", totalPlanned));
        sb.append(String.format("Total Actual Spending:  $%.2f%n", totalActual));
        sb.append(String.format("Remaining Budget: $%.2f%n", totalPlanned - totalActual));

        return sb.toString();
    }


}
