package view;

import controller.listener.WishlistBackMouseListener;
import model.CarRecord;
import model.Imodel;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class WishlistWin extends JFrame {
    private DetailsWin detailsWin;
    private Imodel model;
    private JButton backButton;
    private JPanel listPanel;

    public WishlistWin(DetailsWin detailsWin, Imodel model) {
        this.detailsWin = detailsWin;
        this.model = model;

        setTitle("Wishlist");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Main panel
        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setBackground(Color.WHITE);

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setPreferredSize(new Dimension(1100, 50));
        headerPanel.setBackground(Color.WHITE);

        // Back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addMouseListener(new WishlistBackMouseListener(this, detailsWin));
        headerPanel.add(backButton);

        // Scrollable list panel
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 10, 10));
        listPanel.setBackground(Color.WHITE);

        loadWishlist();

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel);
    }

    private void loadWishlist() {
        Set<CarRecord> wishlist = model.getWishlist();
        listPanel.removeAll();

        for (CarRecord car : wishlist) {
            JPanel rowPanel = new JPanel(new BorderLayout());
            rowPanel.setBackground(Color.WHITE);
            rowPanel.setPreferredSize(new Dimension(850, 120));

            // Load car image
            JLabel carImageLabel;
            try {
                // Get the imageUrl
                String makeFirstWord = car.make().split(" ")[0];
                String modelFirstWord = car.model().split(" ")[0];
                String imageUrl = "data/images/" + car.year() + makeFirstWord + modelFirstWord + ".jpg";

//                System.out.println("imageUrl: " + imageUrl);

                ImageIcon imageIcon = new ImageIcon(imageUrl);
                Image image = imageIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
                carImageLabel = new JLabel(new ImageIcon(image));
            } catch (Exception e) {
                e.printStackTrace();
                ImageIcon fallbackIcon = new ImageIcon("data/images/default.jpg");
                Image fallbackImage = fallbackIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
                carImageLabel = new JLabel(new ImageIcon(fallbackImage));
            }

            // Car title and price panel
            JPanel carInfoPanel = new JPanel(new GridLayout(2, 1));
            carInfoPanel.setBackground(Color.WHITE);

            JLabel carTitleLabel = new JLabel(car.getTitle());
            carTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel carPriceLabel = new JLabel("$" + car.price());
            carPriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            carPriceLabel.setForeground(Color.BLUE);

            carInfoPanel.add(carTitleLabel);
            carInfoPanel.add(carPriceLabel);

            rowPanel.add(carImageLabel, BorderLayout.WEST);
            rowPanel.add(carInfoPanel, BorderLayout.CENTER);

            listPanel.add(rowPanel);
        }

        listPanel.revalidate();
        listPanel.repaint();
    }
}
