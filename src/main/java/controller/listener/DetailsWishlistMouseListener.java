package controller.listener;

import controller.WishlistWinController;
import model.Imodel;
import view.DetailsWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for handling the mouse click event to display the wishlist view from
 * the details window.
 */
public class DetailsWishlistMouseListener implements MouseListener {

    /** Controller responsible for managing the wishlist display logic. */
    private WishlistWinController wishlistWinController;

    /**
     * Constructs a DetailsWishlistMouseListener with the given details window and model.
     * @param detailsWin The details window instance.
     * @param model The data model instance.
     */
    public DetailsWishlistMouseListener(DetailsWin detailsWin, Imodel model) {
        this.wishlistWinController = new WishlistWinController(detailsWin, model);
    }

    /**
     * Handles the mouse click event to trigger the wishlist display.
     * @param e The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        wishlistWinController.showWishlist();
    }

    /** Handles mouse press event. */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /** Handles mouse release event. */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /** Handles mouse enter event. */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /** Handles mouse exit event. */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
