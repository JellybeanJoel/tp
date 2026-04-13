package seedu.traveltrio.command.finance.expense;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.budget.BudgetList;

/**
 * Represents a command to set a maximum daily spending limit for the trip. A SetDailyLimitCommand
 * object updates the budget list with a threshold to help warn users against overspending on a single day.
 */
public class SetDailyLimitCommand {

    private final BudgetList budgetList;
    private final double amount;

    /**
     * Initializes a SetDailyLimitCommand with the specified budget list and limit amount.
     *
     * @param budgetList The list containing the financial data and budgets for the current trip.
     * @param amount The maximum monetary amount the user intends to spend per day.
     */
    public SetDailyLimitCommand(BudgetList budgetList, double amount) {
        this.budgetList = budgetList;
        this.amount = amount;
    }

    /**
     * Executes the command to update the trip's daily spending limit.
     *
     * @return Formatted string confirming the newly set daily spending limit.
     * @throws TravelTrioException If an error occurs while setting the limit (e.g., if the amount is negative).
     */
    public String execute() throws TravelTrioException {
        budgetList.setDailySpendingLimit(amount);
        return String.format("Daily spending limit has been set to $%.2f.", amount);
    }
}
