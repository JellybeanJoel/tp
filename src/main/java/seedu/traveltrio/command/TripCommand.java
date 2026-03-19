package seedu.traveltrio.command;

import seedu.traveltrio.model.TripList;

public abstract class TripCommand {

    protected TripList tripList;

    public TripCommand(TripList tripList) {
        this.tripList = tripList;
    }
    
    public abstract String execute();

}

