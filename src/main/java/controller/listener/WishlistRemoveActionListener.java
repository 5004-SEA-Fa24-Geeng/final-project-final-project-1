package controller.listener;

import controller.WishlistContentController;
import controller.WishlistRemoveController;
import model.CarRecord;
import model.Imodel;
import view.WishlistWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the removal of a single car from the wishlist.
 * Triggers the removal action when the button is clicked.
 */
public class WishlistRemoveActionListener implements ActionListener {

    /** Controller handling the removal of a specific wishlist item. */
    private WishlistRemoveController wishlistRemoveController;

    /** The car record to be removed. */
    private CarRecord carRecord;

    /**
     * Constructs the action listener for removing a car from the wishlist.
     * @param wishlistWin The wishlist window for UI updates.
     * @param model The data model managing wishlist items.
     * @param carRecord The car record to be removed.
     * @param wishlistContentController The controller handling wishlist refresh operations.
     */
    public WishlistRemoveActionListener(WishlistWin wishlistWin, Imodel model, CarRecord carRecord, WishlistContentController wishlistContentController) {
        this.wishlistRemoveController = new WishlistRemoveController(wishlistWin, model, carRecord, wishlistContentController);
        this.carRecord = carRecord;
    }

    /**
     * Handles the action event for removing a car from the wishlist.
     * Invokes the remove operation when triggered.
     * @param e The action event triggering the removal.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        wishlistRemoveController.removeFromWishlist(carRecord);
    }
}
