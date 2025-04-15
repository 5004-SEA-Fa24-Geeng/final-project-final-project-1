package view;

import controller.listener.DetailsBackMouseListener;
import controller.listener.DetailsWishlistActionListener;
import controller.listener.DetailsWishlistMouseListener;
import model.CarRecord;
import model.Imodel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DetailsWin extends JFrame {
    private ResultsWin resultsWin;
    private Imodel model;
    private CarRecord carRecord;
    private JButton backButton;
    private JButton showWishlistButton;
    private JLabel carImageLabel;
    private JTextArea carTitleTextArea;
    private JLabel carPriceLabel;
    private JButton addToWishlistButton;

    public DetailsWin(ResultsWin resultsWin, Imodel model, CarRecord carRecord) {
        this.resultsWin = resultsWin;
        this.model = model;
        this.carRecord = carRecord;

        setTitle("Car Details");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Main panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setPreferredSize(new Dimension(1100, 400));
        mainPanel.setBackground(Color.WHITE);

        // Add carImagePanel and carInfoPanel to mainPanel
        mainPanel.add(createCarImagePanel());
        mainPanel.add(createCarInfoPanel());

        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(1100, 250));
        footerPanel.setBackground(Color.WHITE);

        contentPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.add(footerPanel, BorderLayout.SOUTH);

        add(contentPanel);
    }

    private JPanel createHeaderPanel() {
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1100, 100));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setMinimumSize(new Dimension(1100, 100));

        // Back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addMouseListener(new DetailsBackMouseListener(resultsWin, this));

        // Back button panel
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.setBackground(Color.WHITE);
        backButtonPanel.add(backButton);

        // Show wishlist button
        showWishlistButton = new JButton("Show Wishlist");
        showWishlistButton.setFont(new Font("Arial", Font.PLAIN, 14));
        showWishlistButton.addMouseListener(new DetailsWishlistMouseListener(this, model));

        // Show wishlist button panel
        JPanel showWishlistButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        showWishlistButtonPanel.setBackground(Color.WHITE);
        showWishlistButtonPanel.add(showWishlistButton);

        // Add backButtonPanel and showWishlistButtonPanel to headerPanel
        headerPanel.add(backButtonPanel, BorderLayout.WEST);
        headerPanel.add(showWishlistButtonPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createCarImagePanel() {
        // Car image panel
        JPanel carImagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        carImagePanel.setPreferredSize(new Dimension(480, 400));
        carImagePanel.setBackground(Color.WHITE);

        // Load the car image dynamically
        ImageIcon originalIcon = new ImageIcon(carRecord.imageUrl());
        Image image = originalIcon.getImage().getScaledInstance(480, 350, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        carImageLabel = new JLabel(resizedIcon);

        carImagePanel.add(carImageLabel);

        return carImagePanel;
    }

    private JPanel createCarInfoPanel() {
        // Car info panel
        JPanel carInfoPanel = new JPanel(new BorderLayout());
        carInfoPanel.setPreferredSize(new Dimension(620, 400));
        carInfoPanel.setBackground(Color.WHITE);

        // Car title and price panel
        JPanel carTitlePricePanel = new JPanel(new BorderLayout());
        carTitlePricePanel.setPreferredSize(new Dimension(620, 80));
        carTitlePricePanel.setBackground(Color.WHITE);

        // Title
        carTitleTextArea = new JTextArea(carRecord.getTitle());
        carTitleTextArea.setFont(new Font("Arial", Font.BOLD, 24));
        carTitleTextArea.setWrapStyleWord(true);
        carTitleTextArea.setLineWrap(true);
        carTitleTextArea.setEditable(false);
        carTitleTextArea.setOpaque(false);
        carTitleTextArea.setBorder(BorderFactory.createEmptyBorder());
        carTitleTextArea.setBorder(new EmptyBorder(0, 0, 0, 40));

        // Price
        carPriceLabel = new JLabel("$" + carRecord.price());
        carPriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        carPriceLabel.setForeground(Color.BLUE);
        carPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        carPriceLabel.setBorder(new EmptyBorder(0, 0, 0, 40));

        // Add carTitleLabel and carPriceLabel to carTitlePricePanel
        carTitlePricePanel.add(carTitleTextArea, BorderLayout.NORTH);
        carTitlePricePanel.add(carPriceLabel, BorderLayout.SOUTH);

        // Car details panel
        JPanel carDetailsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        carDetailsPanel.setPreferredSize(new Dimension(620, 140));
        carDetailsPanel.setBackground(Color.WHITE);
        carDetailsPanel.setBorder(new EmptyBorder(20, 0, 40, 0));

        // Car attributes
        JLabel mileageLabel = new JLabel("Mileage: " + carRecord.mileage() + " miles");
        mileageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel zipLabel = new JLabel("Location: " + carRecord.zip());
        zipLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel engineLabel = new JLabel("Engine: " + carRecord.engineInfo());
        engineLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel bodyTypeLabel = new JLabel("Body Type: " + carRecord.bodyType());
        bodyTypeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel cylinderLabel = new JLabel("Cylinders: " + carRecord.numOfCylinders());
        cylinderLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel driveTypeLabel = new JLabel("Drive Type: " + carRecord.driveType());
        driveTypeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        carDetailsPanel.add(mileageLabel);
        carDetailsPanel.add(zipLabel);
        carDetailsPanel.add(engineLabel);
        carDetailsPanel.add(bodyTypeLabel);
        carDetailsPanel.add(cylinderLabel);
        carDetailsPanel.add(driveTypeLabel);

        // Car add to wishlist panel
        JPanel carAddToWishlistPanel = new JPanel(new BorderLayout());
        carAddToWishlistPanel.setPreferredSize(new Dimension(620, 80));
        carAddToWishlistPanel.setBackground(Color.GREEN);

        // Add to wishlist button panel
        JPanel addToWishlistButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addToWishlistButtonPanel.setBackground(Color.WHITE);

        // Add to wishlist button
        addToWishlistButton = new JButton("Add to Wish List");
        addToWishlistButton.setFont(new Font("Arial", Font.BOLD, 18));
        addToWishlistButton.setPreferredSize(new Dimension(240, 50));
        addToWishlistButton.addActionListener(new DetailsWishlistActionListener(this, model, carRecord));

        // Add addToWishlistButton to addToWishlistButtonPanel
        addToWishlistButtonPanel.add(addToWishlistButton);

        // Add addToWishlistButtonPanel to carAddToWishlistPanel
        carAddToWishlistPanel.add(addToWishlistButtonPanel, BorderLayout.CENTER);

        // Add carTitlePricePanel, carDetailsPanel, and carAddToWishlistPanel to carInfoPanel
        carInfoPanel.add(carTitlePricePanel, BorderLayout.NORTH);
        carInfoPanel.add(carDetailsPanel, BorderLayout.CENTER);
        carInfoPanel.add(carAddToWishlistPanel, BorderLayout.SOUTH);

        return carInfoPanel;
    }
}
