package controller;

import model.CarRecord;
import model.Imodel;
import view.WishlistWin;

import java.util.Set;

public class WishlistContentController {
    private WishlistWin wishlistWin;
    private Imodel model;

    public WishlistContentController(WishlistWin wishlistWin, Imodel model) {
        this.wishlistWin = wishlistWin;
        this.model = model;
    }

    public Set<CarRecord> getWishlist() {
        return model.getWishlist();
    }

    public void refreshWishlist() {
        wishlistWin.updateWishlist(getWishlist());
    }
}
