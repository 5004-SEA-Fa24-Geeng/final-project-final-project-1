package controller.listener;

import model.CarRecord;
import model.Imodel;
import view.ResultsWin;
import view.SearchWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class SearchMouseListener implements MouseListener {
    private SearchWin searchWin;
    private Imodel model;

    public SearchMouseListener(SearchWin searchWin, Imodel model) {
        this.searchWin = searchWin;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String selectedMake = searchWin.getSelectedMake();
        String selectedModel = searchWin.getSelectedModel();

//        System.out.println("selectedMake: " + selectedMake);
//        System.out.println("selectedModel: " + selectedModel);

        List<CarRecord> searchResults;

        if ("Select".equals(selectedMake)) {
            // If no make is selected, show ALL cars
            searchResults = model.getAllCars();
        } else if ("Select".equals(selectedModel)) {
            // If only a make is selected, filter by make
            searchResults = model.getCarsByMake(selectedMake);
        } else {
            // Filter by both make and model
            searchResults = model.getCarsByMakeAndModel(selectedMake, selectedModel);
        }

        System.out.println("Search results: " + searchResults.size());

        // Create ResultsWin dynamically with search results
        ResultsWin resultsWin = new ResultsWin(searchWin, model, searchResults);

        resultsWin.setVisible(true);
        searchWin.setVisible(false);
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
