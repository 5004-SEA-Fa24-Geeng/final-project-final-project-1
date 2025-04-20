package controller.listener;

import controller.DetailsAddToWishlistController;
import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DetailsWin;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link DetailsWishlistActionListener}.
 * Ensures that clicking triggers the addition of a car record to the wishlist.
 */
class DetailsWishlistActionListenerTest {

    /** The listener instance under test. */
    private DetailsWishlistActionListener detailsWishlistActionListener;

    /** Mocked controller responsible for adding items to the wishlist. */
    private DetailsAddToWishlistController detailsAddToWishlistController;

    /** Mocked details window instance. */
    private DetailsWin detailsWin;

    /** Mocked data model instance. */
    private Imodel model;

    /** Mocked car record representing the item to be added. */
    private CarRecord carRecord;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        detailsWin = mock(DetailsWin.class);
        model = mock(Imodel.class);
        carRecord = mock(CarRecord.class);
        detailsAddToWishlistController = mock(DetailsAddToWishlistController.class);

        // Initialize listener instance (which normally creates its own controller)
        detailsWishlistActionListener = new DetailsWishlistActionListener(detailsWin, model, carRecord);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = DetailsWishlistActionListener.class.getDeclaredField("detailsAddToWishlistController");
        controllerField.setAccessible(true);
        controllerField.set(detailsWishlistActionListener, detailsAddToWishlistController);
    }

    /**
     * Tests that an action event correctly triggers the addition of a car record to the wishlist.
     */
    @Test
    void testActionPerformed_AddsToWishlist() {
        ActionEvent event = mock(ActionEvent.class);

        // Simulate action event
        detailsWishlistActionListener.actionPerformed(event);

        // Verify that the add-to-wishlist method was invoked once for the given car record
        verify(detailsAddToWishlistController, times(1)).addToWishlist();
    }
}