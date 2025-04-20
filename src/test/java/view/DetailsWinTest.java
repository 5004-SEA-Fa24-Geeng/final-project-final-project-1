package view;

import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link DetailsWin}.
 * Ensures UI components are initialized and event listeners are attached correctly.
 */
class DetailsWinTest {

    /** Mocked results window instance. */
    private ResultsWin resultsWin;

    /** Mocked data model instance. */
    private Imodel model;

    /** Mocked car record instance. */
    private CarRecord carRecord;

    /** The window instance under test. */
    private DetailsWin detailsWin;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() {
        resultsWin = mock(ResultsWin.class);
        model = mock(Imodel.class);
        carRecord = mock(CarRecord.class);

        when(carRecord.getTitle()).thenReturn("Mock Car");
        when(carRecord.imageUrl()).thenReturn("mockImage.jpg");
        when(carRecord.price()).thenReturn(25000.00);
        when(carRecord.mileage()).thenReturn(50000);
        when(carRecord.zip()).thenReturn("12345");
        when(carRecord.engineInfo()).thenReturn("V6");
        when(carRecord.bodyType()).thenReturn("Sedan");
        when(carRecord.numOfCylinders()).thenReturn(6);
        when(carRecord.driveType()).thenReturn("AWD");

        detailsWin = new DetailsWin(resultsWin, model, carRecord);
    }

    /**
     * Tests that UI components are initialized correctly.
     */
    @Test
    void testUIComponentsInitialized() {
        assertNotNull(detailsWin);
        assertNotNull(detailsWin.getTitle());
        assertEquals("Car Details", detailsWin.getTitle());

        assertNotNull(detailsWin.getContentPane());
    }

    /**
     * Tests that car details are displayed correctly.
     */
    @Test
    void testCarDetails_DisplayCorrectData() {
        assertEquals("Mock Car", detailsWin.getCarTitleTextArea().getText());
        assertEquals("$25000.0", detailsWin.getCarPriceLabel().getText());
    }
}