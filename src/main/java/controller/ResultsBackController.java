package controller;

import view.ResultsWin;
import view.SearchWin;

public class ResultsBackController {
    private SearchWin searchWin;
    private ResultsWin resultsWin;

    public ResultsBackController(SearchWin searchWin, ResultsWin resultsWin) {
        this.searchWin = searchWin;
        this.resultsWin = resultsWin;
    }

    public void goBackToSearch() {
        resultsWin.setVisible(false);
        searchWin.setVisible(true);
    }
}
