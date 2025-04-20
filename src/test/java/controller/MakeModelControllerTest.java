package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import utility.Data;
import utility.MakeAndModelData;
import view.SearchWin;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link MakeModelController}.
 * Verifies dropdown behavior for selecting car make and model.
 */
class MakeModelControllerTest {

    /** Mocked SearchWin instance to isolate GUI logic. */
    private SearchWin mockSearchWin;

    /** Instance of MakeModelController being tested. */
    private MakeModelController makeModelController;

    /** Simulated car make and model data. */
    private HashMap<String, List<String>> mockMakeModelData;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockSearchWin = Mockito.mock(SearchWin.class);

        // Mock dropdown components
        JComboBox<String> makeComboBox = new JComboBox<>();
        JComboBox<String> modelComboBox = new JComboBox<>();
        when(mockSearchWin.getMakeComboBox()).thenReturn(makeComboBox);
        when(mockSearchWin.getModelComboBox()).thenReturn(modelComboBox);

        // Simulated data
        mockMakeModelData = new HashMap<>(Map.of(
                "Toyota", List.of("Camry", "Corolla"),
                "Honda", List.of("Civic", "Accord")
        ));

        // Ensure updateModelDropdown is properly called in the mock
        doAnswer(invocation -> {
            List<String> models = invocation.getArgument(0);

            modelComboBox.removeAllItems();
            modelComboBox.addItem("Select"); // Default option

            for (String model : models) {
                modelComboBox.addItem(model);
            }

            modelComboBox.setSelectedItem("Select");

            modelComboBox.setEnabled(!models.isEmpty());

            return null;
        }).when(mockSearchWin).updateModelDropdown(anyList());

        // Use try-with-resources to automatically close the static mock
        try (MockedStatic<MakeAndModelData> mockedStatic = mockStatic(MakeAndModelData.class)) {
            mockedStatic.when(() -> MakeAndModelData.getMakeModelMap(Data.MAKE_MODEL_CSV_PATH))
                    .thenReturn(mockMakeModelData);

            // Initialize the controller while the mock is active
            makeModelController = new MakeModelController(mockSearchWin);
        }
    }

    /**
     * Tests if make dropdown is populated correctly.
     */
    @Test
    void testPopulateMakeDropdown() {
        JComboBox<String> makeComboBox = mockSearchWin.getMakeComboBox();

        assertEquals(3, makeComboBox.getItemCount());
        assertEquals("Select", makeComboBox.getItemAt(0));
    }

    /**
     * Tests if selecting a make updates the model dropdown correctly.
     */
    @Test
    void testUpdateModelDropdown() {
        when(mockSearchWin.getSelectedMake()).thenReturn("Toyota");

        makeModelController.updateModelList();

        JComboBox<String> modelComboBox = mockSearchWin.getModelComboBox();

        assertEquals(3, modelComboBox.getItemCount());
        assertTrue(modelComboBox.isEnabled());
    }

    /**
     * Tests if an invalid make selection results in an empty model dropdown.
     */
    @Test
    void testUpdateModelDropdown_InvalidSelection() {
        JComboBox<String> makeComboBox = mockSearchWin.getMakeComboBox();
        makeComboBox.setSelectedItem("InvalidMake");

        makeModelController.updateModelList();

        JComboBox<String> modelComboBox = mockSearchWin.getModelComboBox();
        assertEquals(1, modelComboBox.getItemCount());
        assertFalse(modelComboBox.isEnabled());
    }
}