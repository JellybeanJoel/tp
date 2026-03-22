package seedu.traveltrio;

import seedu.traveltrio.command.activity.AddActivityCommand;
import seedu.traveltrio.command.activity.DeleteActivityCommand;
import seedu.traveltrio.command.activity.EditActivityCommand;
import seedu.traveltrio.command.activity.ListActivityCommand;
import seedu.traveltrio.command.budget.AddBudgetCommand;
import seedu.traveltrio.command.budget.BudgetSummaryCommand;
import seedu.traveltrio.command.trip.AddTripCommand;
import seedu.traveltrio.command.trip.DeleteTripCommand;
import seedu.traveltrio.command.trip.ListTripCommand;
import seedu.traveltrio.command.trip.OpenTripCommand;
import seedu.traveltrio.model.trip.Trip;
import seedu.traveltrio.model.trip.TripList;

public class CommandProcessor {
    private final TripList tripList;
    private final Ui ui;
    private Trip openTrip = null;

    public CommandProcessor(TripList tripList, Ui ui) {
        this.tripList = tripList;
        this.ui = ui;
    }

    public void process(String command) {
        try {
            switch (command) {
            case "addtrip":
                handleAddTrip();
                break;

            case "listtrip":
                printTripList();
                break;

            case "opentrip":
                handleOpenTrip();
                break;

            case "deletetrip":
                handleDeleteTrip();
                break;

            case "addactivity":
                handleAddActivity();
                break;

            case "listactivity":
                handleListActivity();
                break;

            case "editactivity":
                handleEditActivity();
                break;

            case "deleteactivity":
                handleDeleteActivity();
                break;

            case "addbudget":
                handleAddBudget();
                break;

            case "budgetsummary":
                handleBudgetSummary();
                break;
            default:
                handleUnknownCommand();
            }
        } catch (Exception e){
            ui.showMessage("Error. " + e.getMessage());
        }
    }

    private void handleUnknownCommand() {
        ui.showMessage("Unknown command. Available commands: addtrip, listtrip, "
                + "opentrip, deletetrip, addactivity, listactivity, "
                + "editactivity, deleteactivity, addbudget, budgetsummary, exit");
    }

    private void handleBudgetSummary() {
        ensureTripOpen();
        ui.showMessage(new BudgetSummaryCommand(openTrip.getBudgets(),
                openTrip.getActivities()).execute());
    }

    private void handleAddBudget() {
        ensureTripOpen();
        if (openTrip.getActivities().isEmpty()) {
            ui.showMessage("No activities found. Please add an activity before setting a budget.");
            return;
        }
        printActivityList();
        int budgetActivityIdx = ui.promptInt("Enter the number of the activity to add a budget for. ");
        double budgetAmount = ui.promptDouble("Enter budget amount ($)");
        ui.showMessage(new AddBudgetCommand(openTrip.getBudgets(),
                openTrip.getActivities(), openTrip.getActivities().get(budgetActivityIdx - 1), budgetAmount)
                .execute());
    }

    private void handleDeleteActivity() {
        ensureTripOpen();
        printActivityList();
        int actIdx = ui.promptInt("Enter the number of the activity to delete");
        ui.showMessage(new DeleteActivityCommand(openTrip.getActivities(), actIdx)
                .execute(openTrip.getName()));
    }

    private void handleEditActivity() {
        ensureTripOpen();
        printActivityList();
        int activityIdx = ui.promptInt("Enter the number of the activity to edit");
        ui.showMessage("Leave any field blank to keep current values.");
        String newTitle = ui.promptField("New Title");
        String newLocation = ui.promptField("New Location");
        String newDate = ui.promptField("New Date (YYYY-MM-DD)");
        String newStartTime = ui.promptField("New Start Time (HH:MM)");
        String newEndTime = ui.promptField("New End Time (HH:MM)");
        ui.showMessage(new EditActivityCommand(openTrip.getActivities(),
                activityIdx, newTitle, newLocation, newDate, newStartTime, newEndTime)
                .execute(openTrip.getName()));
    }

    private void handleListActivity() {
        ensureTripOpen();
        printActivityList();
    }

    private void printActivityList() {
        ui.showMessage(new ListActivityCommand(openTrip.getActivities()).execute(openTrip.getName()));
    }

    private void handleAddActivity() {
        ensureTripOpen();

        String tripStartDate = openTrip.getStartDate();
        String tripEndDate = openTrip.getEndDate();

        String title = ui.promptField("Activity Title");
        String location = ui.promptField("Location");
        String date = ui.promptField("Date (YYYY-MM-DD)");

        if (date.compareTo(tripStartDate) < 0 || date.compareTo(tripEndDate) > 0) {
            ui.showMessage("Error. Activity date (" + date + ") " + "is outside of your trip dates.");
            ui.showMessage("Your trip is from " + tripStartDate + " to " + tripEndDate + ".");
            return;
        }

        String startTime = ui.promptField("Start Time (HH:MM)");
        String endTime = ui.promptField("End Time (HH:MM)");
        ui.showMessage(new AddActivityCommand(openTrip.getActivities(),
                title, location, date, startTime, endTime)
                .execute(openTrip.getName()));
    }

    private void handleDeleteTrip() {
        printTripList();
        int tripIdx = ui.promptInt("Enter the number of the trip to delete");
        ui.showMessage(new DeleteTripCommand(tripList, tripIdx).execute());
        // If the open trip is the one deleted, reset opentrip
        if (openTrip != null && !tripList.contains(openTrip)) {
            openTrip = null;
            ui.showMessage("The active trip was deleted. Use 'opentrip' to open a trip.");
        }
    }

    private void handleOpenTrip() {
        printTripList();
        int idx = ui.promptInt("Enter the number of the trip to open");
        openTrip = tripList.get(idx - 1);
        assert openTrip != null;
        ui.showMessage(new OpenTripCommand(tripList, idx).execute());
    }

    private void printTripList() {
        ui.showMessage(new ListTripCommand(tripList).execute());
    }

    private void handleAddTrip() {
        String name = ui.promptField("Trip Name");
        String start = ui.promptField("Start Date (YYYY-MM-DD)");
        String end = ui.promptField("End Date (YYYY-MM-DD)");
        System.out.println(new AddTripCommand(tripList, name, start, end).execute());
    }

    private void ensureTripOpen() {
        if (this.openTrip == null) {
            throw new IllegalStateException("You need to open a trip first. (Use 'opentrip')");
        }
        assert openTrip != null : "openTrip should not be null after check";
    }
}
