package controller;

import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import view.DetailsWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link DetailsAddToWishlistController}.
 * Ensures wishlist functionality and UI confirmation.
 */
class DetailsAddToWishlistControllerTest {

    /** Mocked details window instance. */
    private DetailsWin detailsWin;

    /** Mocked data model instance. */
    private Imodel model;

    /** Mocked car record instance. */
    private CarRecord carRecord;

    /** The controller instance under test. */
    private DetailsAddToWishlistController controller;

    /** Mocked details window to prevent UI interactions. */
    private DetailsWin mockDetailsWin;

    /** Mocked data model handling wishlist operations. */
    private Imodel mockModel;

    /** Sample car record for testing wishlist functionality. */
    private CarRecord mockCarRecord;

    /** In-memory wishlist for testing. */
    private Set<CarRecord> testWishlist;

    /** Instance of DetailsAddToWishlistController being tested. */
    private DetailsAddToWishlistController detailsAddToWishlistController;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockDetailsWin = mock(DetailsWin.class);
        mockModel = mock(Imodel.class);
        mockCarRecord = new CarRecord("1", 12000.00, "94043", 10000, "Ford",
                "Mustang", 2024, "Unknown", "2.4L", "Sedan",
                4, "AWD", "data/images/mustang.jpg");

        testWishlist = new HashSet<>();

        detailsAddToWishlistController = new DetailsAddToWishlistController(mockDetailsWin, mockModel, mockCarRecord);

        // Simulate wishlist behavior
        doAnswer(invocation -> {
            testWishlist.add(mockCarRecord);
            return null;
        }).when(mockModel).addToWishlist(mockCarRecord);

        // Prevent real file interactions
        doNothing().when(mockModel).saveWishlist();
    }

    /**
     * Tests that the car is correctly added to the wishlist without modifying actual files.
     */
    @Test
    void testAddToWishlist() {
        detailsAddToWishlistController.addToWishlist();

        // Verify that model methods were called correctly
        verify(mockModel, times(1)).addToWishlist(mockCarRecord);
        verify(mockModel, times(1)).saveWishlist();

        // Ensures the captured car is the correct record
        assertTrue(testWishlist.contains(mockCarRecord));
    }
}