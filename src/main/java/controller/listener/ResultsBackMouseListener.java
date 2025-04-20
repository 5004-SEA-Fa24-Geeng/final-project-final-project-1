package controller.listener;

import controller.ResultsBackController;
import view.ResultsWin;
import view.SearchWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for handling the mouse click event to navigate back from the results
 * window to the search window.
 */
public class ResultsBackMouseListener implements MouseListener {

    /** Controller responsible for managing navigation back to the search view. */
    private ResultsBackController resultsBackController;

    /**
     * Constructs a ResultsBackMouseListener with the given search and results windows.
     * @param searchWin The search window instance.
     * @param resultsWin The results window instance.
     */
    public ResultsBackMouseListener(SearchWin searchWin, ResultsWin resultsWin) {
        this.resultsBackController = new ResultsBackController(searchWin, resultsWin);
    }

    /**
     * Handles the mouse click event to trigger navigation back to the search view.
     * @param e The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        resultsBackController.goBackToSearch();
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
