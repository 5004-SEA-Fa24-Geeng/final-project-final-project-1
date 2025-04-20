package view;

import controller.ResultsFilterController;
import controller.ResultsSortController;
import controller.listener.*;
import model.CarRecord;
import model.Imodel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents the search results window displaying filtered car listings.
 */
public class ResultsWin extends JFrame {

    /** Reference to the search window. */
    private SearchWin searchWin;

    /** Reference to the data model storing car records. */
    private Imodel model;

    /** Button for navigating back to the search window. */
    private JButton backButton;

    /** Panel displaying car listings in a grid layout. */
    private JPanel gridPanel;

    /** Text fields for filtering based on price range. */
    private JTextField minPriceField, maxPriceField;

    /** Text fields for filtering based on year range. */
    private JTextField minYearField, maxYearField;

    /** Text fields for filtering based on mileage range. */
    private JTextField minMileageField, maxMileageField;

    private ResultsFilterController resultsFilterController;

    private ResultsSortController resultsSortController;

    /**
     * Constructs the results window and initializes UI components.
     * @param searchWin The search window.
     * @param model The data model containing car listings.
     * @param carRecords The list of car records to be displayed.
     */
    public ResultsWin(SearchWin searchWin, Imodel model, List<CarRecord> carRecords) {
        this.searchWin = searchWin;
        this.model = model;

        this.resultsSortController = new ResultsSortController(this, carRecords);
        this.resultsFilterController = new ResultsFilterController(this, resultsSortController, carRecords);

        setTitle("Search Results");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(15, 15));

