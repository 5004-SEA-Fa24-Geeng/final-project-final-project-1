package controller.listener;

import controller.ResultsBackController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ResultsWin;
import view.SearchWin;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ResultsBackMouseListener}.
 * Verifies that clicking triggers navigation back to the search window.
 */
class ResultsBackMouseListenerTest {

    /** The listener instance under test. */
    private ResultsBackMouseListener resultsBackMouseListener;

    /** Mocked controller responsible for navigation. */
    private ResultsBackController resultsBackController;

    /** Mocked search window instance. */
    private SearchWin searchWin;

    /** Mocked results window instance. */
    private ResultsWin resultsWin;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        searchWin = mock(SearchWin.class);
        resultsWin = mock(ResultsWin.class);
        resultsBackController = mock(ResultsBackController.class);

        // Initialize listener instance (which normally creates its own controller)
        resultsBackMouseListener = new ResultsBackMouseListener(searchWin, resultsWin);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = ResultsBackMouseListener.class.getDeclaredField("resultsBackController");
        controllerField.setAccessible(true);
        controllerField.set(resultsBackMouseListener, resultsBackController);
    }

    /**
     * Tests that a mouse click event correctly triggers navigation back to the search window.
     */
    @Test
    void testMouseClicked_NavigatesBackToSearch() {
        MouseEvent event = mock(MouseEvent.class);

        // Simulate mouse click event
        resultsBackMouseListener.mouseClicked(event);

        // Verify that the navigation method was invoked once
        verify(resultsBackController, times(1)).goBackToSearch();
    }
}