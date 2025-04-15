package controller.listener;

import controller.WishlistWinController;
import model.Imodel;
import view.DetailsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DetailsWishlistMouseListener implements MouseListener {
    private WishlistWinController wishlistWinController;

    public DetailsWishlistMouseListener(DetailsWin detailsWin, Imodel model) {
        this.wishlistWinController = new WishlistWinController(detailsWin, model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wishlistWinController.showWishlist();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
