package seedu.traveltrio.command.finance.budget;

import org.junit.jupiter.api.Test;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.Budget;
import seedu.traveltrio.model.budget.BudgetList;
import seedu.traveltrio.model.trip.Trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BudgetSummaryCommandTest {

    @Test
    public void execute_noBudgetsAdded_throwsException() throws TravelTrioException {
        Trip trip = new Trip("Test Trip", "2026-12-01", "2026-12-30");
        BudgetList budgetList = new BudgetList();
        ActivityList activityList = new ActivityList(trip);

        // Populate activities but no budgets
        activityList.add(new Activity("Hiking", "Mount Fuji", "2026-12-10", "09:00", "13:00"));

        BudgetSummaryCommand command = new BudgetSummaryCommand(budgetList, activityList);

        TravelTrioException exception = assertThrows(TravelTrioException.class, () -> command.execute());
        assertEquals("No budgets added yet.", exception.getMessage());
    }

    @Test
    public void execute_validBudgets_returnsCorrectSummary() throws TravelTrioException {
        Trip trip = new Trip("Test Trip", "2026-12-01", "2026-12-30");
        BudgetList budgetList = new BudgetList();
        ActivityList activityList = new ActivityList(trip);

        Activity activity1 = new Activity("Hiking", "Mount Fuji", "2026-12-10", "09:00", "13:00");
        Activity activity2 = new Activity("Night Swim", "Hotel", "2026-12-11", "16:00", "17:00");

        activityList.add(activity1);
        activityList.add(activity2);

        // Manually setup budgets and expenses for the test
        Budget budget1 = new Budget(200.00, activity1);
        Budget budget2 = new Budget(20.00, activity2);

        budgetList.addBudget(activity1, budget1);
        budgetList.addBudget(activity2, budget2);

        // Simulating some actual expenses
        budgetList.setExpense(activity1, 50.00);

        BudgetSummaryCommand command = new BudgetSummaryCommand(budgetList, activityList);
        String result = command.execute();

        // Construct expected string format based on Budget#toString()
        String expected = "Total trip budget: $220.00\n" +
                "Total expense: $50.00\n" +
                "Total remaining budget: $170.00\n" +
                "Exchange rate: 1 Foreign Currency = 1.00 Home Currency\n\n" +
                "Budget Breakdown:\n" +
                "1. Hiking\n   Total: $200.00 | Spent: $50.00 | Remaining: $150.00\n" +
                "2. Night Swim\n   Total: $20.00 | Spent: $0.00 | Remaining: $20.00\n";

        assertEquals(expected, result);
    }
}
