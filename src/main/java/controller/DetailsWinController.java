package controller;

import model.CarRecord;
import model.Imodel;
import view.DetailsWin;
import view.ResultsWin;

/**
 * Controls the transition from {@link ResultsWin} to {@link DetailsWin}.
 * Manages the display of detailed car information when a user selects a car from the search results.
 */
public class DetailsWinController {

    /** Reference to the results window that contains search results. */
    private ResultsWin resultsWin;

    /** Reference to the data model containing car records and application logic. */
    private Imodel model;

    /** The car record selected for detailed viewing. */
    private CarRecord carRecord;

    /**
     * Constructs the controller that manages the transition from the results window to the details window.
     *
     * @param resultsWin The results window that will be hidden when details are displayed.
     * @param model The data model containing car records and wishlist functionality.
     * @param carRecord The car record selected by the user for detailed viewing.
     */
    public DetailsWinController(ResultsWin resultsWin, Imodel model, CarRecord carRecord) {
        this.resultsWin = resultsWin;
        this.model = model;
        this.carRecord = carRecord;
    }

    /**
     * Creates the {@link DetailsWin} instance for displaying car details.
     * This method is extracted to facilitate easier testing via spies.
     *
     * @return A new instance of {@link DetailsWin}.
     */
    protected DetailsWin createDetailsWin() {
        return new DetailsWin(resultsWin, model, carRecord);
    }

    /**
     * Displays the detailed view of the selected car.
     * Hides the results window and initializes the {@link DetailsWin} window with the selected car's details.
     */
    public void showDetails() {
        resultsWin.setVisible(false);
        DetailsWin detailsWin = createDetailsWin();
        detailsWin.setVisible(true);
    }
}