package seedu.traveltrio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.traveltrio.model.trip.TripList;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    public void load_validTripAndActivity_success() throws Exception {
        File tempFile = tempDir.resolve("happy.txt").toFile();
        FileWriter writer = new FileWriter(tempFile);
        writer.write("***************************************************************************\n");
        writer.write("Trip: japan | From: 2025-01-01 | To: 2025-01-10 | Total Budget: 0.00\n");
        writer.write("***************************************************************************\n");
        writer.write("=== Date: 2025-01-02 ===\n");
        writer.write("Title: Sightseeing\n");
        writer.write("    Location: Osaka Castle\n");
        writer.write("    Start Time: 09:00\n");
        writer.write("    End Time:   11:00\n");
        writer.close();

        Storage storage = new Storage(tempFile.getAbsolutePath());
        TripList trips = storage.load();

        assertEquals(1, trips.size());
        assertEquals("japan", trips.get(0).getName());
        assertEquals(1, trips.get(0).getActivities().size());
        assertEquals("Sightseeing", trips.get(0).getActivities().get(0).getName());
    }

    @Test
    public void load_activityWithoutTrip_throwsException() throws Exception {
        File tempFile = tempDir.resolve("orphan.txt").toFile();
        FileWriter writer = new FileWriter(tempFile);
        // Missing trip header entirely
        writer.write("=== Date: 2025-01-02 ===\n");
        writer.write("Title: Orphan Activity\n");
        writer.write("    Location: Nowhere\n");
        writer.write("    Start Time: 00:00\n");
        writer.write("    End Time:   01:00\n");
        writer.close();

        Storage storage = new Storage(tempFile.getAbsolutePath());

        assertThrows(TravelTrioException.class, storage::load);
    }
}
