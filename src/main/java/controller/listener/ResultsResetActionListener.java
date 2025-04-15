package controller.listener;

import controller.ResultsFilterController;
import model.CarRecord;
import view.ResultsWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultsResetActionListener implements ActionListener {
    private ResultsFilterController resultsFilterController;
    private JTextField minPriceField;
    private JTextField maxPriceField;
    private JTextField minYearField;
    private JTextField maxYearField;
    private JTextField minMileageField;
    private JTextField maxMileageField;

    public ResultsResetActionListener(ResultsFilterController resultsFilterController,
                                      JTextField minPriceField, JTextField maxPriceField,
                                      JTextField minYearField, JTextField maxYearField,
                                      JTextField minMileageField, JTextField maxMileageField) {
        this.resultsFilterController = resultsFilterController;
        this.minPriceField = minPriceField;
        this.maxPriceField = maxPriceField;
        this.minYearField = minYearField;
        this.maxYearField = maxYearField;
        this.minMileageField = minMileageField;
        this.maxMileageField = maxMileageField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resultsFilterController.resetFilters(
                minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField
        );
    }
}
