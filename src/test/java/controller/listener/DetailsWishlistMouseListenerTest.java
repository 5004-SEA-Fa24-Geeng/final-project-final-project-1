package controller.listener;

import controller.WishlistWinController;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DetailsWin;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link DetailsWishlistMouseListener}.
 * Ensures that clicking triggers the display of the wishlist view.
 */
class DetailsWishlistMouseListenerTest {

    /** The listener instance under test. */
    private DetailsWishlistMouseListener detailsWishlistMouseListener;

    /** Mocked controller responsible for managing wishlist display logic. */
    private WishlistWinController wishlistWinController;

    /** Mocked details window instance. */
    private DetailsWin detailsWin;

    /** Mocked data model instance. */
    private Imodel model;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        detailsWin = mock(DetailsWin.class);
        model = mock(Imodel.class);
        wishlistWinController = mock(WishlistWinController.class);

        // Initialize listener instance (which normally creates its own controller)
        detailsWishlistMouseListener = new DetailsWishlistMouseListener(detailsWin, model);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = DetailsWishlistMouseListener.class.getDeclaredField("wishlistWinController");
        controllerField.setAccessible(true);
        controllerField.set(detailsWishlistMouseListener, wishlistWinController);
    }

    /**
     * Tests that a mouse click event correctly triggers the display of the wishlist view.
     */
    @Test
    void testMouseClicked_ShowsWishlist() {
        MouseEvent event = mock(MouseEvent.class);

        // Simulate mouse click event
        detailsWishlistMouseListener.mouseClicked(event);

        // Verify that the showWishlist method was invoked once
        verify(wishlistWinController, times(1)).showWishlist();
    }
}