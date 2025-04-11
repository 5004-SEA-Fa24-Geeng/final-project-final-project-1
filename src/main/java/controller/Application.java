package controller;

import controller.listener.SearchMouseListener;
import model.Imodel;
import model.Model;
import view.SearchWin;

public class Application {
    public static void run() {
        // Initialize the model
        Imodel model = new Model();

        // Initialize the search view
        SearchWin searchWin = new SearchWin();

        // Attach search listener
        searchWin.getSearchButton().addMouseListener(new SearchMouseListener(searchWin, model));

        searchWin.setVisible(true);
    }
}
