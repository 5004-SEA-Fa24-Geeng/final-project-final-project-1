package controller;

import model.CarRecord;
import model.Imodel;
import view.DetailsWin;
import view.ResultsWin;

public class DetailsWinController {
    private ResultsWin resultsWin;
    private Imodel model;
    private CarRecord carRecord;

    public DetailsWinController(ResultsWin resultsWin, Imodel model, CarRecord carRecord) {
        this.resultsWin = resultsWin;
        this.model = model;
        this.carRecord = carRecord;
    }

    public void showDetails() {
        resultsWin.setVisible(false);
        DetailsWin detailsWin = new DetailsWin(resultsWin, model, carRecord);
        detailsWin.setVisible(true);
    }
}
