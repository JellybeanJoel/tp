package seedu.traveltrio.model.budget;

import java.util.Map;
import java.util.HashMap;
import seedu.traveltrio.model.activity.Activity;

public class BudgetList {
    private final Map<Activity, Budget> budgets;
    private double totalTripBudget;

    public BudgetList() {
        this.budgets = new HashMap<>();
        this.totalTripBudget = 0;
    }

    public void addBudget(Activity activity, Budget budget) {
        budgets.put(activity, budget);
        totalTripBudget += budget.getTotalBudget();
    }

    public void removeBudget(Activity activity) {
        totalTripBudget -= budgets.get(activity).getTotalBudget();
        budgets.remove(activity);
    }

    public Budget getBudget(Activity activity) {
        return budgets.get(activity);
    }

    public double getTotalTripBudget() {
        return totalTripBudget;
    }

    public Map<Activity, Budget> getBudgets() {
        return budgets;
    }
}
