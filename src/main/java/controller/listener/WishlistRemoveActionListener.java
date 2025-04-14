package controller.listener;

import model.CarRecord;
import model.Imodel;
import view.WishlistWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistRemoveActionListener implements ActionListener {
    private WishlistWin wishlistWin;
    private Imodel model;
    private CarRecord carRecord;

    public WishlistRemoveActionListener(WishlistWin wishlistWin, Imodel model, CarRecord carRecord) {
        this.wishlistWin = wishlistWin;
        this.model = model;
        this.carRecord = carRecord;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
                wishlistWin, "Are you sure you want to remove this car from the wishlist?",
                "Confirm Remove", JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            model.removeFromWishlist(carRecord);
            wishlistWin.refreshWishlist();
        }
    }
}
