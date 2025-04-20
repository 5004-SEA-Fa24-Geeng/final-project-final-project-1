package controller;

import model.CarRecord;
import model.Imodel;
import view.DetailsWin;

import javax.swing.*;
import java.awt.*;

/**
 * Manages adding a car to the wishlist and displaying a confirmation dialog.
 */
public class DetailsAddToWishlistController {

    /** Reference to the details window. */
    private DetailsWin detailsWin;

    /** Reference to the data model handling car records. */
    private Imodel model;

    /** The car record being added to the wishlist. */
    private CarRecord carRecord;

    /**
     * Constructs the wishlist controller with necessary dependencies.
     * @param detailsWin The details window displaying the car.
     * @param model The data model handling wishlist operations.
     * @param carRecord The car record to be added to the wishlist.
     */
    public DetailsAddToWishlistController(DetailsWin detailsWin, Imodel model, CarRecord carRecord) {
        this.detailsWin = detailsWin;
        this.model = model;
        this.carRecord = carRecord;
    }

    /**
     * Adds the selected car to the wishlist and displays a pop-up confirmation.
     */
    public void addToWishlist() {
        model.addToWishlist(carRecord);
        model.saveWishlist();

        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    detailsWin,
                    "Added to Wishlist!",
                    "Wishlist Confirmation",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
}
