package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {
    private CarRecord car;
    @BeforeEach
    void setUp() {
        car = new CarRecord(
                "id123", 29999.99, "98052", 15000,
                "Subaru", "WRX", 2022, "Limited",
                "2.4L Turbo", "Sedan", 4, "AWD",
                "https://example.com/image.jpg"
        );
    }

    @Test
    void writeToCSV() {
        File file = new File("test_output.csv");
        Formatter.writeToCSV(Set.of(car), file.getPath());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void writeToJSON() {
        File file = new File("test_output.json");
        Formatter.writeToJSON(Set.of(car), file.getPath());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void writeToXML() {
        File file = new File("test_output.xml");
        Formatter.writeToXML(Set.of(car), file.getPath());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void writeWithCsvFormat() {
        File file = new File("test_output_dispatch.csv");
        Formatter.write(Set.of(car), "csv", file.getPath());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void writeWithJsonFormat() {
        File file = new File("test_output_dispatch.json");
        Formatter.write(Set.of(car), "json", file.getPath());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void writeWithXmlFormat() {
        File file = new File("test_output_dispatch.xml");
        Formatter.write(Set.of(car), "xml", file.getPath());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void getAndSetWishlist() {
        Formatter.CarRecordListWrapper wrapper = new Formatter.CarRecordListWrapper();
        wrapper.setWishlist(Set.of(car));
        assertEquals(1, wrapper.getWishlist().size());
        assertTrue(wrapper.getWishlist().contains(car));
    }
}