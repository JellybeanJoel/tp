package seedu.traveltrio.command;

import seedu.traveltrio.model.TripList;

public class DeleteTripCommand extends TripCommand {
    private final int index;

    public DeleteTripCommand(TripList tripList, int tripNumber) {
        super(tripList);
        this.index = tripNumber - 1;
    }

    @Override
    public String execute() {
        try {
            if (index < 0 || index >= tripList.size()) {
                return "Invalid trip index.";
            }
            String removedTrip = tripList.get(index).toString();
            tripList.remove(index);
            return "Deleted trip: " + removedTrip;
        } catch (NumberFormatException e) {
            return "Please enter a valid number for the trip index.";
        }
    }
    
}
