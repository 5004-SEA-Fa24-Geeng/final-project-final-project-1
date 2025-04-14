package controller.listener;

import model.CarRecord;
import view.ResultsWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultsResetActionListener implements ActionListener {
    private JTextField minPriceField;
    private JTextField maxPriceField;
    private JTextField minYearField;
    private JTextField maxYearField;
    private JTextField minMileageField;
    private JTextField maxMileageField;
    private ResultsWin resultsWin;
    private List<CarRecord> carRecords;

    public ResultsResetActionListener(JTextField minPriceField, JTextField maxPriceField, JTextField minYearField,
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
        // Reset all filter fields
        minPriceField.setText("0");
        maxPriceField.setText("50000");
        minYearField.setText("1900");
        maxYearField.setText("2025");
        minMileageField.setText("0");
        maxMileageField.setText("300000");

        // Restore the full list of results
        resultsWin.updateGridPanel(carRecords);
    }
}
