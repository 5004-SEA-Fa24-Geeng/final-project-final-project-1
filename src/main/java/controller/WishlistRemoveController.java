package controller;

import model.CarRecord;
import model.Imodel;
import view.WishlistWin;

import javax.swing.*;

public class WishlistRemoveController {

    private WishlistWin wishlistWin;

    private Imodel model;

    private CarRecord carRecord;

    private WishlistContentController wishlistContentController;

    public WishlistRemoveController(WishlistWin wishlistWin, Imodel model, CarRecord carRecord, WishlistContentController wishlistContentController) {
        this.wishlistWin = wishlistWin;
        this.model = model;
        this.carRecord = carRecord;
        this.wishlistContentController = wishlistContentController;
    }

    public void removeFromWishlist(CarRecord carRecord) {
        int confirm = JOptionPane.showConfirmDialog(
                wishlistWin, "Are you sure you want to remove this car from the wishlist?",
                "Confirm Remove", JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            model.removeFromWishlist(carRecord);
            wishlistContentController.refreshWishlist();
        }
    }
}
