package controller;

import view.DetailsWin;
import view.ResultsWin;

/**
 * Controls the transition from {@link DetailsWin} back to {@link ResultsWin}.
 * Manages visibility settings to navigate between the results window and details window.
 */
public class DetailsBackController {

    /** Reference to the results window that displays search results. */
    private ResultsWin resultsWin;

    /** Reference to the details window displaying a selected car's information. */
    private DetailsWin detailsWin;

    /**
     * Constructs the controller responsible for handling navigation back to the results window.
     *
     * @param resultsWin The results window to be made visible when returning from the details view.
     * @param detailsWin The details window to be hidden when transitioning back to the results view.
     */
    public DetailsBackController(ResultsWin resultsWin, DetailsWin detailsWin) {
        this.resultsWin = resultsWin;
        this.detailsWin = detailsWin;
    }

    /**
     * Navigates from the details window back to the results window.
     * Hides the details window and makes the results window visible.
     */
    public void goBackToResults() {
        resultsWin.setVisible(true);
        detailsWin.setVisible(false);
    }
}
