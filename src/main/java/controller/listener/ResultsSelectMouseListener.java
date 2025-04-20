package controller.listener;

import controller.DetailsWinController;
import model.CarRecord;
import model.Imodel;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for handling the mouse click event to display the details view from
 * the results window.
 */
public class ResultsSelectMouseListener implements MouseListener {

    /** Controller responsible for managing the display of the details view. */
    private DetailsWinController detailsWinController;

    /**
     * Constructs a ResultsSelectMouseListener with the given results window, model, and car record,.
     * @param resultsWin The results window instance.
     * @param model The data model instance.
     * @param carRecord The car record to be displayed in the details view.
     */
    public ResultsSelectMouseListener(ResultsWin resultsWin, Imodel model, CarRecord carRecord) {
        this.detailsWinController = new DetailsWinController(resultsWin, model, carRecord);
    }

    /**
     * Handles the mouse click event to trigger the display of the details view.
     * @param e The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        detailsWinController.showDetails();
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
