package view;

import controller.MakeModelController;
import utility.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents the search window where users can select car make and model.
 * Provides a GUI for filtering car listings based on user selection.
 */
public class SearchWin extends JFrame {

    /** Background label for the search window UI. */
    private JLabel searchBgLabel = new JLabel(Data.START_BG_IMG_ICON);

    /** Dropdown for selecting car make. */
    private JComboBox<String> makeComboBox;

    /** Dropdown for selecting car model. */
    private JComboBox<String> modelComboBox;

    /** Search button to initiate search based on selected criteria. */
    private JButton searchButton;

    /** Controller responsible for handling dropdown updates. */
    private MakeModelController makeModelController;

    /**
     * Constructs the Search Window and initializes UI components.
     */
    public SearchWin() {
        setTitle("Search");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // Set background label and layout
        searchBgLabel.setBounds(0, 0, 900, 700);
        searchBgLabel.setLayout(new BorderLayout());
        add(searchBgLabel);

        // Content panel to arrange dropdowns and button horizontally
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setOpaque(false);

        // Make dropdown setup
        makeComboBox = new JComboBox<>();
        makeComboBox.setPreferredSize(new Dimension(320, 40));
        makeComboBox.setMaximumSize(new Dimension(320, 40));
        makeComboBox.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 0));
        makeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(makeComboBox);

        // Model dropdown setup, disabled initially
        modelComboBox = new JComboBox<>();
        modelComboBox.setEnabled(false);
        modelComboBox.setPreferredSize(new Dimension(320, 40));
        modelComboBox.setMaximumSize(new Dimension(320, 40));
        modelComboBox.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 60));
        modelComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(modelComboBox);

        // Search button setup
        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(160, 40));
        searchButton.setMaximumSize(new Dimension(160, 40));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(searchButton);

        // Add contentPanel to background label
        searchBgLabel.add(contentPanel, BorderLayout.CENTER);

        // Initialize the controller after UI components are created
        makeModelController = new MakeModelController(this);
    }

    /**
     * Updates the model dropdown with a new list of car models.
     * @param models The list of car models to display.
     */
    public void updateModelDropdown(List<String> models) {
        System.out.println("test1");
        modelComboBox.removeAllItems();
        modelComboBox.addItem("Select"); // Default option

        for (String model : models) {
            modelComboBox.addItem(model);
        }

        modelComboBox.setSelectedItem("Select");

        modelComboBox.setEnabled(!models.isEmpty()); // Enable dropdown if models exist
    }

    /**
     * Gets the selected car make from the dropdown.
     * @return The selected make as a string.
     */
    public String getSelectedMake() {
        return (String) makeComboBox.getSelectedItem();
    }

    /**
     * Gets the selected car model from the dropdown.
     * @return The selected model as a string.
     */
    public String getSelectedModel() {
        return (String) modelComboBox.getSelectedItem();
    }

    /**
     * Gets the Make ComboBox instance.
     * @return The JComboBox for selecting a car make.
     */
    public JComboBox<String> getMakeComboBox() {
        return makeComboBox;
    }

    /**
     * Gets the Model ComboBox instance.
     * @return The JComboBox for selecting a car model.
     */
    public JComboBox<String> getModelComboBox() {
        return modelComboBox;
    }

    /**
     * Gets the search button instance.
     * @return The JButton used for initiating the search.
     */
    public JButton getSearchButton() {
        return searchButton;
    }
}
