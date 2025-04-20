package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link SearchWin}
 * This class verifies the correct behavior of the SearchWin GUI components.
 */
class SearchWinTest {
    /** Instance of SearchWin for testing. */
    private SearchWin searchWin;

    /**
     * Initializes the test environment before each test execution.
     */
    @BeforeEach
    void setUp() {
        searchWin = new SearchWin();
    }

    /**
     * Ensures all UI components exist after initialization.
     */
    @Test
    void testComponentsExist() {
        assertNotNull(searchWin.getMakeComboBox());
        assertNotNull(searchWin.getModelComboBox());
        assertNotNull(searchWin.getSearchButton());
    }

    /**
     * Tests the update of the model dropdown when provided with car models.
     */
    @Test
    void testUpdateModelDropdown() {
        searchWin.updateModelDropdown(Arrays.asList("Civic", "Accord"));

        JComboBox<String> modelDropdown = searchWin.getModelComboBox();
        assertEquals(3, modelDropdown.getItemCount());
        assertTrue(modelDropdown.isEnabled());
    }

    /**
     * Tests the behavior of the model dropdown when given an empty list of models.
     */
    @Test
    void testUpdateModelDropdown_EmptyList() {
        searchWin.updateModelDropdown(Arrays.asList());

        JComboBox<String> modelDropdown = searchWin.getModelComboBox();
        assertEquals(1, modelDropdown.getItemCount());
        assertEquals("Select", modelDropdown.getItemAt(0));
        assertFalse(modelDropdown.isEnabled());
    }

    /**
     * Tests manual selection of a make in the combo box.
     */
    @Test
    void testMakeSelection() {
        searchWin.getMakeComboBox().addItem("Toyota");
        searchWin.getMakeComboBox().setSelectedItem("Toyota");

        assertEquals("Toyota", searchWin.getSelectedMake());
    }

    /**
     * Tests default selection in the make dropdown.
     */
    @Test
    void testMakeSelection_DefaultSelection() {
        assertEquals("Select", searchWin.getSelectedMake());
    }

    /**
     * Tests manual selection of a model in the combo box.
     */
    @Test
    void testModelSelection() {
        searchWin.getModelComboBox().addItem("Camry");
        searchWin.getModelComboBox().setSelectedItem("Camry");

        assertEquals("Camry", searchWin.getSelectedModel());
    }

    /**
     * Tests default selection in the model dropdown.
     */
    @Test
    void testModelSelection_DefaultSelection() {
        searchWin.updateModelDropdown(Arrays.asList());

        searchWin.getModelComboBox().setSelectedItem("Select");

        assertEquals("Select", searchWin.getSelectedModel());
    }

    /**
     * Verifies the search button properties.
     */
    @Test
    void testSearchButtonClick() {
        JButton searchButton = searchWin.getSearchButton();

        assertNotNull(searchButton);
        assertEquals("Search", searchButton.getText());
    }

    /**
     * Tests whether UI components maintain correct dimensions.
     */
    @Test
    void testComponentDimensions() {
        assertEquals(new Dimension(320, 40), searchWin.getMakeComboBox().getPreferredSize());
        assertEquals(new Dimension(320, 40), searchWin.getModelComboBox().getPreferredSize());
        assertEquals(new Dimension(160, 40), searchWin.getSearchButton().getPreferredSize());
    }
}