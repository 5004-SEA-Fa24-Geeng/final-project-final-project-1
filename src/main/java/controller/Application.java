package controller;

import controller.listener.SearchMouseListener;
import model.Imodel;
import model.Model;
import view.SearchWin;

/**
 * Main application class responsible for initializing and launching the search window.
 */
public final class Application {

    /**
     * private constructor for preventing instantiate.
     */
    private Application() {

    }

    /**
     * Runs the application by initializing the model and search window.
     */
    public static void run() {
        // Initialize the model
        Imodel model = new Model();

        // Initialize the search window
        SearchWin searchWin = new SearchWin();

        // Attach search listener to handle search actions
        searchWin.getSearchButton().addMouseListener(new SearchMouseListener(searchWin, model));

        // Display the search window
        searchWin.setVisible(true);
    }
}
