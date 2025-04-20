package controller;

import view.DetailsWin;
import view.WishlistWin;

/**
 * Controls the transition from {@link WishlistWin} back to {@link DetailsWin}.
 * Manages visibility settings to navigate between the wishlist window and the details window.
 */
public class WishlistBackController {

    /** Reference to the wishlist window that displays saved cars. */
    private WishlistWin wishlistWin;

    /** Reference to the details window displaying a selected car's information. */
    private DetailsWin detailsWin;

    /**
     * Constructs the controller responsible for handling navigation back to the details window.
     *
     * @param wishlistWin The wishlist window to be hidden when transitioning back to the details view.
     * @param detailsWin The details window to be made visible when returning from the wishlist view.
     */
    public WishlistBackController(WishlistWin wishlistWin, DetailsWin detailsWin) {
        this.wishlistWin = wishlistWin;
        this.detailsWin = detailsWin;
    }

    /**
     * Navigates from the wishlist window back to the details window.
     * Hides the wishlist window and makes the details window visible.
     */
    public void goBackToDetails() {
        wishlistWin.setVisible(false);
        detailsWin.setVisible(true);
    }
}