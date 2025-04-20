package controller;

import model.CarRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import view.ResultsWin;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ResultsSortController}.
 * Ensures sorting logic functions correctly.
 */
class ResultsSortControllerTest {

    /** Mocked results window to prevent actual UI interaction. */
    private ResultsWin mockResultsWin;

    /** Instance of ResultsSortController being tested. */
    private ResultsSortController resultsSortController;

    /** Sample car records for sorting tests. */
    private List<CarRecord> mockCarRecords;

    /**
     * Sets up mock objects and test data before each test.
     */
    @BeforeEach
    void setUp() {
        mockResultsWin = mock(ResultsWin.class);

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

        resultsSortController = new ResultsSortController(mockResultsWin, mockCarRecords);
    }

    /**
     * Tests sorting by price from low to high.
     */
    @Test
    void testSortByPriceLowToHigh() {
        resultsSortController.applySort("Price (Low to High)");

        // Capture sorted records
        ArgumentCaptor<List<CarRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockResultsWin, times(1)).updateGridPanel(captor.capture());

        List<CarRecord> sortedRecords = captor.getValue();

        // Validate sorting order
        assertEquals(mockCarRecords.get(1), sortedRecords.get(0));
        assertEquals(mockCarRecords.get(0), sortedRecords.get(1));
        assertEquals(mockCarRecords.get(2), sortedRecords.get(2));
    }

    /**
     * Tests sorting by price from high to low.
     */
    @Test
    void testSortByPriceHighToLow() {
        resultsSortController.applySort("Price (High to Low)");

        // Capture sorted records
        ArgumentCaptor<List<CarRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockResultsWin, times(1)).updateGridPanel(captor.capture());

        List<CarRecord> sortedRecords = captor.getValue();

        // Validate sorting order
        assertEquals(mockCarRecords.get(2), sortedRecords.get(0));
        assertEquals(mockCarRecords.get(0), sortedRecords.get(1));
        assertEquals(mockCarRecords.get(1), sortedRecords.get(2));
    }

    /**
     * Tests sorting by year from newest to oldest.
     */
    @Test
    void testSortByYearNewestFirst() {
        resultsSortController.applySort("Year (Newest First)");

        // Capture sorted records
        ArgumentCaptor<List<CarRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockResultsWin, times(1)).updateGridPanel(captor.capture());

        List<CarRecord> sortedRecords = captor.getValue();

        // Validate sorting order
        assertEquals(mockCarRecords.get(0), sortedRecords.get(0));
        assertEquals(mockCarRecords.get(1), sortedRecords.get(1));
        assertEquals(mockCarRecords.get(2), sortedRecords.get(2));
    }

    /**
     * Tests sorting by year from oldest to newest.
     */
    @Test
    void testSortByYearOldestFirst() {
        resultsSortController.applySort("Year (Oldest First)");

        // Capture sorted records
        ArgumentCaptor<List<CarRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockResultsWin, times(1)).updateGridPanel(captor.capture());

        List<CarRecord> sortedRecords = captor.getValue();

        // Validate sorting order
        assertEquals(mockCarRecords.get(2), sortedRecords.get(0));
        assertEquals(mockCarRecords.get(1), sortedRecords.get(1));
        assertEquals(mockCarRecords.get(0), sortedRecords.get(2));
    }

    /**
     * Tests sorting by mileage from low to high.
     */
    @Test
    void testSortByMileageLowToHigh() {
        resultsSortController.applySort("Mileage (Low to High)");

        // Capture sorted records
        ArgumentCaptor<List<CarRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockResultsWin, times(1)).updateGridPanel(captor.capture());

        List<CarRecord> sortedRecords = captor.getValue();

        // Validate sorting order
        assertEquals(mockCarRecords.get(0), sortedRecords.get(0));
        assertEquals(mockCarRecords.get(1), sortedRecords.get(1));
        assertEquals(mockCarRecords.get(2), sortedRecords.get(2));
    }

    /**
     * Tests sorting by mileage from high to low.
     */
    @Test
    void testSortByMileageHighToLow() {
        resultsSortController.applySort("Mileage (High to Low)");

        // Capture sorted records
        ArgumentCaptor<List<CarRecord>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockResultsWin, times(1)).updateGridPanel(captor.capture());

        List<CarRecord> sortedRecords = captor.getValue();

        // Validate sorting order
        assertEquals(mockCarRecords.get(2), sortedRecords.get(0));
        assertEquals(mockCarRecords.get(1), sortedRecords.get(1));
        assertEquals(mockCarRecords.get(0), sortedRecords.get(2));
    }
}