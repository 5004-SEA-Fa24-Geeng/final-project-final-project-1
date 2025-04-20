package controller;

import model.CarRecord;
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

/**
 * Unit test for {@link WishlistRemoveController}.
 * Ensures correct removal of a single car from the wishlist.
 */
class WishlistRemoveControllerTest {

    /** Mocked wishlist window to prevent UI interactions. */
    private WishlistWin mockWishlistWin;

    /** Mocked data model handling wishlist operations. */
    private Imodel mockModel;

    /** Mocked wishlist content controller for refreshing UI. */
    private WishlistContentController mockWishlistContentController;

    /** Sample car record for testing removal. */
    private CarRecord mockCarRecord;

    /** Instance of WishlistRemoveController being tested. */
    private WishlistRemoveController wishlistRemoveController;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockWishlistWin = mock(WishlistWin.class);
        mockModel = mock(Imodel.class);
        mockWishlistContentController = mock(WishlistContentController.class);

        mockCarRecord = new CarRecord("1", 12000.00, "94043", 10000, "Ford",
                "Mustang", 2024, "Unknown", "2.4L", "Sedan",
                4, "AWD", "data/images/mustang.jpg");

        wishlistRemoveController = new WishlistRemoveController(
                mockWishlistWin, mockModel, mockCarRecord, mockWishlistContentController
        );
    }

    /**
     * Tests that a car is removed when the user confirms the action.
     */
    @Test
    void testRemoveFromWishlist_Confirmed() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            // Simulate user clicking "YES"
            mockedJOptionPane.when(() -> JOptionPane.showConfirmDialog(
                    any(), anyString(), anyString(), anyInt()
            )).thenReturn(JOptionPane.YES_OPTION);

            wishlistRemoveController.removeFromWishlist(mockCarRecord);

            verify(mockModel, times(1)).removeFromWishlist(mockCarRecord);
            verify(mockWishlistContentController, times(1)).refreshWishlist();
        }
    }

    /**
     * Tests that a car is NOT removed when the user declines the confirmation dialog.
     */
    @Test
    void testRemoveFromWishlist_Canceled() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            // Simulate user clicking "NO"
            mockedJOptionPane.when(() -> JOptionPane.showConfirmDialog(
                    any(), anyString(), anyString(), anyInt()
            )).thenReturn(JOptionPane.NO_OPTION);

            wishlistRemoveController.removeFromWishlist(mockCarRecord);

            verify(mockModel, never()).removeFromWishlist(mockCarRecord);
            verify(mockWishlistContentController, never()).refreshWishlist();
        }
    }
}