        // Add organized panels
        add(createFilterPanel(carRecords), BorderLayout.WEST);
        add(createSortBarPanel(carRecords), BorderLayout.NORTH);
        add(createGridPanel(carRecords), BorderLayout.CENTER);
    }

    /**
     * Create the sorting bar panel for arranging search results.
     * @param carRecords List of car records used for sorting.
     * @return JPanel containing sorting options.
     */
    private JPanel createSortBarPanel(List<CarRecord> carRecords) {
        JPanel sortBarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sortBarPanel.setPreferredSize(new Dimension(600, 50));
        sortBarPanel.setBackground(Color.WHITE);

        // Initialize back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addMouseListener(new ResultsBackMouseListener(searchWin, this));

        // Sorting dropdown setup
        JLabel sortLabel = new JLabel("Sort");
        String[] sortOptions = {
                "Select", // Default option
                "Price (Low to High)", "Price (High to Low)",
                "Year (Newest First)", "Year (Oldest First)",
                "Mileage (Low to High)", "Mileage (High to Low)",
        };
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);

        // Attach sorting listener
        sortComboBox.addActionListener(new ResultsSortActionListener(resultsSortController));

        sortBarPanel.add(backButton);
        sortBarPanel.add(Box.createHorizontalStrut(500));
        sortBarPanel.add(sortLabel);
        sortBarPanel.add(sortComboBox);

        return sortBarPanel;
    }

    /**
     * Creates the sidebar filter panel for refining search results.
     * @param carRecords List of car records to be filtered.
     * @return JPanel containing filter options.
     */
    private JPanel createFilterPanel(List<CarRecord> carRecords) {
        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(280, 750));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));

        setupFilterComponents(filterPanel, carRecords);

        return filterPanel;
    }

    /**
     * Sets up filtering components, including price, year, and mileage inputs.
     * @param filterPanel Panel where filter elements are placed.
     * @param carRecords List of car records for filtering.
     */
    private void setupFilterComponents(JPanel filterPanel, List<CarRecord> carRecords) {
        // Price filter components
        JPanel priceFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel priceLabel = new JLabel("Price: ");
        minPriceField = new JTextField("0", 7);
        JLabel toPriceLabel = new JLabel("to");
        maxPriceField = new JTextField("50000", 7);
        priceFilterPanel.add(priceLabel);
        priceFilterPanel.add(minPriceField);
        priceFilterPanel.add(toPriceLabel);
        priceFilterPanel.add(maxPriceField);

        // Year filter components
        JPanel yearFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel yearLabel = new JLabel("Year: ");
        minYearField = new JTextField("1900", 7);
        JLabel toYearLabel = new JLabel("to");
        maxYearField = new JTextField("2025", 7);
        yearFilterPanel.add(yearLabel);
        yearFilterPanel.add(minYearField);
        yearFilterPanel.add(toYearLabel);
        yearFilterPanel.add(maxYearField);

        // Mileage filter components
        JPanel mileageFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel mileageLabel = new JLabel("Mileage: ");
        minMileageField = new JTextField("0", 7);
        JLabel toMileageLabel = new JLabel("to");
        maxMileageField = new JTextField("300000", 7);
        mileageFilterPanel.add(mileageLabel);
        mileageFilterPanel.add(minMileageField);
        mileageFilterPanel.add(toMileageLabel);
        mileageFilterPanel.add(maxMileageField);

        // Filter button panel with apply and reset actions
        JPanel filterButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton applyFilterButton = new JButton("filter");
        JButton resetFilterButton = new JButton("reset");

        applyFilterButton.addActionListener(new ResultsFilterActionListener(
                resultsFilterController, minPriceField, maxPriceField,
                minYearField, maxYearField, minMileageField, maxMileageField
        ));
        resetFilterButton.addActionListener(new ResultsResetActionListener(
                resultsFilterController, minPriceField, maxPriceField,
                minYearField, maxYearField, minMileageField, maxMileageField
        ));

        filterButtonPanel.add(applyFilterButton);
        filterButtonPanel.add(resetFilterButton);

        // Add components to filter panel
        filterPanel.add(priceFilterPanel);
        filterPanel.add(Box.createVerticalStrut(10));
        filterPanel.add(yearFilterPanel);
        filterPanel.add(Box.createVerticalStrut(10));
        filterPanel.add(mileageFilterPanel);
        filterPanel.add(Box.createVerticalStrut(10));
        filterPanel.add(filterButtonPanel);
    }

    /**
     * Creates the grid panel displaying car listings.
     * @param carRecords List of car records to be displayed.
     * @return JScrollPane containing the grid panel.
     */
    public JScrollPane createGridPanel(List<CarRecord> carRecords) {
        gridPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        gridPanel.setBackground(Color.WHITE);

        // Populate grid with car records
        for (CarRecord car : carRecords) {
            gridPanel.add(createCarPanel(car));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

    /**
     * Updates the grid panel with sorted car records based on price range.
     * @param sortedCarRecords List of filtered car records.
     */
    public void updateGridPanel(List<CarRecord> sortedCarRecords) {
        gridPanel.removeAll();

        // Populate grid with filtered car records
        for (CarRecord car : sortedCarRecords) {
            gridPanel.add(createCarPanel(car));
        }

        // Refresh UI
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    /**
     * Creates a panel displaying car details including image, title, and price.
     * @param car The car record to be displayed.
     * @return JPanel representing the car.
     */
    private JPanel createCarPanel(CarRecord car) {
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

        // Attach listeners for selecting the car
        carImageLabel.addMouseListener(new ResultsSelectMouseListener(this, model, car));
        carPanel.addMouseListener(new ResultsSelectMouseListener(this, model, car));

        carPanel.add(carImageLabel, BorderLayout.CENTER);
        carPanel.add(carTitleLabel, BorderLayout.NORTH);
        carPanel.add(carPriceLabel, BorderLayout.SOUTH);

        return carPanel;
    }

    /**
     * Gets the back button.
     * @return JButton used for returning to the search window.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Gets the grid panel displaying car listings.
     * @return JPanel containing the visual representation of car records in a grid format.
     */
    public JPanel getGridPanel() {
        return gridPanel;
    }
}
