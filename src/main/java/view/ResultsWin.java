package view;

import controller.listener.ResultsBackMouseListener;
import controller.listener.ResultsSelectMouseListener;
import controller.listener.ResultsSortActionListener;
import model.CarRecord;
import model.Imodel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultsWin extends JFrame {
    private SearchWin searchWin;
    private Imodel model;
    private JButton backButton;
    private JPanel gridPanel;

    public ResultsWin(SearchWin searchWin, Imodel model, List<CarRecord> carRecords) {
        this.searchWin = searchWin;
        this.model = model;

        setTitle("Search Results");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(15, 15));

        // Sidebar filter panel
        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(280, 750));
        filterPanel.setBackground(Color.WHITE);

        // Sorting bar panel
        JPanel sortBarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sortBarPanel.setPreferredSize(new Dimension(600, 50));
        sortBarPanel.setBackground(Color.WHITE);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addMouseListener(new ResultsBackMouseListener(searchWin, this));

        JLabel sortLabel = new JLabel("Sort");
        String[] sortOptions = {
                "Select", // Default option
                "Price (Low to High)",
                "Price (High to Low)",
                "Year (Newest First)",
                "Year (Oldest First)",
                "Mileage (Low to High)",
                "Mileage (High to Low)",
        };
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);

        // Attach sorting listener
        sortComboBox.addActionListener(new ResultsSortActionListener(this, carRecords, gridPanel));

        sortBarPanel.add(backButton);
        sortBarPanel.add(Box.createHorizontalStrut(500));
        sortBarPanel.add(sortLabel);
        sortBarPanel.add(sortComboBox);

        // Grid panel for dynamically displaying cars
        gridPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        gridPanel.setBackground(Color.WHITE);

        // Populate grid with car records
        for (CarRecord car : carRecords) {
            JPanel carPanel = new JPanel(new BorderLayout());
            carPanel.setBackground(new Color(240, 240, 240));

            JLabel carImageLabel;

            try {
                String imagePath = car.imageUrl();

                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(image);
                carImageLabel = new JLabel(resizedIcon);
            } catch (Exception e) {
                e.printStackTrace();

                ImageIcon fallbackIcon = new ImageIcon("data/images/default.jpg");
                Image fallbackImage = fallbackIcon.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
                ImageIcon resizedFallbackIcon = new ImageIcon(fallbackImage);

                carImageLabel = new JLabel(resizedFallbackIcon);
            }

            JLabel carTitleLabel = new JLabel(car.getTitle(), JLabel.CENTER);
            carTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));

            JLabel carPriceLabel = new JLabel("$" + car.price(), JLabel.CENTER);
            carPriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            carPriceLabel.setForeground(Color.BLUE);

            // Click listeners for redirecting to DetailsWin
            carImageLabel.addMouseListener(new ResultsSelectMouseListener(this, model, car));
            carPanel.addMouseListener(new ResultsSelectMouseListener(this, model, car));

            carPanel.add(carImageLabel, BorderLayout.CENTER);
            carPanel.add(carTitleLabel, BorderLayout.NORTH);
            carPanel.add(carPriceLabel, BorderLayout.SOUTH);

            gridPanel.add(carPanel);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(filterPanel, BorderLayout.WEST);
        add(sortBarPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateGridPanel(List<CarRecord> sortedCarRecords) {
        gridPanel.removeAll();

        for (CarRecord car : sortedCarRecords) {
            JPanel carPanel = new JPanel(new BorderLayout());
            carPanel.setBackground(new Color(240, 240, 240));

            JLabel carImageLabel;
            try {
                String imagePath = car.imageUrl();
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(image);
                carImageLabel = new JLabel(resizedIcon);
            } catch (Exception e) {
                e.printStackTrace();
                ImageIcon fallbackIcon = new ImageIcon("data/images/default.jpg");
                Image fallbackImage = fallbackIcon.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
                ImageIcon resizedFallbackIcon = new ImageIcon(fallbackImage);
                carImageLabel = new JLabel(resizedFallbackIcon);
            }

            JLabel carTitleLabel = new JLabel(car.getTitle(), JLabel.CENTER);
            carTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));

            JLabel carPriceLabel = new JLabel("$" + car.price(), JLabel.CENTER);
            carPriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            carPriceLabel.setForeground(Color.BLUE);

            // Reattach listeners
            carImageLabel.addMouseListener(new ResultsSelectMouseListener(this, model, car));
            carPanel.addMouseListener(new ResultsSelectMouseListener(this, model, car));

            carPanel.add(carImageLabel, BorderLayout.CENTER);
            carPanel.add(carTitleLabel, BorderLayout.NORTH);
            carPanel.add(carPriceLabel, BorderLayout.SOUTH);

            gridPanel.add(carPanel);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public JButton getBackButton() {
        return backButton;
    }
}
