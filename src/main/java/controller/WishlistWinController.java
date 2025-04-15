package controller;

import model.Imodel;
import view.DetailsWin;
import view.WishlistWin;

public class WishlistWinController {
    private DetailsWin detailsWin;

    private Imodel model;

    public WishlistWinController(DetailsWin detailsWin, Imodel model) {
        this.detailsWin = detailsWin;
        this.model = model;
    }

    public void showWishlist() {
        detailsWin.setVisible(false);
        WishlistWin wishlistWin = new WishlistWin(detailsWin, model);
        wishlistWin.setVisible(true);
    }
}
