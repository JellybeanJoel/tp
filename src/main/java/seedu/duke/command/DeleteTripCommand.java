package seedu.duke.command;

import seedu.duke.model.Trip;
import seedu.duke.model.TripList;

public class DeleteTripCommand extends TripCommand {
    private final int index;

    public DeleteTripCommand(TripList tripList, int index) {
        super(tripList);
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            if (index < 0 || index >= tripList.size()) {
                return "Invalid trip index.";
            }
            Trip removedTrip = tripList.get(index);
            tripList.remove(removedTrip);
            return "Deleted trip: " + removedTrip.getDestination();
        } catch (NumberFormatException e) {
            return "Please enter a valid number for the trip index.";
        }
    }
    
}
