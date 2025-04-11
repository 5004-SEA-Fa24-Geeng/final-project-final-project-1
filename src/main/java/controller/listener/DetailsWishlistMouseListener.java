package controller.listener;

import model.Imodel;
import view.DetailsWin;
import view.WishlistWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DetailsWishlistMouseListener implements MouseListener {
    private DetailsWin detailsWin;
    private Imodel model;

    public DetailsWishlistMouseListener(DetailsWin detailsWin, Imodel model) {
        this.detailsWin = detailsWin;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        detailsWin.setVisible(false);

        WishlistWin wishlistWin = new WishlistWin(detailsWin, model);
        wishlistWin.setVisible(true);
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
