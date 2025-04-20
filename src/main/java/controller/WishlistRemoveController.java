package controller;

import model.CarRecord;
import model.Imodel;
import view.WishlistWin;

import javax.swing.*;

/**
 * Handles the removal of a single car from the wishlist.
 * Displays a confirmation prompt before deletion.
 */
public class WishlistRemoveController {

    /** Reference to the wishlist window for UI updates. */
    private WishlistWin wishlistWin;

    /** Reference to the data model managing wishlist items. */
    private Imodel model;

    /** The car record to be removed. */
    private CarRecord carRecord;

    /** Controller for managing wishlist content updates. */
    private WishlistContentController wishlistContentController;

    /**
     * Constructs the wishlist remove controller with necessary dependencies.
     * @param wishlistWin The wishlist window to update UI components.
     * @param model The data model managing wishlist items.
     * @param carRecord The car record to be removed.
     * @param wishlistContentController The controller handling wishlist refresh operations.
     */
    public WishlistRemoveController(WishlistWin wishlistWin, Imodel model, CarRecord carRecord, WishlistContentController wishlistContentController) {
        this.wishlistWin = wishlistWin;
        this.model = model;
        this.carRecord = carRecord;
        this.wishlistContentController = wishlistContentController;
    }

    /**
     * Removes a car from the wishlist after user confirmation.
     * @param carRecord The car record to remove.
     */
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
