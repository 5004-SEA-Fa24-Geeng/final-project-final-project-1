package controller.listener;

import controller.WishlistContentController;
import controller.WishlistRemoveAllController;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.WishlistWin;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link WishlistRemoveAllActionListener}.
 * Verifies that clicking triggers the removal of all wishlist items.
 */
class WishlistRemoveAllActionListenerTest {

    /** The listener instance under test. */
    private WishlistRemoveAllActionListener wishlistRemoveAllActionListener;

    /** Mocked controller responsible for removing all wishlist items. */
    private WishlistRemoveAllController wishlistRemoveAllController;

    /** Mocked controller for handling wishlist content updates. */
    private WishlistContentController wishlistContentController;

    /** Mocked wishlist window instance. */
    private WishlistWin wishlistWin;

    /** Mocked data model instance. */
    private Imodel model;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        wishlistWin = mock(WishlistWin.class);
        model = mock(Imodel.class);
        wishlistContentController = mock(WishlistContentController.class);
        wishlistRemoveAllController = mock(WishlistRemoveAllController.class);

        // Initialize listener instance (which normally creates its own controller)
        wishlistRemoveAllActionListener = new WishlistRemoveAllActionListener(
                wishlistWin, model, wishlistContentController
        );

        // Use reflection to inject the mock controller into the listener
        Field controllerField = WishlistRemoveAllActionListener.class.getDeclaredField("wishlistRemoveAllController");
        controllerField.setAccessible(true);
        controllerField.set(wishlistRemoveAllActionListener, wishlistRemoveAllController);
    }

    /**
     * Tests that an action event correctly triggers the removal of all wishlist items.
     */
    @Test
    void testActionPerformed_RemovesAllWishlistItems() {
        ActionEvent event = mock(ActionEvent.class);

        // Simulate action event
        wishlistRemoveAllActionListener.actionPerformed(event);

        // Verify that the remove-all method was invoked once
        verify(wishlistRemoveAllController, times(1)).removeAllFromWishlist();
    }
}