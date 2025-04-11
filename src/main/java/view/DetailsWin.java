package view;

import controller.listener.DetailsBackMouseListener;
import controller.listener.DetailsWishlistActionListener;
import controller.listener.DetailsWishlistMouseListener;
import model.CarRecord;
import model.Imodel;

import javax.swing.*;
import java.awt.*;

public class DetailsWin extends JFrame {
    private ResultsWin resultsWin;
    private Imodel model;
    private CarRecord carRecord;
    private JButton backButton;
    private JButton showWishlistButton;
    private JLabel carImageLabel;
    private JLabel carTitleLabel;
    private JLabel carPriceLabel;
    private JButton wishlistButton;

    public DetailsWin(ResultsWin resultsWin, Imodel model, CarRecord carRecord) {
        this.resultsWin = resultsWin;
        this.model = model;
        this.carRecord = carRecord;

        setTitle("Car Details");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Main scrollable panel
        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setBackground(Color.WHITE);

        // Placeholder panel for future features
        JPanel placeholderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        placeholderPanel.setPreferredSize(new Dimension(1100, 50));
        placeholderPanel.setBackground(Color.WHITE);

        // Back button panel
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addMouseListener(new DetailsBackMouseListener(resultsWin, this));
        placeholderPanel.add(backButton);

        // Show Wishlist button
        showWishlistButton = new JButton("Show Wishlist");
        showWishlistButton.setFont(new Font("Arial", Font.PLAIN, 14));
        showWishlistButton.addMouseListener(new DetailsWishlistMouseListener(this, model));
        placeholderPanel.add(showWishlistButton);

        // Car image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);

        // Load the correct car image dynamically
        ImageIcon originalIcon = new ImageIcon(carRecord.imageUrl());
        Image image = originalIcon.getImage().getScaledInstance(480, 350, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        carImageLabel = new JLabel(resizedIcon);

        imagePanel.add(carImageLabel);

        // Car info panel
        JPanel carInfoPanel = new JPanel(new BorderLayout(15, 15));
        carInfoPanel.setBackground(Color.WHITE);
        carInfoPanel.setPreferredSize(new Dimension(560, 300));

        // Title
        carTitleLabel = new JLabel(carRecord.getTitle());
        carTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        carTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Price
        carPriceLabel = new JLabel("$" + carRecord.price());
        carPriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        carPriceLabel.setForeground(Color.BLUE);
        carPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Wishlist button
        wishlistButton = new JButton("Add to Wish List");
        wishlistButton.setFont(new Font("Arial", Font.BOLD, 18));
        wishlistButton.setPreferredSize(new Dimension(240, 50));
        wishlistButton.addActionListener(new DetailsWishlistActionListener(this, model, carRecord));

        carInfoPanel.add(carTitleLabel, BorderLayout.WEST);
        carInfoPanel.add(carPriceLabel, BorderLayout.EAST);
        carInfoPanel.add(wishlistButton, BorderLayout.SOUTH);

        // Main layout panel
        JPanel mainLayoutPanel = new JPanel(new GridBagLayout());
        mainLayoutPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.45;
        gbc.weighty = 1.0;
        mainLayoutPanel.add(imagePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.55;
        gbc.weighty = 1.0;
        mainLayoutPanel.add(carInfoPanel, gbc);

        contentPanel.add(placeholderPanel, BorderLayout.NORTH);
        contentPanel.add(mainLayoutPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane);
    }
}
