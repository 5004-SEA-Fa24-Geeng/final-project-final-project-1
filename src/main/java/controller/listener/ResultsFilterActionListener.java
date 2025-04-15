package controller.listener;

import controller.ResultsFilterController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsFilterActionListener implements ActionListener {
    private ResultsFilterController resultsFilterController;
    private JTextField minPriceField;
    private JTextField maxPriceField;
    private JTextField minYearField;
    private JTextField maxYearField;
    private JTextField minMileageField;
    private JTextField maxMileageField;

    public ResultsFilterActionListener(ResultsFilterController resultsFilterController,
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
        resultsFilterController.applyFilters(
                minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField
        );
    }
}
