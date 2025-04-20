package controller.listener;

import controller.DetailsBackController;
import view.DetailsWin;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for handling the mouse click event to navigate back from the details window
 * to the results window.
 */
public class DetailsBackMouseListener implements MouseListener {

    /** Controller responsible for handling the navigation logic. */
    private DetailsBackController detailsBackController;

    /**
     * Constructs a DetailsBackMouseListener with the given results and details windows.
     * @param resultsWin The results window instance.
     * @param detailsWin The details window instance.
     */
    public DetailsBackMouseListener(ResultsWin resultsWin, DetailsWin detailsWin) {
        this.detailsBackController = new DetailsBackController(resultsWin, detailsWin);
    }

    /**
     * Handles the mouse click event to trigger navigation back to results view.
     * @param e The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        detailsBackController.goBackToResults();
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
