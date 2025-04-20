package controller;

import model.CarRecord;
import model.Imodel;
import view.WishlistWin;

import java.util.Set;

/**
 * Manages interactions between the wishlist view and the data model.
 * Handles retrieval and refreshing of wishlist contents.
 */
public class WishlistContentController {

    /** Reference to the wishlist window for UI updates. */
    private WishlistWin wishlistWin;

    /** Reference to the data model storing wishlist items. */
    private Imodel model;

    /**
     * Constructs the wishlist content controller with required dependencies.
     * @param wishlistWin The wishlist window to update UI components.
     * @param model The data model managing wishlist items.
     */
    public WishlistContentController(WishlistWin wishlistWin, Imodel model) {
        this.wishlistWin = wishlistWin;
        this.model = model;
    }

    /**
     * Retrieves the current set of wishlist items.
     * @return A set of {@link CarRecord} representing saved cars.
     */
    public Set<CarRecord> getWishlist() {
        return model.getWishlist();
    }

    /**
     * Refreshes the wishlist UI with updated data.
     */
    public void refreshWishlist() {
        wishlistWin.updateWishlist(getWishlist());
    }
}
