package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.model.Activity;
import seedu.duke.model.ActivityList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditActivityCommandTest {

    //positive
    @Test
    void execute_validIndexAndPartialUpdate_successfulMessage() {
        ActivityList activityList = new ActivityList();
        activityList.add(new Activity("Hiking at Mount Fuji", "Mount Fuji", "2026-06-28", "08:00", "14:00"));
        String tripName = "Japan Trip";

        EditActivityCommand editCommand = new EditActivityCommand(
                activityList, 1, null, null, null, "10:00", "16:00"
        );
        String message = editCommand.execute(tripName);

        assertEquals("""
                Activity updated:
                
                Hiking at Mount Fuji
                📍 Location: Mount Fuji
                📆 Date: 2026-06-28
                🕛 Time: 10:00 to 16:00
                """, message);
    }

    //negative
    @Test
    void execute_invalidIndex_errorMessage() {
        ActivityList activityList = new ActivityList();
        activityList.add(new Activity("Tokyo Skytree", "Tokyo", "2026-06-29", "09:00", "11:00"));
        String tripName = "Japan Trip";

        // Try to edit index 5 (which doesn't exist)
        EditActivityCommand editCommand = new EditActivityCommand(
                activityList, 5, "New Name", null, null, null, null
        );
        String message = editCommand.execute(tripName);

        assertEquals("Oops! Invalid activity index provided.", message);
    }

}
