package controller.listener;

import controller.WishlistBackController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DetailsWin;
import view.WishlistWin;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link WishlistBackMouseListener}.
 * Verifies that clicking triggers navigation back to the details window.
 */
class WishlistBackMouseListenerTest {

    /** The listener instance under test. */
    private WishlistBackMouseListener wishlistBackMouseListener;

    /** Mocked controller responsible for navigation. */
    private WishlistBackController wishlistBackController;

    /** Mocked wishlist window instance. */
    private WishlistWin wishlistWin;

    /** Mocked details window instance. */
    private DetailsWin detailsWin;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        wishlistWin = mock(WishlistWin.class);
        detailsWin = mock(DetailsWin.class);
        wishlistBackController = mock(WishlistBackController.class);

        // Initialize listener instance
        wishlistBackMouseListener = new WishlistBackMouseListener(wishlistWin, detailsWin);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = WishlistBackMouseListener.class.getDeclaredField("wishlistBackController");
        controllerField.setAccessible(true);
        controllerField.set(wishlistBackMouseListener, wishlistBackController);
    }

    /**
     * Tests that a mouse click event correctly triggers navigation back to the details window.
     */
    @Test
    void testMouseClicked_NavigatesBackToDetails() {
        MouseEvent event = mock(MouseEvent.class);

        // Simulate mouse click event
        wishlistBackMouseListener.mouseClicked(event);

        // Verify that the mock controller's method was called
        verify(wishlistBackController, times(1)).goBackToDetails();
    }
}