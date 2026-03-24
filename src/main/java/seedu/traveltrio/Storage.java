package seedu.traveltrio;

import seedu.traveltrio.model.trip.Trip;
import seedu.traveltrio.model.trip.TripList;
import seedu.traveltrio.model.activity.Activity;
import seedu.traveltrio.model.budget.Budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Handles loading and saving trip data to a local file.
 * File stores all trip, activity and budget details.
 */
public class Storage {
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of trips and activities from the txt file from the previous session.
     *
     * @return A populated TripList or an empty one if no file exists.
     */
    public TripList load() {
        TripList trips = new TripList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return trips;
            }

            Scanner fileScanner = new Scanner(file);
            Trip currentTrip = null;
            Activity lastActivity = null;

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                String type = parts[0];

                switch (type) {
                case "T":
                    // Format: T | Name | Start | End
                    currentTrip = new Trip(parts[1], parts[2], parts[3]);
                    trips.add(currentTrip);
                    lastActivity = null;
                    break;

                case "A":
                    // Format: A | Title | Location | Date | Start | End
                    assert currentTrip != null : "Storage Error: Activity found before any Trip was declared in file.";

                    lastActivity = new Activity(parts[1], parts[2], parts[3], parts[4], parts[5]);
                    currentTrip.getActivities().add(lastActivity);

                    break;

                case "B":
                    // Format: B | totalAmount | amountSpent
                    assert currentTrip != null : "Storage Error: Budget found without a Trip context.";
                    assert lastActivity != null : "Storage Error: Budget found without a preceding Activity.";

                    double total = Double.parseDouble(parts[1]);
                    double spent = Double.parseDouble(parts[2]);

                    Budget budget = new Budget(total, lastActivity);
                    budget.setExpense(spent);
                    currentTrip.getBudgets().addBudget(lastActivity, budget);

                    break;
                default:
                    logger.log(Level.WARNING, "Unknown line type encountered in storage: " + type);
                    break;
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return trips;
    }

    /**
     * Saves the current list of trips to the file.
     */
    public void save(TripList trips) {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < trips.size(); i++) {
                // Handles the T, A, and B lines
                writer.write(trips.get(i).toFileFormat());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
