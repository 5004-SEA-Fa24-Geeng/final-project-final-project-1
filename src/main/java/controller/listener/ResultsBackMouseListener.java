package controller.listener;

import view.ResultsWin;
import view.SearchWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResultsBackMouseListener implements MouseListener {
    private SearchWin searchWin;
    private ResultsWin resultsWin;

    public ResultsBackMouseListener(SearchWin searchWin, ResultsWin resultsWin) {
        this.searchWin = searchWin;
        this.resultsWin = resultsWin;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        resultsWin.setVisible(false);
        searchWin.setVisible(true);
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
