package controller;

import view.DetailsWin;
import view.ResultsWin;

public class DetailsBackController {
    private ResultsWin resultsWin;

    private DetailsWin detailsWin;

    public DetailsBackController(ResultsWin resultsWin, DetailsWin detailsWin) {
        this.resultsWin = resultsWin;
        this.detailsWin = detailsWin;
    }

    public void goBackToResults() {
        resultsWin.setVisible(true);
        detailsWin.setVisible(false);
    }
}
