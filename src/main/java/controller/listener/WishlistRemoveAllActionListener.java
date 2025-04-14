package controller.listener;

import model.Imodel;
import view.WishlistWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistRemoveAllActionListener implements ActionListener {
    private WishlistWin wishlistWin;
    private Imodel model;

    public WishlistRemoveAllActionListener(WishlistWin wishlistWin, Imodel model) {
        this.wishlistWin = wishlistWin;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
                wishlistWin, "Are you sure you want to remove all cars from the wishlist?",
                "Confirm Remove All", JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            model.clearWishlist();
            wishlistWin.refreshWishlist();
        }
    }
}
