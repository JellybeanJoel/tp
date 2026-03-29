package seedu.traveltrio.command.finance.budget;

import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.activity.ActivityList;
import seedu.traveltrio.model.budget.BudgetList;
import seedu.traveltrio.TravelTrioException;

public class SetCurrencyCommand extends BudgetCommand{
    private final double exchangeRate;

    public SetCurrencyCommand(BudgetList budgetList, ActivityList activityList,
            Activity activity, double exchangeRate) {
        super(budgetList, activityList, activity);
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String execute() throws TravelTrioException {
        if (exchangeRate <= 0) {
            throw new TravelTrioException("Exchange rate must be a positive number.");
        }
        budgetList.setExchangeRate(exchangeRate);
        return "Currency exchange rate set to: 1 Foreign Currency = " 
                + String.format("%.4f", exchangeRate) + " Home Currency";
    }
}
