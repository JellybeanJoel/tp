package seedu.traveltrio.model.trip;

import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.Budget;
import seedu.traveltrio.model.budget.BudgetList;

public class Trip {
    private String name;
    private String startDate;
    private String endDate;
    private final ActivityList activities;
    private final BudgetList budgets;
    private boolean isOpen;

    public Trip(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activities = new ActivityList(this);
        this.budgets = new BudgetList();
        this.isOpen = false;
    }

    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();

        // Add trip details
        sb.append(String.format("T | %s | %s | %s\n", name, startDate, endDate));

        for (int i = 0; i < activities.size(); i++) {
            Activity act = activities.get(i);

            // Add activity details
            sb.append("A | ").append(act.toFileFormat()).append("\n");

            // Add budget details if it exists
            Budget b = budgets.getBudget(act);
            if (b != null) {
                // Format: B | Amount | ActualExpense
                sb.append(String.format("B | %.2f | %.2f\n",
                        b.getTotalBudget(), b.getAmountSpent()));
            }
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ActivityList getActivities() {
        return activities;
    }

    public BudgetList getBudgets() {
        return budgets;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return name + " (" + startDate + " to " + endDate + ")";
    }

    public String formatForList() {
        String result = name + "\n";
        result += "   Start: " + startDate + "\n";
        result += "   End:   " + endDate;
        return result;
    }
}

