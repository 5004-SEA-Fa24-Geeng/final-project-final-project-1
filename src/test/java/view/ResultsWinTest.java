package view;

import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit test for {@link ResultsWin}
 * Verifies search results window behavior.
 */
class ResultsWinTest {

    /** Mocked search window to isolate UI interactions. */
    private SearchWin mockSearchWin;

    /** Mocked data model to prevent dependencies. */
    private Imodel mockModel;

    /** Instance of ResultsWin being tested. */
    private ResultsWin resultsWin;

    /** Simulated car records for testing. */
    private List<CarRecord> mockCarRecords;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockSearchWin = mock(SearchWin.class);
        mockModel = mock(Imodel.class);

        // Sample car record data
        mockCarRecords = List.of(
                new CarRecord("1", 12000.00, "94043", 10000, "Ford", "Mustang",
                        2024, "Unknown", "2.4L", "Sedan", 4,
                        "AWD", "data/images/mustang.jpg"),
                new CarRecord("2", 1000.00, "98052", 15000, "Toyota", "Camry",
                        2022, "Limited", "2.4L Turbo", "Sedan", 4,
                        "AWD", "data/images/camry.jpg"),
                new CarRecord("3", 28000.00, "98102", 200000, "Toyota", "Corolla",
                        2000, "Limited", "2.4L", "Sedan", 4,
                        "AWD", "data/images/corolla.jpg")
        );

        // Instantiate ResultsWin with mock dependencies
        resultsWin = new ResultsWin(mockSearchWin, mockModel, mockCarRecords);
    }

    /**
     * Tests that the results window initializes correctly with controllers.
     */
    @Test
    void testResultsWinInitialization() {
        assertNotNull(resultsWin);
        assertNotNull(resultsWin.getBackButton());
    }

    /**
     * Tests that car listings are properly displayed in the grid panel.
     */
    @Test
    void testCreateGridPanel() {
        JScrollPane gridScrollPane = resultsWin.createGridPanel(mockCarRecords);
        assertNotNull(gridScrollPane);
    }

    /**
     * Tests updating the grid panel with filtered/sorted car records.
     */
    @Test
    void testUpdateGridPanel() {
        List<CarRecord> filteredRecords = List.of(
                new CarRecord("2", 1000.00, "98052", 15000, "Toyota", "Camry",
                        2022, "Limited", "2.4L Turbo", "Sedan", 4,
                        "AWD", "data/images/camry.jpg"),
                new CarRecord("3", 28000.00, "98102", 200000, "Toyota", "Corolla",
                        2000, "Limited", "2.4L", "Sedan", 4,
                        "AWD", "data/images/corolla.jpg")
        );

        resultsWin.updateGridPanel(filteredRecords);

        assertEquals(filteredRecords.size(), resultsWin.getGridPanel().getComponentCount());
    }
}