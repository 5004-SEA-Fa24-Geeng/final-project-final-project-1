package controller.listener;

import model.CarRecord;
import view.ResultsWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class ResultsSortActionListener implements ActionListener {
    private ResultsWin resultsWin;
    private List<CarRecord> carRecords;
    private JPanel gridPanel;

    public ResultsSortActionListener(ResultsWin resultsWin, List<CarRecord> carRecords, JPanel gridPanel) {
        this.resultsWin = resultsWin;
        this.carRecords = carRecords;
        this.gridPanel = gridPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox<?> sortComboBox) {
            if (sortComboBox.getSelectedItem() instanceof String selectedSort) {
                // If "Select" is chosen, don't apply any sorting
                switch (selectedSort) {
                    case "Select" -> {
                        return;
                    }

                    // Sorting logic
                    case "Price (Low to High)" ->
                            carRecords.sort(Comparator.comparingDouble(CarRecord::price));
                    case "Price (High to Low)" ->
                            carRecords.sort(Comparator.comparingDouble(CarRecord::price).reversed());
                    case "Year (Newest First)" ->
                            carRecords.sort(Comparator.comparingInt(CarRecord::year).reversed());
                    case "Year (Oldest First)" ->
                            carRecords.sort(Comparator.comparingInt(CarRecord::year));
                    case "Mileage (Low to High)" ->
                            carRecords.sort(Comparator.comparingInt(CarRecord::mileage));
                    case "Mileage (High to Low)" ->
                            carRecords.sort(Comparator.comparingInt(CarRecord::mileage).reversed());
                }
            }
        }

        resultsWin.updateGridPanel(carRecords);
    }
}
