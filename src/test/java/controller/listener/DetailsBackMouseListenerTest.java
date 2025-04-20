package controller.listener;

import controller.DetailsBackController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DetailsWin;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link DetailsBackMouseListener}.
 * Verifies that clicking triggers navigation back to the results window.
 */
class DetailsBackMouseListenerTest {

    /** The listener instance under test. */
    private DetailsBackMouseListener detailsBackMouseListener;

    /** Mocked controller responsible for navigation. */
    private DetailsBackController detailsBackController;

    /** Mocked results window instance. */
    private ResultsWin resultsWin;

    /** Mocked details window instance. */
    private DetailsWin detailsWin;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        resultsWin = mock(ResultsWin.class);
        detailsWin = mock(DetailsWin.class);
        detailsBackController = mock(DetailsBackController.class);

        // Initialize listener instance (which normally creates its own controller)
        detailsBackMouseListener = new DetailsBackMouseListener(resultsWin, detailsWin);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = DetailsBackMouseListener.class.getDeclaredField("detailsBackController");
        controllerField.setAccessible(true);
        controllerField.set(detailsBackMouseListener, detailsBackController);
    }

    /**
     * Tests that a mouse click event correctly triggers navigation back to the results window.
     */
    @Test
    void testMouseClicked_NavigatesBackToResults() {
        MouseEvent event = mock(MouseEvent.class);

        // Simulate mouse click event
        detailsBackMouseListener.mouseClicked(event);

        // Verify that the navigation method was invoked once
        verify(detailsBackController, times(1)).goBackToResults();
    }
}