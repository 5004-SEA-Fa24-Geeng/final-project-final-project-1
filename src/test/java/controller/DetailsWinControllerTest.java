package controller;

import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DetailsWin;
import view.ResultsWin;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link DetailsWinController}.
 * Ensures transition from results window to details window works correctly.
 */
class DetailsWinControllerTest {

    /** Mocked instance of the results window. */
    private ResultsWin resultsWin;

    /** Mocked instance of the data model. */
    private Imodel model;

    /** Mocked car record. */
    private CarRecord carRecord;

    /** The controller under test. */
    private DetailsWinController detailsWinController;

    /** Mocked instance of the details window. */
    private DetailsWin mockDetailsWin;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() {
        resultsWin = mock(ResultsWin.class);
        model = mock(Imodel.class);
        carRecord = mock(CarRecord.class);
        mockDetailsWin = mock(DetailsWin.class); // Mock DetailsWin instance

        // Spy on the controller to override `createDetailsWin()`
        detailsWinController = spy(new DetailsWinController(resultsWin, model, carRecord));

        // Override `createDetailsWin()` to return the mocked instance
        doReturn(mockDetailsWin).when(detailsWinController).createDetailsWin();
    }

    /**
     * Tests that showDetails() hides the results window and displays the details window.
     */
    @Test
    void testShowDetails_HidesResultsWinAndDisplaysDetailsWin() {
        detailsWinController.showDetails();

        // Verify ResultsWin is hidden
        verify(resultsWin, times(1)).setVisible(false);

        // Verify DetailsWin is displayed
        verify(mockDetailsWin, times(1)).setVisible(true);
    }
}