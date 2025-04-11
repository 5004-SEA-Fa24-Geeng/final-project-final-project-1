package controller.listener;

import view.DetailsWin;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DetailsBackMouseListener implements MouseListener {
    private ResultsWin resultsWin;
    private DetailsWin detailsWin;

    public DetailsBackMouseListener(ResultsWin resultsWin, DetailsWin detailsWin) {
        this.resultsWin = resultsWin;
        this.detailsWin = detailsWin;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        resultsWin.setVisible(true);
        detailsWin.setVisible(false);
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
