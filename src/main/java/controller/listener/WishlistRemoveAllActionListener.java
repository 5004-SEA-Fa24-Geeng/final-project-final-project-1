package controller.listener;

import controller.WishlistContentController;
import controller.WishlistRemoveAllController;
import model.Imodel;
import view.WishlistWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistRemoveAllActionListener implements ActionListener {
    private WishlistRemoveAllController wishlistRemoveAllController;

    public WishlistRemoveAllActionListener(WishlistWin wishlistWin, Imodel model, WishlistContentController wishlistContentController) {
        this.wishlistRemoveAllController = new WishlistRemoveAllController(wishlistWin, model, wishlistContentController);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wishlistRemoveAllController.removeAllFromWishlist();
    }
}
