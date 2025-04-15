package controller;

import model.CarRecord;
import view.ResultsWin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsFilterController {

    private ResultsWin resultsWin;

    private ResultsSortController resultsSortController;

    private List<CarRecord> originalCarRecords;

    private List<CarRecord> filteredCarRecords = new ArrayList<>();

    public ResultsFilterController(ResultsWin resultsWin, ResultsSortController resultsSortController, List<CarRecord> originalCarRecords) {
        this.resultsWin = resultsWin;
        this.resultsSortController = resultsSortController;
        this.originalCarRecords = originalCarRecords;
    }

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

            if (selectedMinPrice > selectedMaxPrice || selectedMinYear > selectedMaxYear ||
                    selectedMinMileage > selectedMaxMileage) {
                JOptionPane.showMessageDialog(
                        resultsWin, "Invalid filter values",
                        "Filter Error", JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Apply all filters
            filteredCarRecords = originalCarRecords.stream()
                    .filter(car -> car.price() >= selectedMinPrice && car.price() <= selectedMaxPrice)
                    .filter(car -> car.year() >= selectedMinYear && car.year() <= selectedMaxYear)
                    .filter(car -> car.mileage() >= selectedMinMileage && car.mileage() <= selectedMaxMileage)
                    .collect(Collectors.toList());

            resultsSortController.updateFilteredCarRecords(filteredCarRecords);

            resultsWin.updateGridPanel(filteredCarRecords);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    resultsWin, "Please enter valid numeric values for filtering",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void resetFilters(JTextField minPriceField, JTextField maxPriceField,
                             JTextField minYearField, JTextField maxYearField,
                             JTextField minMileageField, JTextField maxMileageField) {
        // Reset all filter fields
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
}
