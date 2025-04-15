package controller.listener;

import controller.WishlistContentController;
import controller.WishlistRemoveController;
import model.CarRecord;
import model.Imodel;
import view.WishlistWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistRemoveActionListener implements ActionListener {
    private WishlistRemoveController wishlistRemoveController;
    private CarRecord carRecord;

    public WishlistRemoveActionListener(WishlistWin wishlistWin, Imodel model, CarRecord carRecord, WishlistContentController wishlistContentController) {
        this.wishlistRemoveController = new WishlistRemoveController(wishlistWin, model, carRecord, wishlistContentController);
        this.carRecord = carRecord;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wishlistRemoveController.removeFromWishlist(carRecord);
    }
}
