package view;

import controller.WishlistContentController;
import controller.listener.WishlistBackMouseListener;
import controller.listener.WishlistRemoveActionListener;
import controller.listener.WishlistRemoveAllActionListener;
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
    private WishlistContentController wishlistContentController;

    public WishlistWin(DetailsWin detailsWin, Imodel model) {
        this.detailsWin = detailsWin;
        this.model = model;
        this.wishlistContentController = new WishlistContentController(this, model);

        setTitle("Wishlist");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Main panel
        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setBackground(Color.WHITE);

        // Scrollable list panel
        listPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        listPanel.setBackground(Color.WHITE);

        updateWishlist(wishlistContentController.getWishlist());

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel);
    }

    public void updateWishlist(Set<CarRecord> wishlist) {
        listPanel.removeAll();

        for (CarRecord car : wishlist) {
            listPanel.add(createCarPanel(car));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private JPanel createHeaderPanel() {
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1100, 50));
        headerPanel.setBackground(Color.WHITE);

        // "Remove All" panel
        JPanel removeAllPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        removeAllPanel.setBackground(Color.WHITE);

        // "Remove All" button
        JButton removeAllButton = new JButton("Remove All");
        removeAllButton.addActionListener(new WishlistRemoveAllActionListener(this, model, wishlistContentController));

        removeAllPanel.add(removeAllButton);

        headerPanel.add(removeAllPanel, BorderLayout.EAST);

        // Back panel
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(Color.WHITE);

        // Back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addMouseListener(new WishlistBackMouseListener(this, detailsWin));

        backPanel.add(backButton);

        headerPanel.add(backPanel, BorderLayout.WEST);

        return headerPanel;
    }

    private JPanel createCarPanel(CarRecord car) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBackground(Color.WHITE);
        rowPanel.setPreferredSize(new Dimension(850, 120));

        // Load car image
        JLabel carImageLabel = createCarImageLabel(car);

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

        // Remove button with confirmation
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new WishlistRemoveActionListener(this, model, car, wishlistContentController));

        rowPanel.add(carImageLabel, BorderLayout.WEST);
        rowPanel.add(carInfoPanel, BorderLayout.CENTER);
        rowPanel.add(removeButton, BorderLayout.EAST);

        return rowPanel;
    }

    private JLabel createCarImageLabel(CarRecord car) {
        try {
            // Get the imageUrl
            String makeFirstWord = car.make().split(" ")[0];
            String modelFirstWord = car.model().split(" ")[0];
            String imageUrl = "data/images/" + car.year() + makeFirstWord + modelFirstWord + ".jpg";

            ImageIcon imageIcon = new ImageIcon(imageUrl);
            Image image = imageIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
            ImageIcon fallbackIcon = new ImageIcon("data/images/default.jpg");
            Image fallbackImage = fallbackIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(fallbackImage));
        }
    }
}
