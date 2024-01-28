package engineer.test.inshur.weatherservice.domain;

import java.time.LocalTime;

public class Period {

    private final LocalTime time;

    private final Temperature minimumTemperature;

    private final Temperature maximumTemperature;

    private final double humidity;

    public Period(LocalTime time, Temperature minimumTemperature, Temperature maximumTemperature, double humidity) {
        this.time = time;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.humidity = humidity;
    }

    public LocalTime getTime() {
        return time;
    }

    public Temperature getMinimumTemperature() {
        return minimumTemperature;
    }

    public Temperature getMaximumTemperature() {
        return maximumTemperature;
    }

    public double getHumidity() {
        return humidity;
    }
}
