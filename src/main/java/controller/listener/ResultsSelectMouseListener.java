package controller.listener;

import controller.DetailsWinController;
import model.CarRecord;
import model.Imodel;
import view.DetailsWin;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResultsSelectMouseListener implements MouseListener {
    private DetailsWinController detailsWinController;

    public ResultsSelectMouseListener(ResultsWin resultsWin, Imodel model, CarRecord carRecord) {
        this.detailsWinController = new DetailsWinController(resultsWin, model, carRecord);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        detailsWinController.showDetails();
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
