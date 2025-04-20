package controller;

import model.CarRecord;
import model.Imodel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link SearchController}.
 * Validates searching behavior for different inputs.
 */
class SearchControllerTest {

    /** Mocked model to isolate search logic. */
    private Imodel mockModel;

    /** Instance of SearchController being tested. */
    private SearchController searchController;

    /** Simulated list of all car records available in the dataset. */
    private List<CarRecord> allCars;

    /** Simulated list of car records filtered by the Toyota make. */
    private List<CarRecord> toyotaCars;

    /** Simulated list of Toyota car records filtered by the Corolla model. */
    private List<CarRecord> toyotaCorollaCars;

    /**
     * Sets up mock objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockModel = mock(Imodel.class);
        searchController = new SearchController(mockModel);

        // Mock car records
        allCars = List.of(
                new CarRecord("1", 12000.00, "94043", 10000, "Ford", "Mustang",
                        2024, "Unknown", "2.4L", "Sedan", 4,
                        "AWD", "data/images/mustang.jpg"),
                new CarRecord("2", 1000.00, "98052", 15000, "Toyota", "Camry",
                        2022, "Limited", "2.4L Turbo", "Sedan", 4,
                        "AWD", "data/images/camry.jpg"),
                new CarRecord("3", 28000.00, "98102", 200000, "Toyota", "Corolla",
                        2000, "Limited", "2.4L", "Sedan", 4,
                        "AWD", "data/images/corolla.jpg")
        );

        toyotaCars = List.of(
                new CarRecord("2", 1000.00, "98052", 15000, "Toyota", "Camry",
                        2022, "Limited", "2.4L Turbo", "Sedan", 4,
                        "AWD", "data/images/camry.jpg"),
                new CarRecord("3", 28000.00, "98102", 200000, "Toyota", "Corolla",
                        2000, "Limited", "2.4L", "Sedan", 4,
                        "AWD", "data/images/corolla.jpg")
        );

        toyotaCorollaCars = List.of(
                new CarRecord("3", 28000.00, "98102", 200000, "Toyota", "Corolla",
                        2000, "Limited", "2.4L", "Sedan", 4,
                        "AWD", "data/images/corolla.jpg")
        );

        when(mockModel.getAllCars()).thenReturn(allCars);
        when(mockModel.getCarsByMake("Toyota")).thenReturn(toyotaCars);
        when(mockModel.getCarsByMakeAndModel("Toyota", "Corolla")).thenReturn(toyotaCorollaCars);
    }

    /**
     * Tests searching all cars when no make is selected.
     */
    @Test
    void testSearchAllCars() {
        List<CarRecord> result = searchController.searchCars("Select", "Select");
        assertEquals(3, result.size());
        assertEquals(allCars, result);
    }

    /**
     * Tests filtering cars by make.
     */
    @Test
    void testSearchByMake() {
        List<CarRecord> result = searchController.searchCars("Toyota", "Select");
        assertEquals(2, result.size());
        assertEquals(toyotaCars, result);
    }

    /**
     * Tests filtering cars by both make and model.
     */
    @Test
    void testSearchByMakeAndModel() {
        List<CarRecord> result = searchController.searchCars("Toyota", "Corolla");
        assertEquals(1, result.size());
        assertEquals(toyotaCorollaCars, result);
    }
}