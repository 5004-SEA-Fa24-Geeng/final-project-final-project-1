package controller;

import model.CarRecord;
import view.ResultsWin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages filtering of car listings in the results window.
 */
public class ResultsFilterController {

    /** Reference to the results window. */
    private ResultsWin resultsWin;

    /** Reference to the sorting controller for filtered results. */
    private ResultsSortController resultsSortController;

    /** The original list of car records before filtering. */
    private List<CarRecord> originalCarRecords;

    /** The filtered list of car records after applying filters. */
    private List<CarRecord> filteredCarRecords = new ArrayList<>();

    /**
     * Constructs the filter controller with references to the results window and sorting controller.
     * @param resultsWin The results window managing car listings.
     * @param resultsSortController The sorting controller handling sorting behavior.
     * @param originalCarRecords The full list of car records before filtering.
     */
    public ResultsFilterController(ResultsWin resultsWin, ResultsSortController resultsSortController, List<CarRecord> originalCarRecords) {
        this.resultsWin = resultsWin;
        this.resultsSortController = resultsSortController;
        this.originalCarRecords = originalCarRecords;
    }

    /**
     * Applies filters to the car records based on price, year, and mileage range.
     * Displays an error message if the values are invalid.
     * @param minPriceField The minimum price filter input field.
     * @param maxPriceField The maximum price filter input field.
     * @param minYearField The minimum year filter input field.
     * @param maxYearField The maximum year filter input field.
     * @param minMileageField The minimum mileage filter input field.
     * @param maxMileageField The maximum mileage filter input field.
     */
    public void applyFilters(JTextField minPriceField, JTextField maxPriceField,
                             JTextField minYearField, JTextField maxYearField,
                             JTextField minMileageField, JTextField maxMileageField) {
        try {
            int selectedMinPrice = Integer.parseInt(minPriceField.getText());
            int selectedMaxPrice = Integer.parseInt(maxPriceField.getText());
            int selectedMinYear = Integer.parseInt(minYearField.getText());
            int selectedMaxYear = Integer.parseInt(maxYearField.getText());
            int selectedMinMileage = Integer.parseInt(minMileageField.getText());
            int selectedMaxMileage = Integer.parseInt(maxMileageField.getText());

            // Validate filter values
            if (selectedMinPrice > selectedMaxPrice || selectedMinYear > selectedMaxYear ||
                    selectedMinMileage > selectedMaxMileage) {
                JOptionPane.showMessageDialog(
                        resultsWin, "Invalid filter values",
                        "Filter Error", JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Apply filters to car records
            filteredCarRecords = originalCarRecords.stream()
                    .filter(car -> car.price() >= selectedMinPrice && car.price() <= selectedMaxPrice)
                    .filter(car -> car.year() >= selectedMinYear && car.year() <= selectedMaxYear)
                    .filter(car -> car.mileage() >= selectedMinMileage && car.mileage() <= selectedMaxMileage)
                    .collect(Collectors.toList());

            // Update results
            resultsSortController.updateFilteredCarRecords(filteredCarRecords);
            resultsWin.updateGridPanel(filteredCarRecords);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    resultsWin, "Please enter valid numeric values for filtering",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Resets all filers to default values and restores the full list of results.
     * @param minPriceField The minimum price filter input field.
     * @param maxPriceField The maxinum price filter input field.
     * @param minYearField The minimum year filter input field.
     * @param maxYearField The maximum year filter input field.
     * @param minMileageField The minimum mileage filter input field.
     * @param maxMileageField The maximum mileage filter input field.
     */
    public void resetFilters(JTextField minPriceField, JTextField maxPriceField,
                             JTextField minYearField, JTextField maxYearField,
                             JTextField minMileageField, JTextField maxMileageField) {
        // Reset all filter fields to default values
        minPriceField.setText("0");
        maxPriceField.setText("50000");
        minYearField.setText("1900");
        maxYearField.setText("2025");
        minMileageField.setText("0");
        maxMileageField.setText("300000");

        // Restore the full list of results
        resultsSortController.updateFilteredCarRecords(originalCarRecords);
        resultsWin.updateGridPanel(originalCarRecords);
    }

    public List<CarRecord> getOriginalCarRecords() {
        return originalCarRecords;
    }
}
