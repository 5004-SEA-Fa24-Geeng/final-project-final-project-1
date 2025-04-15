package controller.listener;

import controller.DetailsBackController;
import view.DetailsWin;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DetailsBackMouseListener implements MouseListener {
    private DetailsBackController detailsBackController;

    public DetailsBackMouseListener(ResultsWin resultsWin, DetailsWin detailsWin) {
        this.detailsBackController = new DetailsBackController(resultsWin, detailsWin);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        detailsBackController.goBackToResults();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
