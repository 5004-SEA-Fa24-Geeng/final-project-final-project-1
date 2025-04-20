package controller;

import view.ResultsWin;
import view.SearchWin;

/**
 * Handles navigation from the results window back to the search window.
 */
public class ResultsBackController {

    /** Reference to the search window. */
    private SearchWin searchWin;

    /** Reference to the results window. */
    private ResultsWin resultsWin;

    /**
     * Constructs the controller for managing back navigation.
     * @param searchWin The search window to be shown when navigating back.
     * @param resultsWin The results window to be hidden when going back.
     */
    public ResultsBackController(SearchWin searchWin, ResultsWin resultsWin) {
        this.searchWin = searchWin;
        this.resultsWin = resultsWin;
    }

    /**
     * Navigates back to the search window by hiding results and displaying search.
     */
    public void goBackToSearch() {
        resultsWin.setVisible(false);
        searchWin.setVisible(true);
    }
}
