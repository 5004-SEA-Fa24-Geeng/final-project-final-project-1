package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CarRecordTest {

    CarRecord car;
    CarRecord identical;
    CarRecord notIdentical;

    @BeforeEach
    void setUp() {
        car = new CarRecord(
                "id123", 29999.99, "98052", 15000,
                "Subaru", "WRX", 2022, "Limited",
                "2.4L Turbo", "Sedan", 4, "AWD",
                "https://example.com/image.jpg"
        );
         identical = new CarRecord(
                "id123", 29999.99, "98052", 15000,
                "Subaru", "WRX", 2022, "Limited",
                "2.4L Turbo", "Sedan", 4, "AWD",
                "someImageThatDoesntMatter.jpg");
        notIdentical = new CarRecord(
                "id122", 29999.99, "98052", 15000,
                "Subaru", "WRX", 2022, "Limited",
                "2.4L Turbo", "Sedan", 4, "AWD",
                "someImageThatDoesntMatter.jpg"

        );
    }

    @Test
    void getTitle() {
        assertEquals( car.year() + " " + car.make() + " " + car.model() + " " + car.trim() + " " + car.driveType(), car.getTitle());
        assertNotEquals("car.year() + \" \" + car.make() + \" \" + car.model() + \" \" + car.trim() + \" \" + car.driveType()", car.getTitle());
    }

    @Test
    void testEquals() {

        assertNotEquals(car, notIdentical);
        assertEquals(car, identical);
        assertNotEquals(car.hashCode(), notIdentical.hashCode());
        assertEquals(car.hashCode(), identical.hashCode());

    }

    @Test
    void testHashCode() {
        assertNotEquals(car.hashCode(), notIdentical.hashCode());
        assertEquals(car.hashCode(), identical.hashCode());
    }

    @Test
    void id() {
        assertEquals("id123", car.id());
    }

    @Test
    void price() {
        assertEquals(29999.99, car.price());
    }

    @Test
    void zip() {
        assertEquals("98052", car.zip());
    }

    @Test
    void mileage() {
        assertEquals(15000, car.mileage());
    }

    @Test
    void make() {
        assertEquals("Subaru", car.make());
    }

    @Test
    void model() {
        assertEquals("WRX", car.model());
    }

    @Test
    void year() {
        assertEquals(2022, car.year());
    }

    @Test
    void trim() {
        assertEquals("Limited", car.trim());
    }

    @Test
    void engineInfo() {
        assertEquals("2.4L Turbo", car.engineInfo());
    }

    @Test
    void bodyType() {
        assertEquals("Sedan", car.bodyType());
    }

    @Test
    void numOfCylinders() {
        assertEquals(4, car.numOfCylinders());
    }

    @Test
    void driveType() {
        assertEquals("AWD", car.driveType());
    }

    @Test
    void imageUrl() {
        assertEquals("https://example.com/image.jpg", car.imageUrl());
    }
}