package seedu.traveltrio.command.finance.budget;

import org.junit.jupiter.api.Test;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.BudgetList;
import seedu.traveltrio.model.trip.Trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SetBudgetCommandTest {

    @Test
    public void execute_validBudgetHomeCurrency_success() throws TravelTrioException {
        Trip trip = new Trip("Test Trip", "2026-12-01", "2026-12-30");
        BudgetList budgetList = new BudgetList();
        ActivityList activityList = new ActivityList(trip);
        Activity activity = new Activity("Hiking", "Mount Fuji", "2026-12-10", "09:00", "13:00");
        activityList.add(activity);

        SetBudgetCommand command = new SetBudgetCommand(budgetList, activityList, activity, 200.00, false);
        String result = command.execute();

        assertEquals("Added budget for Hiking: $200.00", result);
        assertNotNull(budgetList.getBudget(activity));
        assertEquals(200.00, budgetList.getBudget(activity).getActivityBudget());
    }

    @Test
    public void execute_validBudgetForeignCurrency_success() throws TravelTrioException {
        Trip trip = new Trip("Test Trip", "2026-12-01", "2026-12-30");
        BudgetList budgetList = new BudgetList();
        budgetList.setExchangeRate(1.50); // 1 Foreign = 1.50 Home
        ActivityList activityList = new ActivityList(trip);
        Activity activity = new Activity("Night Swim", "Hotel", "2026-12-11", "16:00", "17:00");
        activityList.add(activity);

        SetBudgetCommand command = new SetBudgetCommand(budgetList, activityList, activity, 100.00, true);
        String result = command.execute();

        // 100 foreign * 1.50 exchange rate = 150.00 home currency
        assertEquals("Added budget for Night Swim: $150.00", result);
        assertNotNull(budgetList.getBudget(activity));
        assertEquals(150.00, budgetList.getBudget(activity).getActivityBudget());
    }

    @Test
    public void execute_negativeBudgetAmount_throwsException() throws TravelTrioException {
        Trip trip = new Trip("Test Trip", "2026-12-01", "2026-12-30");
        BudgetList budgetList = new BudgetList();
        ActivityList activityList = new ActivityList(trip);
        Activity activity = new Activity("Hiking", "Mount Fuji", "2026-12-10", "09:00", "13:00");
        activityList.add(activity);

        SetBudgetCommand command = new SetBudgetCommand(budgetList, activityList, activity, -50.00, false);

        TravelTrioException exception = assertThrows(TravelTrioException.class, () -> command.execute());
        assertEquals("Budget amount cannot be negative.", exception.getMessage());
    }
}
