package controller.listener;

import view.DetailsWin;
import view.WishlistWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WishlistBackMouseListener implements MouseListener {
    private WishlistWin wishlistWin;
    private DetailsWin detailsWin;

    public WishlistBackMouseListener(WishlistWin wishlistWin, DetailsWin detailsWin) {
        this.wishlistWin = wishlistWin;
        this.detailsWin = detailsWin;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wishlistWin.setVisible(false);
        detailsWin.setVisible(true);
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
