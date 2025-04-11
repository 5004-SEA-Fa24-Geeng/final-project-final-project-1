package controller.listener;

import model.CarRecord;
import model.Imodel;
import view.DetailsWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsWishlistActionListener implements ActionListener {
    private DetailsWin detailsWin;
    private Imodel model;
    private CarRecord carRecord;

    public DetailsWishlistActionListener(DetailsWin detailsWin, Imodel model, CarRecord carRecord) {
        this.detailsWin = detailsWin;
        this.model = model;
        this.carRecord = carRecord;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.addToWishlist(carRecord);

        model.saveWishlist();

//        System.out.println("Added to wish list");

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
        Timer timer = new Timer(1500, evt -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
    }
}
