package controller.listener;

import controller.WishlistBackController;
import view.DetailsWin;
import view.WishlistWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WishlistBackMouseListener implements MouseListener {
    private WishlistBackController wishlistBackController;

    public WishlistBackMouseListener(WishlistWin wishlistWin, DetailsWin detailsWin) {
        this.wishlistBackController = new WishlistBackController(wishlistWin, detailsWin);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wishlistBackController.goBackToDetails();
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
