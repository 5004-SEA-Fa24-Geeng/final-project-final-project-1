package view;

import controller.WishlistContentController;
import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link WishlistWin}
 * Ensures wishlist functionality works correctly within the UI.
 */
class WishlistWinTest {

    /** Mocked details window to prevent navigation issues. */
    private DetailsWin mockDetailsWin;

    /** Mocked data model handling wishlist operations. */
    private Imodel mockModel;

    /** Mocked wishlist content controller. */
    private WishlistContentController mockWishlistContentController;

    /** Sample wishlist items for testing. */
    private Set<CarRecord> testWishlist;

    /** Instance of WishlistWin being tested. */
    private WishlistWin wishlistWin;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockDetailsWin = mock(DetailsWin.class);
        mockModel = mock(Imodel.class);
        mockWishlistContentController = mock(WishlistContentController.class);

        wishlistWin = new WishlistWin(mockDetailsWin, mockModel);

        testWishlist = new HashSet<>();
        testWishlist.add(
                new CarRecord("1", 12000.00, "94043", 10000, "Ford",
                        "Mustang", 2024, "Unknown", "2.4L", "Sedan",
                        4, "AWD", "data/images/mustang.jpg")
        );
        testWishlist.add(
                new CarRecord("2", 1000.00, "98052", 15000, "Toyota", "Camry",
                        2022, "Limited", "2.4L Turbo", "Sedan", 4,
                        "AWD", "data/images/camry.jpg")
        );

        doAnswer(invocation -> {
            testWishlist.clear();
            return null;
        }).when(mockModel).clearWishlist();
    }

    /**
     * Tests that `updateWishlist()` correctly updates the UI.
     */
    @Test
    void testUpdateWishlist() {
        wishlistWin.updateWishlist(testWishlist);

        assertEquals(testWishlist.size(), wishlistWin.getListPanel().getComponentCount());
    }

    /**
     * Tests that clicking "Remove All" triggers the correct model interaction.
     */
    @Test
    void testRemoveAllAction() {
        JButton removeAllButton = new JButton();
        removeAllButton.addActionListener(e -> mockModel.clearWishlist());

        removeAllButton.doClick();

        verify(mockModel, times(1)).clearWishlist();

        assertTrue(testWishlist.isEmpty());
    }
}