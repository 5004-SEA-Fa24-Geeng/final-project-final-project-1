package controller.listener;

import controller.ResultsFilterController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ResultsFilterActionListener}.
 * Ensures that clicking triggers the filter application based on user-defined criteria.
 */
class ResultsFilterActionListenerTest {

    /** The listener instance under test. */
    private ResultsFilterActionListener resultsFilterActionListener;

    /** Mocked controller responsible for managing filter application logic. */
    private ResultsFilterController resultsFilterController;

    /** Mocked filter fields. */
    private JTextField minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        resultsFilterController = mock(ResultsFilterController.class);
        minPriceField = mock(JTextField.class);
        maxPriceField = mock(JTextField.class);
        minYearField = mock(JTextField.class);
        maxYearField = mock(JTextField.class);
        minMileageField = mock(JTextField.class);
        maxMileageField = mock(JTextField.class);

        // Initialize listener instance (which normally creates its own controller)
        resultsFilterActionListener = new ResultsFilterActionListener(
                resultsFilterController, minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField
        );

        // Use reflection to inject the mock controller into the listener
        Field controllerField = ResultsFilterActionListener.class.getDeclaredField("resultsFilterController");
        controllerField.setAccessible(true);
        controllerField.set(resultsFilterActionListener, resultsFilterController);
    }

    /**
     * Tests that an action event correctly triggers filter application.
     */
    @Test
    void testActionPerformed_AppliesFilters() {
        ActionEvent event = mock(ActionEvent.class);

        // Simulate action event
        resultsFilterActionListener.actionPerformed(event);

        // Verify that the filter application method was invoked once with correct fields
        verify(resultsFilterController, times(1)).applyFilters(
                minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField
        );
    }
}