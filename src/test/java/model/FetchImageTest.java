package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FetchImageTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void fetchUrl() {
        String url = FetchImage.fetchUrl("Subaru", "WRX", 2022);
        assertNotNull(url);
    }
}