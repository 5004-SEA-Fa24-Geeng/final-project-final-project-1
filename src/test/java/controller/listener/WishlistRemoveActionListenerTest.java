package controller.listener;

import controller.WishlistContentController;
import controller.WishlistRemoveController;
import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.WishlistWin;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link WishlistRemoveActionListener}.
 * Ensures that clicking triggers the removal of a single wishlist item.
 */
class WishlistRemoveActionListenerTest {

    /** The listener instance under test. */
    private WishlistRemoveActionListener wishlistRemoveActionListener;

    /** Mocked controller responsible for removing a specific wishlist item. */
    private WishlistRemoveController wishlistRemoveController;

    /** Mocked controller for handling wishlist content updates. */
    private WishlistContentController wishlistContentController;

    /** Mocked wishlist window instance. */
    private WishlistWin wishlistWin;

    /** Mocked data model instance. */
    private Imodel model;

    /** Mocked car record instance representing the item to be removed. */
    private CarRecord carRecord;

    /**
     * Sets up test dependencies before each test execution.
     */
    @BeforeEach
    void setUp() throws Exception {
        wishlistWin = mock(WishlistWin.class);
        model = mock(Imodel.class);
        carRecord = mock(CarRecord.class);
        wishlistContentController = mock(WishlistContentController.class);
        wishlistRemoveController = mock(WishlistRemoveController.class);

        // Initialize listener instance (which normally creates its own controller)
        wishlistRemoveActionListener = new WishlistRemoveActionListener(wishlistWin, model, carRecord, wishlistContentController);

        // Use reflection to inject the mock controller into the listener
        Field controllerField = WishlistRemoveActionListener.class.getDeclaredField("wishlistRemoveController");
        controllerField.setAccessible(true);
        controllerField.set(wishlistRemoveActionListener, wishlistRemoveController);
    }

    /**
     * Tests that an action event correctly triggers removal of the specified wishlist item.
     */
    @Test
    void testActionPerformed_RemovesWishlistItem() {
        ActionEvent event = mock(ActionEvent.class);

        // Simulate action event
        wishlistRemoveActionListener.actionPerformed(event);

        // Verify that the remove method was invoked once for the given car record
        verify(wishlistRemoveController, times(1)).removeFromWishlist(carRecord);
    }
}