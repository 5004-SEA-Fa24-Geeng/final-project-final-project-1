package controller;

import model.Imodel;
import view.DetailsWin;
import view.WishlistWin;

/**
 * Controls the transition from {@link DetailsWin} to {@link WishlistWin}.
 * Manages visibility settings to navigate between the details window and the wishlist window.
 */
public class WishlistWinController {

    /** Reference to the details window displaying a selected car's information. */
    private DetailsWin detailsWin;

    /** Reference to the data model containing car records and wishlist functionality. */
    private Imodel model;

    /**
     * Constructs the controller responsible for transitioning to the wishlist window.
     *
     * @param detailsWin The details window to be hidden when displaying the wishlist view.
     * @param model The data model handling car records and wishlist operations.
     */
    public WishlistWinController(DetailsWin detailsWin, Imodel model) {
        this.detailsWin = detailsWin;
        this.model = model;
    }

    /**
     * Displays the wishlist window.
     * Hides the details window and initializes {@link WishlistWin} with relevant data.
     */
    public void showWishlist() {
        detailsWin.setVisible(false);
        WishlistWin wishlistWin = new WishlistWin(detailsWin, model);
        wishlistWin.setVisible(true);
    }
}