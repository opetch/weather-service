package engineer.test.inshur.weatherservice.domain;
public class Temperature {

    public enum Unit {
        KELVIN, CELSIUS, FAHRENHEIT;
    }

    private final Unit unit;

    private final double degrees;
    public Temperature(Unit unit, double degrees) {
        this.unit = unit;
        this.degrees = degrees;
    }

    public Unit getUnit() {
        return unit;
    }

    public double getDegrees() {
        return degrees;
    }
}
