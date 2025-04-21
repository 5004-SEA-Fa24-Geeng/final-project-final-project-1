package controller.listener;

import controller.WishlistContentController;
import controller.WishlistRemoveAllController;
import model.Imodel;
import view.WishlistWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the "Remove All" action in the wishlist window.
 * Triggers the removal of all wishlist items when the button is clicked.
 */
public class WishlistRemoveAllActionListener implements ActionListener {

    /** Controller handling the removal of all wishlist items. */
    private WishlistRemoveAllController wishlistRemoveAllController;

    /**
     * Constructs the action listener for removing all wishlist items.
     * @param wishlistWin The wishlist window for UI updates.
     * @param model The data model managing wishlist items.
     * @param wishlistContentController The controller handling wishlist refresh operations.
     */
    public WishlistRemoveAllActionListener(WishlistWin wishlistWin,
                                           Imodel model,
                                           WishlistContentController wishlistContentController) {
        this.wishlistRemoveAllController = new WishlistRemoveAllController(wishlistWin,
                model,
                wishlistContentController);
    }

    /**
     * Handles the action event for removing all wishlist items.
     * Invokes the remove-all operation when triggered.
     * @param e The action event triggering the removal.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        wishlistRemoveAllController.removeAllFromWishlist();
    }
}
