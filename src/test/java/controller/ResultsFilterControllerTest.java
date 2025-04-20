package controller;

import model.CarRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import view.ResultsWin;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ResultsFilterController}.
 * Validates filtering and reset functionality.
 */
class ResultsFilterControllerTest {

    /** Mocked results window to prevent UI interactions. */
    private ResultsWin mockResultsWin;

    /** Mocked sorting controller to isolate filtering logic. */
    private ResultsSortController mockResultsSortController;

    /** Instance of ResultsFilterController being tested. */
    private ResultsFilterController resultsFilterController;

    /** Sample car records for filtering tests. */
    private List<CarRecord> mockCarRecords;

    /** JTextFields representing filter inputs. */
    private JTextField minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField;

    /**
     * Sets up mock objects and UI components before each test.
     */
    @BeforeEach
    void setUp() {
        mockResultsWin = mock(ResultsWin.class);
        mockResultsSortController = mock(ResultsSortController.class);

        // Sample car records
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

        resultsFilterController = new ResultsFilterController(mockResultsWin, mockResultsSortController, mockCarRecords);

        // Initialize filter input fields
        minPriceField = new JTextField("2000");
        maxPriceField = new JTextField("30000");
        minYearField = new JTextField("2000");
        maxYearField = new JTextField("2025");
        minMileageField = new JTextField("8000");
        maxMileageField = new JTextField("100000");
    }

    /**
     * Tests filtering by price, year, and mileage range.
     */
    @Test
    void testApplyFilters_ValidInput() {
        resultsFilterController.applyFilters(
                minPriceField, maxPriceField,
                minYearField, maxYearField,
                minMileageField, maxMileageField
        );

        ArgumentCaptor<List<CarRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockResultsSortController, times(1)).updateFilteredCarRecords(captor.capture());

        List<CarRecord> filteredCarRecords = captor.getValue();

        assertEquals(1, filteredCarRecords.size());
    }

    /**
     * Tests handling of invalid filter values.
     */
    @Test
    void testApplyFilters_InvalidInput() {
        // Mock JOptionPane to prevent GUI blocking
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            minPriceField.setText("50000"); // Min price higher than max

            resultsFilterController.applyFilters(
                    minPriceField, maxPriceField,
                    minYearField, maxYearField,
                    minMileageField, maxMileageField
            );

            // Verify that JOptionPane.showMessageDialog() was called
            mockedJOptionPane.verify(() ->
                            JOptionPane.showMessageDialog(any(), eq("Invalid filter values"),
                                    eq("Filter Error"), eq(JOptionPane.ERROR_MESSAGE)),
                    times(1)
            );
        }

        verify(mockResultsSortController, never()).updateFilteredCarRecords(anyList());
        verify(mockResultsWin, never()).updateGridPanel(anyList());
    }

    /**
     * Tests resetting filters to default values.
     */
    @Test
    void testResetFilters() {
        resultsFilterController.resetFilters(
                minPriceField, maxPriceField,
                minYearField, maxYearField,
                minMileageField, maxMileageField
        );

        assertEquals("0", minPriceField.getText());
        assertEquals("50000", maxPriceField.getText());
        assertEquals("1900", minYearField.getText());
        assertEquals("2025", maxYearField.getText());
        assertEquals("0", minMileageField.getText());
        assertEquals("300000", maxMileageField.getText());

        verify(mockResultsSortController, times(1)).updateFilteredCarRecords(mockCarRecords);
        verify(mockResultsWin, times(1)).updateGridPanel(mockCarRecords);
    }
}