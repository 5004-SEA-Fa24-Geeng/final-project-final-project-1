package controller;

import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import view.WishlistWin;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Unit test for {@link WishlistRemoveAllController}.
 * Ensures correct removal of all cars from the wishlist.
 */
class WishlistRemoveAllControllerTest {

    /** Mocked wishlist window to prevent UI interactions. */
    private WishlistWin mockWishlistWin;

    /** Mocked data model handling wishlist operations. */
    private Imodel mockModel;

    /** Mocked wishlist content controller for refreshing UI. */
    private WishlistContentController mockWishlistContentController;

    /** Instance of WishlistRemoveAllController being tested. */
    private WishlistRemoveAllController wishlistRemoveAllController;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockWishlistWin = mock(WishlistWin.class);
        mockModel = mock(Imodel.class);
        mockWishlistContentController = mock(WishlistContentController.class);

        wishlistRemoveAllController = new WishlistRemoveAllController(
                mockWishlistWin, mockModel, mockWishlistContentController
        );
    }

    /**
     * Tests that all wishlist items are removed when the user confirms the action.
     */
    @Test
    void testRemoveAllFromWishlist_Confirmed() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            // Simulate user clicking "YES"
            mockedJOptionPane.when(() -> JOptionPane.showConfirmDialog(
                    any(), anyString(), anyString(), anyInt()
            )).thenReturn(JOptionPane.YES_OPTION);

            wishlistRemoveAllController.removeAllFromWishlist();

            verify(mockModel, times(1)).clearWishlist();
            verify(mockWishlistContentController, times(1)).refreshWishlist();
        }
    }

    /**
     * Tests that wishlist is NOT cleared when the user declines the confirmation dialog.
     */
    @Test
    void testRemoveAllFromWishlist_Canceled() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            // Simulate user clicking "NO"
            mockedJOptionPane.when(() -> JOptionPane.showConfirmDialog(
                    any(), anyString(), anyString(), anyInt()
            )).thenReturn(JOptionPane.NO_OPTION);

            wishlistRemoveAllController.removeAllFromWishlist();

            verify(mockModel, never()).clearWishlist();
            verify(mockWishlistContentController, never()).refreshWishlist();
        }
    }
}