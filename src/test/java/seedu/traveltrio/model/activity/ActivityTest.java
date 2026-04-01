package seedu.traveltrio.model.activity;

import org.junit.jupiter.api.Test;
import seedu.traveltrio.TravelTrioException;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    @Test
    public void overlapsWith_sameDateOverlappingTime_returnsTrue() throws TravelTrioException {
        Activity a1 = new Activity("Diving", "Okinawa Sea", "2026-12-10", "09:00", "11:00");
        Activity a2 = new Activity("Hiking", "Mount Fuji", "2026-12-10", "10:00", "12:00");

        assertTrue(a1.overlapsWith(a2));
    }

    @Test
    public void overlapsWith_sameDateNonOverlappingTime_returnsFalse() throws TravelTrioException {
        Activity a1 = new Activity("Diving", "Okinawa Sea", "2026-12-10", "09:00", "11:00");
        Activity a2 = new Activity("Hiking", "Mount Fuji", "2026-12-10", "11:30", "14:00");

        assertFalse(a1.overlapsWith(a2));
    }
}

