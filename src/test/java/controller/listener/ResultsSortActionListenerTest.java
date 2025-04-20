package controller.listener;

import controller.ResultsSortController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ResultsSortActionListener}.
 * Ensures that clicking triggers the sorting operation based on user selection.
 */
class ResultsSortActionListenerTest {

    /** The listener instance under test. */
    private ResultsSortActionListener resultsSortActionListener;

    /** Mocked controller responsible for managing sorting logic. */
    private ResultsSortController resultsSortController;

    /** Mocked sorting combo box. */
    private JComboBox<String> sortComboBox;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        resultsSortController = mock(ResultsSortController.class);
        sortComboBox = mock(JComboBox.class);

        // Initialize listener instance (which normally creates its own controller)
        resultsSortActionListener = new ResultsSortActionListener(resultsSortController);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = ResultsSortActionListener.class.getDeclaredField("resultsSortController");
        controllerField.setAccessible(true);
        controllerField.set(resultsSortActionListener, resultsSortController);
    }

    /**
     * Tests that an action event correctly triggers the sorting operation.
     */
    @Test
    void testActionPerformed_AppliesSorting() {
        ActionEvent event = mock(ActionEvent.class);

        // Mock sorting combo box selection
        when(event.getSource()).thenReturn(sortComboBox);
        when(sortComboBox.getSelectedItem()).thenReturn("Price: Low to High");

        // Simulate action event
        resultsSortActionListener.actionPerformed(event);

        // Verify that the sorting method was invoked once with the selected sorting option
        verify(resultsSortController, times(1)).applySort("Price: Low to High");
    }
}