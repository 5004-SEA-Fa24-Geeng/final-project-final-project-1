package view;

import utility.Data;
import utility.MakeAndModelData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class SearchWin extends JFrame {
    private JLabel searchBgLabel = new JLabel(Data.START_BG_IMG_ICON);
    private JComboBox<String> makeComboBox;
    private JComboBox<String> modelComboBox;
    private JButton searchButton;
    private HashMap<String, List<String>> makeModelData;

    public SearchWin() {
        setTitle("Search");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        searchBgLabel.setBounds(0, 0, 900, 700);
        add(searchBgLabel);

        // Load CSV data
        makeModelData = MakeAndModelData.getMakeModelMap();

        // Make ComboBox with "Select" Option
        makeComboBox = new JComboBox<>();
        makeComboBox.addItem("Select"); // Default option
        for (String make : makeModelData.keySet()) {
            makeComboBox.addItem(make);
        }
        makeComboBox.setBounds(150, 300, 220, 80);
        searchBgLabel.add(makeComboBox);

        // Model ComboBox
        modelComboBox = new JComboBox<>();
        modelComboBox.addItem("Select"); // Default option
        modelComboBox.setEnabled(false); // Disabled initially
        modelComboBox.setBounds(400, 300, 220, 80);
        searchBgLabel.add(modelComboBox);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(650, 320, 150, 40);
        searchBgLabel.add(searchButton);

        // Listener for make selection
        makeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMake = (String) makeComboBox.getSelectedItem();
                updateModelList(selectedMake);
            }
        });
    }

    // Updates model list based on selected make
    private void updateModelList(String make) {
        modelComboBox.removeAllItems();
        modelComboBox.addItem("Select");

        if (makeModelData.containsKey(make)) {
            for (String model : makeModelData.get(make)) {
                modelComboBox.addItem(model);
            }
            modelComboBox.setEnabled(true);
        } else {
            modelComboBox.setEnabled(false);
        }
    }

    public String getSelectedMake() {
        return (String) makeComboBox.getSelectedItem();
    }

    public String getSelectedModel() {
        return (String) modelComboBox.getSelectedItem();
    }

    public JLabel getSearchBgLabel() {
        return searchBgLabel;
    }

    public JComboBox<String> getMakeComboBox() {
        return makeComboBox;
    }

    public JComboBox<String> getModelComboBox() {
        return modelComboBox;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
