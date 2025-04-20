package controller.listener;

import controller.WishlistBackController;
import view.DetailsWin;
import view.WishlistWin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for handling the mouse click event to navigate back from the wishlist window
 * to the details window.
 */
public class WishlistBackMouseListener implements MouseListener {

    /** Controller responsible for handling the navigation logic. */
    private WishlistBackController wishlistBackController;

    /**
     * Constructs a WishlistBackMouseListener with the given wishlist and details windows.
     * @param wishlistWin The wishlist window instance.
     * @param detailsWin The details window instance.
     */
    public WishlistBackMouseListener(WishlistWin wishlistWin, DetailsWin detailsWin) {
        this.wishlistBackController = new WishlistBackController(wishlistWin, detailsWin);
    }

    /**
     * Handles the mouse click event to trigger navigation back to details view.
     * @param e The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        wishlistBackController.goBackToDetails();
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
