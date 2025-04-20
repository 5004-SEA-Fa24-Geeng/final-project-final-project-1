package controller;

import model.Imodel;
import view.WishlistWin;

import javax.swing.*;

/**
 * Handles the removal of all cars from the wishlist.
 * Displays a confirmation prompt before clearing the list.
 */
public class WishlistRemoveAllController {

    /** Reference to the wishlist window for UI updates. */
    private WishlistWin wishlistWin;

    /** Reference to the data model managing wishlist items. */
    private Imodel model;

    /** Controller for managing wishlist content updates. */
    private WishlistContentController wishlistContentController;

    /**
     * Constructs the wishlist remove-all controller with necessary dependencies.
     * @param wishlistWin The wishlist window to update UI components.
     * @param model The data model managing wishlist items.
     * @param wishlistContentController The controller handling wishlist refresh operations.
     */
    public WishlistRemoveAllController(WishlistWin wishlistWin, Imodel model, WishlistContentController wishlistContentController) {
        this.wishlistWin = wishlistWin;
        this.model = model;
        this.wishlistContentController = wishlistContentController;
    }

    /**
     * Removes all cars from the wishlist after user confirmation.
     */
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
