package controller.listener;

import controller.ResultsWinController;
import controller.SearchController;
import model.CarRecord;
import model.Imodel;
import view.SearchWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Handles search button clicks to trigger car searches.
 */
public class SearchMouseListener implements MouseListener {

    /** Reference to the search window. */
    private SearchWin searchWin;

    /** Reference to the data model storing car records. */
    private Imodel model;

    /** Controller responsible for executing search queries. */
    private SearchController searchController;

    /**
     * Constructs the listener and initializes dependencies.
     * @param searchWin The search window containing dropdown selections.
     * @param model The data model containing car records.
     */
    public SearchMouseListener(SearchWin searchWin, Imodel model) {
        this.searchWin = searchWin;
        this.model = model;
        this.searchController = new SearchController(model);
    }

    /**
     * Handles the search button click event.
     * Retrieves user selections and displays search results.
     * @param e The mouse event triggering the search.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        String selectedMake = searchWin.getSelectedMake();
        String selectedModel = searchWin.getSelectedModel();

        // Execute search using SearchController based on selected criteria
        List<CarRecord> searchResults = searchController.searchCars(selectedMake, selectedModel);

        // Delegate results display to ResultsWinController
        ResultsWinController resultsWinController = new ResultsWinController(searchWin, model, searchResults);
        resultsWinController.showResults();
    }

    /** Handles mouse press event. */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /** Handles mouse release event. */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /** Handles mouse enter event. */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /** Handles mouse exit event. */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
