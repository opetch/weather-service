package engineer.test.inshur.weatherservice.domain.model;

public class GeoCoordinate {

    private final double latitude;
    private final double longitude;

    public GeoCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        validate();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private void validate() {
        if (decimalPlaces(latitude) > 6 || decimalPlaces(longitude) > 6) {
            throw new IllegalArgumentException("latitude & longitude must each have no more than 6 decimal places");
        }
    }

    private int decimalPlaces(double decimal) {
        var stringValue = String.valueOf(decimal);
        var split = stringValue.split("\\.");
        if (split.length == 1) {
            return 0;
        }
        return split[1].length();
    }
}
