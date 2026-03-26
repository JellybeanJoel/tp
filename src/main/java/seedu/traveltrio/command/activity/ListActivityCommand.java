package seedu.traveltrio.command.activity;

import seedu.traveltrio.TravelTrioException;
import seedu.traveltrio.model.activity.ActivityList;

public class ListActivityCommand extends ActivityCommand {

    public ListActivityCommand(ActivityList activityList){
        super(activityList);
    }

    public String execute(String tripName) throws TravelTrioException {
        if (activityList.isEmpty()){
            throw new TravelTrioException("The itinerary is Empty.");
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Itinerary for ").append(tripName).append(":\n");

        String header = String.format("%-3s | %-25s | %-15s | %-12s | %-18s",
                "No", "Activity", "Location", "Date", "Time");
        int dividerLength = header.length();

        sb.append(header).append("\n");
        sb.append("-".repeat(dividerLength)).append("\n");

        for (int i = 0; i < activityList.size(); i++) {
            sb.append(activityList.get(i).formatForTableRow(i + 1)).append("\n");
        }
        return sb.toString();

    }
}
