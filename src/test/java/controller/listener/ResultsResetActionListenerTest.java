package controller.listener;

import controller.ResultsFilterController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ResultsResetActionListener}.
 * Ensures that clicking triggers the reset of all filter fields.
 */
class ResultsResetActionListenerTest {

    /** The listener instance under test. */
    private ResultsResetActionListener resultsResetActionListener;

    /** Mocked controller responsible for managing filter reset logic. */
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
        resultsResetActionListener = new ResultsResetActionListener(
                resultsFilterController, minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField
        );

        // Use reflection to inject the mock controller into the listener
        Field controllerField = ResultsResetActionListener.class.getDeclaredField("resultsFilterController");
        controllerField.setAccessible(true);
        controllerField.set(resultsResetActionListener, resultsFilterController);
    }

    /**
     * Tests that an action event correctly triggers the reset of all filter fields.
     */
    @Test
    void testActionPerformed_ResetsFilters() {
        ActionEvent event = mock(ActionEvent.class);

        // Simulate action event
        resultsResetActionListener.actionPerformed(event);

        // Verify that the reset method was invoked once with correct fields
        verify(resultsFilterController, times(1)).resetFilters(
                minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField
        );
    }
}