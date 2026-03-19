package seedu.duke.command;

import seedu.duke.model.Activity;
import seedu.duke.model.ActivityList;

public class EditActivityCommand extends ActivityCommand{
    private final int index;
    private final String name;
    private final String location;
    private final String date;
    private final String start;
    private final String end;


    public EditActivityCommand(ActivityList activityList, int index, String name, String location,
                               String date, String start, String end) {
        super(activityList);
        this.index = index;
        this.name = name;
        this.location = location;
        this.date = date;
        this.start = start;
        this.end = end;
    }


    public String execute(String tripName) {
        int zeroBasedIndex = index - 1;

        if (zeroBasedIndex < 0 || zeroBasedIndex >= activityList.size()) {
            return "Oops! Invalid activity index provided.";
        }

        Activity activity = activityList.get(zeroBasedIndex);

        if (name != null) {
            activity.setName(name);
        }
        if (location != null) {
            activity.setLocation(location);
        }
        if (date != null) {
            activity.setDate(date);
        }
        if (start != null) {
            activity.setStart(start);
        }
        if (end != null) {
            activity.setEnd(end);
        }

        return "Activity updated:\n\n" + activity + "\n";
    }

}
