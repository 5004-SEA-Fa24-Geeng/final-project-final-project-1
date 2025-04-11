package controller.listener;

import model.CarRecord;
import model.Imodel;
import view.DetailsWin;
import view.ResultsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResultsSelectMouseListener implements MouseListener {
    private ResultsWin resultsWin;
    private Imodel model;
    private CarRecord carRecord;

    public ResultsSelectMouseListener(ResultsWin resultsWin, Imodel model, CarRecord carRecord) {
        this.resultsWin = resultsWin;
        this.model = model;
        this.carRecord = carRecord;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        resultsWin.setVisible(false);

        DetailsWin detailsWin = new DetailsWin(resultsWin, model, carRecord);
        detailsWin.setVisible(true);
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
