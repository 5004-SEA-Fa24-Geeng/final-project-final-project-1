package controller.listener;

import controller.ResultsSortController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsSortActionListener implements ActionListener {

    private ResultsSortController resultsSortController;

    public ResultsSortActionListener(ResultsSortController resultsSortController) {
        this.resultsSortController = resultsSortController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox<?> sortComboBox) {
            if (sortComboBox.getSelectedItem() instanceof String selectedSort) {
                resultsSortController.applySort(selectedSort);
            }
        }
    }
}
