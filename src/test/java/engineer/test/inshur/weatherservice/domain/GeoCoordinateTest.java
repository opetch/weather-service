package engineer.test.inshur.weatherservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoCoordinateTest {

    @Test
    public void latitudeAndLongitudeHaveSixDecimalPlaces() {
        new GeoCoordinate(0.123456, 0.123456);
    }

    @Test
    public void latitudeAndLongitudeHaveLessThanSixDecimalPlaces() {
        new GeoCoordinate(0.1, 0.12345);
    }

    @Test
    public void latitudeAndLongitudeHaveNoDecimalPlaces() {
        new GeoCoordinate(1, 0);
    }

    @Test
    public void latitudeHasSevenDecimalPlaces() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GeoCoordinate(0.1234567, 0.123456));
        assertEquals("latitude & longitude must each have no more than 6 decimal places", exception.getMessage());
    }

    @Test
    public void longitudeHasSevenDecimalPlaces() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GeoCoordinate(0.123456, 0.1234567));
        assertEquals("latitude & longitude must each have no more than 6 decimal places", exception.getMessage());
    }

}