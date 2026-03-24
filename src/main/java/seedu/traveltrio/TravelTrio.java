package seedu.traveltrio;

import seedu.traveltrio.model.trip.TripList;

/**
 * Main entry point for the TravelTrio application.
 * This class initializes core components and runs the main command loop.
 */
public class TravelTrio {
    private static final TripList tripList = new TripList();
    private static final Ui ui = new Ui();
    private static final CommandProcessor processor = new CommandProcessor(tripList, ui);

    /**
     * Main method that drives the application.
     * Displays a welcome message upon entering and enters a loop to read user inputs until user exits.
     *
     * @param args Command line arguments passed at execution.
     */
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


