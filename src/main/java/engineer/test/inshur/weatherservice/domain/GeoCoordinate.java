package engineer.test.inshur.weatherservice.domain;

public class GeoCoordinate {

    private final double latitude;
    private final double longitude;

    public GeoCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
