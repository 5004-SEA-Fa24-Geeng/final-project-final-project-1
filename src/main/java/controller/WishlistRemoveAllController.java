package controller;

import model.Imodel;
import view.WishlistWin;

import javax.swing.*;

public class WishlistRemoveAllController {
    private WishlistWin wishlistWin;

    private Imodel model;

    private WishlistContentController wishlistContentController;

    public WishlistRemoveAllController(WishlistWin wishlistWin, Imodel model, WishlistContentController wishlistContentController) {
        this.wishlistWin = wishlistWin;
        this.model = model;
        this.wishlistContentController = wishlistContentController;
    }

    public void removeAllFromWishlist() {
        int confirm = JOptionPane.showConfirmDialog(
                wishlistWin, "Are you sure you want to remove all cars from the wishlist?",
                "Confirm Remove All", JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            model.clearWishlist();
            wishlistContentController.refreshWishlist();
        }
    }
}
