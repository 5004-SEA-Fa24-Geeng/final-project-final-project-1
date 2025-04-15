package controller;

import view.DetailsWin;
import view.WishlistWin;

public class WishlistBackController {
    private WishlistWin wishlistWin;

    private DetailsWin detailsWin;

    public WishlistBackController(WishlistWin wishlistWin, DetailsWin detailsWin) {
        this.wishlistWin = wishlistWin;
        this.detailsWin = detailsWin;
    }

    public void goBackToDetails() {
        wishlistWin.setVisible(false);
        detailsWin.setVisible(true);
    }
}
