package controller;

import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.WishlistWin;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link WishlistContentController}.
 * Ensures wishlist functionality works correctly.
 */
class WishlistContentControllerTest {

    /** Mocked wishlist window to prevent UI interactions. */
    private WishlistWin mockWishlistWin;

    /** Mocked data model handling wishlist operations. */
    private Imodel mockModel;

    /** Sample wishlist items for testing. */
    private Set<CarRecord> testWishlist;

    /** Instance of WishlistContentController being tested. */
    private WishlistContentController wishlistContentController;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockWishlistWin = mock(WishlistWin.class);
        mockModel = mock(Imodel.class);

        wishlistContentController = new WishlistContentController(mockWishlistWin, mockModel);

        // Initialize the in-memory test wishlist
        testWishlist = new HashSet<>();
        testWishlist.add(
                new CarRecord("1", 12000.00, "94043", 10000, "Ford", "Mustang",
                        2024, "Unknown", "2.4L", "Sedan", 4,
                        "AWD", "data/images/mustang.jpg")
        );
        testWishlist.add(
                new CarRecord("2", 1000.00, "98052", 15000, "Toyota", "Camry",
                        2022, "Limited", "2.4L Turbo", "Sedan", 4,
                        "AWD", "data/images/camry.jpg")
        );

        // Mock getWishlist() to return the test wishlist
        when(mockModel.getWishlist()).thenReturn(testWishlist);
    }

    /**
     * Tests that `refreshWishlist()` correctly updates the wishlist window.
     */
    @Test
    void testRefreshWishlist() {
        wishlistContentController.refreshWishlist();

        verify(mockWishlistWin, times(1)).updateWishlist(testWishlist);
    }
}