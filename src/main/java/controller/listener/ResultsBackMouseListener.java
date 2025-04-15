package controller.listener;

import controller.ResultsBackController;
import view.ResultsWin;
import view.SearchWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResultsBackMouseListener implements MouseListener {
    private ResultsBackController resultsBackController;

    public ResultsBackMouseListener(SearchWin searchWin, ResultsWin resultsWin) {
        this.resultsBackController = new ResultsBackController(searchWin, resultsWin);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        resultsBackController.goBackToSearch();
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
