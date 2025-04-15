package controller;

import model.CarRecord;
import model.Imodel;
import view.ResultsWin;
import view.SearchWin;

import java.util.List;

/**
 * Manages the transition from SearchWin to ResultsWin.
 */
public class ResultsWinController {

    /**
     * Reference to the search window.
     */
    private SearchWin searchWin;

    /**
     * Reference to the data model storing car records.
     */
    private Imodel model;

    /**
     * List of car search results.
     */
    private List<CarRecord> searchResults;

    /**
     * Constructs the controller.
     *
     * @param searchWin     The search window to be hidden when results are displayed.
     * @param model         The data model containing car records.
     * @param searchResults The list of matching car records from the search.
     */
    public ResultsWinController(SearchWin searchWin, Imodel model, List<CarRecord> searchResults) {
        this.searchWin = searchWin;
        this.model = model;
        this.searchResults = searchResults;
    }

    /**
     * Creates and displays the results window with search data.
     */
    public void showResults() {
        ResultsWin resultsWin = new ResultsWin(searchWin, model, searchResults);
        resultsWin.setVisible(true);
        searchWin.setVisible(false);
    }
}
