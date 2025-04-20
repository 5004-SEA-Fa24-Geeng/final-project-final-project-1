package controller.listener;

import controller.DetailsWinController;
import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ResultsSelectMouseListener}.
 * Ensures that clicking triggers the display of the details view.
 */
class ResultsSelectMouseListenerTest {

    /** The listener instance under test. */
    private ResultsSelectMouseListener resultsSelectMouseListener;

    /** Mocked controller responsible for managing the display of the details view. */
    private DetailsWinController detailsWinController;

    /** Mocked results window instance. */
    private ResultsWin resultsWin;

    /** Mocked data model instance. */
    private Imodel model;

    /** Mocked car record instance representing the item to be displayed. */
    private CarRecord carRecord;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        resultsWin = mock(ResultsWin.class);
        model = mock(Imodel.class);
        carRecord = mock(CarRecord.class);
        detailsWinController = mock(DetailsWinController.class);

        // Initialize listener instance (which normally creates its own controller)
        resultsSelectMouseListener = new ResultsSelectMouseListener(resultsWin, model, carRecord);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = ResultsSelectMouseListener.class.getDeclaredField("detailsWinController");
        controllerField.setAccessible(true);
        controllerField.set(resultsSelectMouseListener, detailsWinController);
    }

    /**
     * Tests that a mouse click event correctly triggers the display of the details view.
     */
    @Test
    void testMouseClicked_ShowsDetails() {
        MouseEvent event = mock(MouseEvent.class);

        // Simulate mouse click event
        resultsSelectMouseListener.mouseClicked(event);

        // Verify that the showDetails method was invoked once
        verify(detailsWinController, times(1)).showDetails();
    }
}