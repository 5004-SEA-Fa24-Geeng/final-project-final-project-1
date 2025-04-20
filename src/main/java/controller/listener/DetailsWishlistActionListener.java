package controller.listener;

import controller.DetailsAddToWishlistController;
import model.CarRecord;
import model.Imodel;
import view.DetailsWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for handling the action event to add a car record to the wishlist
 * from the details window.
 */
public class DetailsWishlistActionListener implements ActionListener {

    /** Controller responsible for managing the addition of items to the wishlist. */
    private DetailsAddToWishlistController detailsAddToWishlistController;

    /**
     * Constructs a DetailsWishlistActionListener with the given details window, model, and car record.
     * @param detailsWin The details window instance.
     * @param model The data model instance.
     * @param carRecord The car record to be added to the wishlist.
     */
    public DetailsWishlistActionListener(DetailsWin detailsWin, Imodel model, CarRecord carRecord) {
        this.detailsAddToWishlistController = new DetailsAddToWishlistController(detailsWin, model, carRecord);
    }

    /**
     * Handles the action event to add the car record to the wishlist.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        detailsAddToWishlistController.addToWishlist();
    }
}
