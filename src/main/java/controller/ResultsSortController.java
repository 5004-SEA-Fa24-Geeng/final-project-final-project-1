package controller;

import model.CarRecord;
import view.ResultsWin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Handles sorting of car listings in the results window.
 */
public class ResultsSortController {

    /** Reference to the results window. */
    private ResultsWin resultsWin;

    /** The original list of car records before filtering or sorting. */
    private List<CarRecord> originalCarRecords;

    /** The list of car records after filtering. */
    private List<CarRecord> filteredCarRecords = new ArrayList<>();

    /** The list of car records after sorting. */
    private List<CarRecord> sortedCarRecords = new ArrayList<>();

    /**
     * Constructs the sort controller with references to the results window and car records.
     * @param resultsWin The results window managing car listings.
     * @param originalCarRecords The full list of car records before filtering or sorting.
     */
    public ResultsSortController(ResultsWin resultsWin, List<CarRecord> originalCarRecords) {
        this.resultsWin = resultsWin;
        this.originalCarRecords = originalCarRecords;
        this.filteredCarRecords = new ArrayList<>(originalCarRecords);
    }

    /**
     * Updates the list of filtered car records before sorting.
     * @param filteredCarRecords The list of cars after applying filters.
     */
    public void updateFilteredCarRecords(List<CarRecord> filteredCarRecords) {
        this.filteredCarRecords = new ArrayList<>(filteredCarRecords);
    }

    /**
     * Applies sorting to the filtered car records based on the selected criteria.
     * @param selectedSort The sorting option chosen by the user.
     */
    public void applySort(String selectedSort) {
        if ("Select".equals(selectedSort)) {
            return; // No sorting applied
        }

        // Determine the base list for sorting
        if (filteredCarRecords.isEmpty()) {
            sortedCarRecords = new ArrayList<>(originalCarRecords);
        } else {
            sortedCarRecords = new ArrayList<>(filteredCarRecords);
        }

        // Apply the sorting method based on user selection
        switch (selectedSort) {
            case "Price (Low to High)" -> sortedCarRecords.sort(Comparator.comparingDouble(CarRecord::price));
            case "Price (High to Low)" -> sortedCarRecords.sort(Comparator.comparingDouble(CarRecord::price).reversed());
            case "Year (Newest First)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::year).reversed());
            case "Year (Oldest First)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::year));
            case "Mileage (Low to High)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::mileage));
            case "Mileage (High to Low)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::mileage).reversed());
        }

        // Update the results window with sorted records
        resultsWin.updateGridPanel(sortedCarRecords);
    }
}
