package controller.listener;

import controller.ResultsFilterController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for handling the action event to apply filters based on user-defined
 * criteria in the results window.
 */
public class ResultsFilterActionListener implements ActionListener {

    /** Controller responsible for managing the filtering logic. */
    private ResultsFilterController resultsFilterController;

    /** Minimum price filter field. */
    private JTextField minPriceField;

    /** Maximum price filter field. */
    private JTextField maxPriceField;

    /** Minimum year filter field. */
    private JTextField minYearField;

    /** Maximum year filter field. */
    private JTextField maxYearField;

    /** Minimum mileage filter field. */
    private JTextField minMileageField;

    /** Maximum mileage filter field. */
    private JTextField maxMileageField;

    /**
     * Constructs a ResultsFilterActionListener with the given filter fields.
     * @param resultsFilterController The controller instance handling filter application.
     * @param minPriceField The minimum price filter field.
     * @param maxPriceField The maximum price filter field.
     * @param minYearField The minimum year filter field.
     * @param maxYearField The maximum year filter field.
     * @param minMileageField The minimum mileage filter field.
     * @param maxMileageField The maximum mileage filter field.
     */
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

    /**
     * Handles the action event to apply filters to the results view.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        resultsFilterController.applyFilters(
                minPriceField, maxPriceField, minYearField, maxYearField, minMileageField, maxMileageField
        );
    }
}
