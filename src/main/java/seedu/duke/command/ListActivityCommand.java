package seedu.duke.command;

import seedu.duke.model.ActivityList;

public class ListActivityCommand extends ActivityCommand {

    public ListActivityCommand(ActivityList activityList){
        super(activityList);
    }

    public String execute(String tripName){
        if (activityList.isEmpty()){
            return "The itinerary is Empty.";
        }

        String listToPrint = "Itinerary for " + tripName + ":\n\n";
        for (int i = 0; i < activityList.size(); i++){
            listToPrint += ((i+1) + ". " + activityList.get(i).formatForList() + "\n\n");
        }
        return listToPrint;

    }
}
