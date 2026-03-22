package seedu.traveltrio;

import seedu.traveltrio.model.trip.TripList;

public class TravelTrio {
    private static final TripList tripList = new TripList();
    private static final Ui ui = new Ui();
    private static final CommandProcessor processor = new CommandProcessor(tripList, ui);

    public static void main(String[] args) {
        ui.showWelcomeMessage();

        while (true) {
            String command = ui.readCommand();

            if (command.equals("exit")) {
                ui.showMessage("Goodbye! Happy Travels!");
                break;
            }

            processor.process(command);
        }
    }


}


