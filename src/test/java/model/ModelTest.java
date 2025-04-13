package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private Model model;
    private CarRecord car;

    @BeforeEach
    void setUp() {
        car = new CarRecord(
                "id123", 29999.99, "98052", 15000,
                "Subaru", "WRX", 2022, "Limited",
                "2.4L Turbo", "Sedan", 4, "AWD",
                "test.jpg"
        );

        model = new Model();
        model.setAllCars(List.of(car));
        model.setWishList(new HashSet<>());
        model.resetWorkingList();
    }

    @Test
    void getAllMakes() {
        assertTrue(model.getAllMakes().contains("Subaru"));
    }

    @Test
    void getModelsByMake() {
        assertTrue(model.getModelsByMake("Subaru").contains("WRX"));
    }

    @Test
    void getAllCars() {
        assertEquals(1, model.getAllCars().size());
    }

    @Test
    void getCarsByMake() {
        assertEquals(1, model.getCarsByMake("Subaru").size());
    }

    @Test
    void getCarsByMakeAndModel() {
        assertEquals(1, model.getCarsByMakeAndModel("Subaru", "WRX").size());
    }

    @Test
    void getWorkingList() {
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void resetWorkingList() {
        model.resetWorkingList();
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByMake() {
        model.filterByMake("Subaru");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByModel() {
        model.filterByModel("WRX");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByPrice() {
        model.filterByPrice(30000, false);
        assertEquals(1, model.getWorkingList().size());

        model.filterByPrice(20000, true);
        assertEquals(1, model.getWorkingList().size());

        model.filterByPrice(40000, true);
        assertEquals(0, model.getWorkingList().size());
    }

    @Test
    void filterByMileage() {
        model.filterByMileage(10000, true);
        assertEquals(1, model.getWorkingList().size());

        model.filterByMileage(16000, true);
        assertEquals(0, model.getWorkingList().size());
    }

    @Test
    void filterByTrim() {
        model.filterByTrim("Limited");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByYear() {
        model.filterByYear(2020, true);
        assertEquals(1, model.getWorkingList().size());

        model.filterByYear(2023, true);
        assertEquals(0, model.getWorkingList().size());
    }

    @Test
    void filterByDriveType() {
        model.filterByDriveType("AWD");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByBodyType() {
        model.filterByBodyType("Sedan");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByNumOfCylinder() {
        model.filterByNumOfCylinder(4);
        assertEquals(1, model.getWorkingList().size());

        model.filterByNumOfCylinder(6);
        assertEquals(0, model.getWorkingList().size());
    }

    @Test
    void sortByYear() {
        model.sortByYear(false);
        assertEquals(2022, model.getWorkingList().get(0).year());
    }

    @Test
    void sortByMileage() {
        model.sortByMileage(false);
        assertEquals(15000, model.getWorkingList().get(0).mileage());
    }

    @Test
    void sortByPrice() {
        model.sortByPrice(false);
        assertEquals(29999.99, model.getWorkingList().get(0).price());
    }

    @Test
    void addToWishlist() {
        model.addToWishlist(car);
        assertTrue(model.getWishlist().contains(car));
    }

    @Test
    void removeFromWishlist() {
        model.addToWishlist(car);
        model.removeFromWishlist(car);
        assertFalse(model.getWishlist().contains(car));
    }

    @Test
    void getWishlist() {
        model.addToWishlist(car);
        assertEquals(1, model.getWishlist().size());
    }

    @Test
    void clearWishlist() {
        model.addToWishlist(car);
        model.clearWishlist();
        assertTrue(model.getWishlist().isEmpty());
    }

    @Test
    void saveWishlist() {
        // just check it doesn’t crash
        assertDoesNotThrow(() -> model.saveWishlist());
    }

    @Test
    void exportWishlist() {
        assertDoesNotThrow(() -> model.exportWishlist("csv"));
        assertDoesNotThrow(() -> model.exportWishlist("json"));
        assertDoesNotThrow(() -> model.exportWishlist("xml"));
        assertDoesNotThrow(() -> model.exportWishlist("invalid"));
    }

    @Test
    void setAllCars() {
        List<CarRecord> cars = List.of(car);
        model.setAllCars(cars);
        assertEquals(cars, model.getAllCars());
    }

    @Test
    void setWishList() {
        Set<CarRecord> wishlist = new HashSet<>();
        wishlist.add(car);
        model.setWishList(wishlist);
        assertEquals(wishlist, model.getWishlist());
    }

    @Test
    void filterByMakeCaseInsensitive() {
        model.filterByMake("subaru");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByModelCaseInsensitive() {
        model.filterByModel("wrx");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByTrimCaseInsensitive() {
        model.filterByTrim("limited");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByDriveTypeCaseInsensitive() {
        model.filterByDriveType("awd");
        assertEquals(1, model.getWorkingList().size());
    }

    @Test
    void filterByBodyTypeCastInsensitive() {
        model.filterByBodyType("sedan");
        assertEquals(1, model.getWorkingList().size());
    }
    @Test
    void exportWishlist_emptyList() {
        model.clearWishlist();
        assertDoesNotThrow(() -> model.exportWishlist("csv"));
        assertDoesNotThrow(() -> model.exportWishlist("json"));
        assertDoesNotThrow(() -> model.exportWishlist("xml"));
    }
    @Test
    void sortByYear_multipleCars() {
        CarRecord newer = new CarRecord("id456", 30999.99, "98052", 14000,
                "Subaru", "WRX", 2023, "Limited", "2.4L Turbo", "Sedan", 4, "AWD", "test.jpg");

        model.setAllCars(List.of(car, newer));
        model.resetWorkingList();
        model.sortByYear(true); // descending
        assertEquals(2023, model.getWorkingList().get(0).year());
    }
    @Test
    void filterByNonExistingMake() {
        model.filterByMake("Ford");
        assertEquals(0, model.getWorkingList().size());
    }
    @Test
    void sortByMileageThenPrice() {
        model.sortByMileage(false);
        model.sortByPrice(true);
        assertEquals(29999.99, model.getWorkingList().get(0).price());
    }
    @Test
    void filterByPriceEqualsBoundary() {
        model.filterByPrice(29999.99, true);
        assertEquals(1, model.getWorkingList().size());

        model.resetWorkingList();
        model.filterByPrice(29999.99, false);
        assertEquals(1, model.getWorkingList().size());
    }
    @Test
    void filterByMileageEqualsBoundary() {
        model.filterByMileage(15000, true);
        assertEquals(1, model.getWorkingList().size());

        model.resetWorkingList();
        model.filterByMileage(15000, false);
        assertEquals(1, model.getWorkingList().size());
    }
    @Test
    void filterByYearEqualsBoundary() {
        model.filterByYear(2022, true);
        assertEquals(1, model.getWorkingList().size());

        model.resetWorkingList();
        model.filterByYear(2022, false);
        assertEquals(1, model.getWorkingList().size());
    }
    @Test
    void sortByYearBothOrders() {
        model.sortByYear(false);
        assertEquals(2022, model.getWorkingList().get(0).year());

        model.sortByYear(true);
        assertEquals(2022, model.getWorkingList().get(0).year());
    }
    @Test
    void exportWishlistInvalidFormat() {
        assertDoesNotThrow(() -> model.exportWishlist("unsupported_format"));
    }
    @Test
    void getCarsByMakeAndModelCaseInsensitive() {
        List<CarRecord> result = model.getCarsByMakeAndModel("subaru", "wrx");
        assertEquals(1, result.size());
    }
    @Test
    void getModelsByMakeCaseInsensitive() {
        Set<String> result = model.getModelsByMake("SUBARU");
        assertTrue(result.contains("WRX"));
    }






}
