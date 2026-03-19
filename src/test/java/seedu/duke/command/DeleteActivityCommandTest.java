package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.model.Activity;
import seedu.duke.model.ActivityList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteActivityCommandTest {

    //positve
    @Test
    void execute_validIndex_successfulMessageAndRemovedFromList() {
        ActivityList activityList = new ActivityList();
        activityList.add(new Activity("Hiking at Mount Fuji", "Mount Fuji", "2026-06-28",
                "08:00", "14:00"));
        String tripName = "Japan Trip";

        DeleteActivityCommand deleteCommand = new DeleteActivityCommand(activityList, 1);
        String message = deleteCommand.execute(tripName);

        assertEquals("Activity deleted:\n\nHiking at Mount Fuji\n", message);
        assertTrue(activityList.isEmpty());
    }

    //negative
    @Test
    void execute_invalidIndex_errorMessage() {
        ActivityList activityList = new ActivityList();
        activityList.add(new Activity("Tokyo Skytree", "Tokyo", "2026-06-29",
                "09:00", "11:00"));
        String tripName = "Japan Trip";

        // Try to delete index 0 (user input is 1-based, so 0 is invalid)
        DeleteActivityCommand deleteCommand = new DeleteActivityCommand(activityList, 0);
        String message = deleteCommand.execute(tripName);

        // Assert error message and ensure the item was NOT deleted
        assertEquals("Oops! Invalid activity index provided.", message);
        assertEquals(1, activityList.size());
    }
}
