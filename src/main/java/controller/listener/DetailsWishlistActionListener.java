package controller.listener;

import controller.DetailsAddToWishlistController;
import model.CarRecord;
import model.Imodel;
import view.DetailsWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsWishlistActionListener implements ActionListener {
    private DetailsAddToWishlistController detailsAddToWishlistController;

    public DetailsWishlistActionListener(DetailsWin detailsWin, Imodel model, CarRecord carRecord) {
        this.detailsAddToWishlistController = new DetailsAddToWishlistController(detailsWin, model, carRecord);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        detailsAddToWishlistController.addToWishlist();
    }
}
