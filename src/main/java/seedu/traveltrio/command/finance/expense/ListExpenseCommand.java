package seedu.traveltrio.command.finance.expense;

import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.budget.Budget;
import seedu.traveltrio.model.trip.Trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a command to display a chronologically sorted list of all expenses incurred during the trip.
 * A ListExpenseCommand object compiles all activities with an assigned budget, sorts them by date,
 * and outputs a formatted table comparing the expenses against the trip's daily limit and overall total.
 */
public class ListExpenseCommand extends ExpenseCommand {

    protected Trip trip;

    /**
     * Initializes a ListExpenseCommand with the currently opened trip.
     *
     * @param openTrip The active trip whose expenses are to be listed.
     */
    public ListExpenseCommand(Trip openTrip) {
        super(openTrip.getBudgets(), openTrip.getActivities());
        trip = openTrip;
    }

    /**
     * Executes the command to generate the expense comparison table.
     * Sorts the activities chronologically, aggregates the actual expenses, and formats the output
     * into a clear, readable table that includes daily limits and total spending.
     *
     * @return Formatted string containing the chronological table of expenses.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Expense Comparison for ").append(trip.getName()).append(":\n");
        if (trip.getBudgets().hasDailyLimitSet()) {
            sb.append("(Daily limit: ").append(
                    String.format("$%.2f)\n\n", trip.getBudgets().getDailySpendingLimit())
            );
        } else {
            sb.append("(Daily Limit: Not set)\n\n");
        }

        String rowFormat = "%-10s | %-18s | %10s\n";
        String divider = "----------------------------------------------------------------------\n";

        sb.append(String.format(rowFormat, "Date", "Activity", "Actual expense"));
        sb.append(divider);

        List<Activity> sortedActivities = new ArrayList<>();
        for (int i = 0; i < activityList.size(); i++) {
            Activity activity = activityList.get(i);
            if (budgetList.getBudget(activity) != null) {
                sortedActivities.add(activity);
            }
        }

        sortedActivities.sort(Comparator.comparing(
                activity -> activity.getDate() == null ? null : LocalDate.parse(activity.getDate()),
                Comparator.nullsLast(Comparator.naturalOrder())
        ));

        double totalActual = 0;

        String previousDate = null;

        for (Activity activity : sortedActivities) {
            Budget budget = budgetList.getBudget(activity);

            double actual = budget.getActualExpense();

            totalActual += actual;

            String currentDate = activity.getDate() != null ? activity.getDate() : "---";
            String displayDate = currentDate.equals(previousDate) ? "" : currentDate;

            sb.append(String.format(
                    rowFormat,
                    displayDate,
                    activity.getName(),
                    String.format("$%.2f", actual)
            ));

            previousDate = currentDate;
        }

        sb.append("\n");
        sb.append(divider);
        sb.append("Total expense: ").append(
                String.format("$%.2f", totalActual)
        );

        return sb.toString();
    }

}
