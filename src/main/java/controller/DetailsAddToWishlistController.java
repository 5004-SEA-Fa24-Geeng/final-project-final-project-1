package controller;

import model.CarRecord;
import model.Imodel;
import view.DetailsWin;

import javax.swing.*;
import java.awt.*;

public class DetailsAddToWishlistController {

    private DetailsWin detailsWin;

    private Imodel model;

    private CarRecord carRecord;

    public DetailsAddToWishlistController(DetailsWin detailsWin, Imodel model, CarRecord carRecord) {
        this.detailsWin = detailsWin;
        this.model = model;
        this.carRecord = carRecord;
    }

    public void addToWishlist() {
        model.addToWishlist(carRecord);
        model.saveWishlist();

        // Create pop-up dialog
        JDialog dialog = new JDialog(detailsWin, "Wishlist", false);
        dialog.setUndecorated(true);
        dialog.setSize(380, 140);
        dialog.setLocationRelativeTo(detailsWin);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel messageLabel = new JLabel("added to wish list!");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(messageLabel, gbc);
        dialog.add(panel);
        dialog.setVisible(true);

        // Close dialog after some time
        Timer timer = new Timer(800, evt -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
    }
}
