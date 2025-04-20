package controller;

import utility.Data;
import utility.MakeAndModelData;
import view.SearchWin;

import java.util.HashMap;
import java.util.List;

/**
 * Controls dropdown behavior for selecting car make and model.
 */
public class MakeModelController {

    /** Reference to the search window. */
    private SearchWin searchWin;

    /** Stores car make and model mapping retrieved from external data source. */
    private HashMap<String, List<String>> makeModelData;

    /**
     * Constructs the controller and initializes dropdowns.
     * @param searchWin The search window where dropdowns will be updated.
     */
    public MakeModelController(SearchWin searchWin) {
        this.searchWin = searchWin;
        this.makeModelData = MakeAndModelData.getMakeModelMap(Data.MAKE_MODEL_CSV_PATH);

        // Fill make dropdown with available options
        populateMakeDropdown();

        // Set up event handling for user selections
        setupListeners();
    }

    /**
     * Populates the make dropdown with car makes from data source.
     */
    private void populateMakeDropdown() {
        searchWin.getMakeComboBox().removeAllItems();
        searchWin.getMakeComboBox().addItem("Select"); // Default option

        for (String make : makeModelData.keySet()) {
            searchWin.getMakeComboBox().addItem(make);
        }
    }

    /**
     * Sets up listener to update the model dropdown when a car make is selected.
     */
    private void setupListeners() {
        searchWin.getMakeComboBox().addActionListener(e -> updateModelList());
    }

    /**
     * Updates the model dropdown based on the selected car make.
     */
    public void updateModelList() {
        String selectedMake = searchWin.getSelectedMake();

        List<String> models = makeModelData.getOrDefault(selectedMake, List.of());

        searchWin.updateModelDropdown(models);
    }
}
