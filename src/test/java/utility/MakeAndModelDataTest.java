package utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link MakeAndModelData}
 * Validates CSV parsing logic and mapping of car makes to models.
 */
class MakeAndModelDataTest {

    /** Temporary test file used for simulating CSV input. */
    private static final String TEST_CSV_PATH = "data/test_make_and_model.csv";

    /**
     * Sets up a temporary CSV file for testing.
     * @throws IOException
     */
    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV_PATH))) {
            writer.write("Make,Model\n"); // CSV Header
            writer.write("Toyota,Camry\n");
            writer.write("Toyota,Corolla\n");
            writer.write("Honda,Civic\n");
            writer.write("Honda,Accord\n");
            writer.write("Ford,F-150\n");
            writer.write("Ford,Mustang\n");
        }
    }

    /**
     * Tests if make and model data is correctly mapped from CSV.
     */
    @Test
    void testGetMakeModelMap_ValidData() {
        HashMap<String, List<String>> makeModelMap = MakeAndModelData.getMakeModelMap(TEST_CSV_PATH);

        assertEquals(3, makeModelMap.size());
        assertTrue(makeModelMap.containsKey("Toyota"));
        assertTrue(makeModelMap.containsKey("Honda"));
        assertTrue(makeModelMap.containsKey("Ford"));

        // Validate model lists
        assertEquals(Arrays.asList("Camry", "Corolla"), makeModelMap.get("Toyota"));
        assertEquals(Arrays.asList("Civic", "Accord"), makeModelMap.get("Honda"));
        assertEquals(Arrays.asList("F-150", "Mustang"), makeModelMap.get("Ford"));
    }

    /**
     * Tests behavior when the CSV file is empty.
     */
    @Test
    void testGetMakeModelMap_EmptyFile() throws IOException {
        // Create an empty file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV_PATH))) {
            writer.write("Make,Model\n"); // Only header, no data
        }

        HashMap<String, List<String>> makeModelMap = MakeAndModelData.getMakeModelMap(TEST_CSV_PATH);

        assertTrue(makeModelMap.isEmpty());
    }

    /**
     * Tests behavior when the CSV file contains malformed data.
     */
    @Test
    void testGetMakeModelMap_MalformedData() throws IOException {
        // Create a file with incorrect format
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV_PATH))) {
            writer.write("Make,Model\n");
            writer.write("Toyota,Camry\n"); // Valid
            writer.write("Honda,\n"); // Missing model
            writer.write("Ford Mustang\n"); // Missing comma separator
        }

        HashMap<String, List<String>> makeModelMap = MakeAndModelData.getMakeModelMap(TEST_CSV_PATH);

        assertEquals(1, makeModelMap.size());
        assertTrue(makeModelMap.containsKey("Toyota"));
        assertEquals(Collections.singletonList("Camry"), makeModelMap.get("Toyota"));

        // Malformed lines should be ignored
        assertFalse(makeModelMap.containsKey("Honda"));
        assertFalse(makeModelMap.containsKey("Ford"));
    }
}