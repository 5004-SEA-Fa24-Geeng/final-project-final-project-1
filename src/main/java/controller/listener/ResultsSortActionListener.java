package controller.listener;

import controller.ResultsSortController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for handling the action event to apply sorting based on user selection
 * from a combo box.
 */
public class ResultsSortActionListener implements ActionListener {

    /** Controller responsible for managing sorting logic. */
    private ResultsSortController resultsSortController;

    /**
     * Constructs a ResultsSortActionListener with the given sorting controller.
     * @param resultsSortController The controller instance handling sorting operations.
     */
    public ResultsSortActionListener(ResultsSortController resultsSortController) {
        this.resultsSortController = resultsSortController;
    }

    /**
     * Handles the action event triggered by the sorting combo box selection.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox<?> sortComboBox) {
            if (sortComboBox.getSelectedItem() instanceof String selectedSort) {
                resultsSortController.applySort(selectedSort);
            }
        }
    }
}
