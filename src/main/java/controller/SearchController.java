package controller;

import model.CarRecord;
import model.Imodel;

import java.util.List;

/**
 * Controls search functionality for filtering car records.
 */
public class SearchController {

    /** Reference to the data model containing car records. */
    private Imodel model;

    /**
     * Constructs the search controller.
     * @param model The model providing car records.
     */
    public SearchController(Imodel model) {
        this.model = model;
    }

    /**
     * Performs a search based on selected make and model.
     * Returns all cars if no make is selected, filters by make if only make is chosen,
     * and filters by both make and model when both are provided.
     * @param selectedMake The car make selected by the user.
     * @param selectedModel The car model selected by the user.
     * @return A list of matching car records.
     */
    public List<CarRecord> searchCars(String selectedMake, String selectedModel) {
        if ("Select".equals(selectedMake)) {
            return model.getAllCars();
        } else if ("Select".equals(selectedModel)) {
            return model.getCarsByMake(selectedMake);
        } else {
            return model.getCarsByMakeAndModel(selectedMake, selectedModel);
        }
    }
}
