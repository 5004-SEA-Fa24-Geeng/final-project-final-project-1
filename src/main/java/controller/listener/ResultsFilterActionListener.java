package controller.listener;

import model.CarRecord;
import view.ResultsWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsFilterActionListener implements ActionListener {
    private JTextField minPriceField;
    private JTextField maxPriceField;
    private JTextField minYearField;
    private JTextField maxYearField;
    private JTextField minMileageField;
    private JTextField maxMileageField;
    private ResultsWin resultsWin;
    private List<CarRecord> carRecords;

    public ResultsFilterActionListener(JTextField minPriceField, JTextField maxPriceField, JTextField minYearField,
                                       JTextField maxYearField, JTextField minMileageField, JTextField maxMileageField,
                                       ResultsWin resultsWin, List<CarRecord> carRecords) {
        this.minPriceField = minPriceField;
        this.maxPriceField = maxPriceField;
        this.minYearField = minYearField;
        this.maxYearField = maxYearField;
        this.minMileageField = minMileageField;
        this.maxMileageField = maxMileageField;
        this.resultsWin = resultsWin;
        this.carRecords = carRecords;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            List<CarRecord> filteredCarRecords = carRecords.stream()
                    .filter(car -> car.price() >= selectedMinPrice && car.price() <= selectedMaxPrice)
                    .filter(car -> car.year() >= selectedMinYear && car.year() <= selectedMaxYear)
                    .filter(car -> car.mileage() >= selectedMinMileage && car.mileage() <= selectedMaxMileage)
                    .collect(Collectors.toList());

            resultsWin.updateGridPanel(filteredCarRecords);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    resultsWin, "Please enter valid numeric values for filtering",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
