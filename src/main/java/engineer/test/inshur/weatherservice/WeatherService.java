package engineer.test.inshur.weatherservice;

import engineer.test.inshur.weatherservice.domain.Day;
import engineer.test.inshur.weatherservice.domain.Forecast;
import engineer.test.inshur.weatherservice.domain.GeoCoordinate;

public class WeatherService {
    
    private final WeatherClient client;

    public WeatherService(WeatherClient client) {
        this.client = client;
    }

    public Forecast getForecast(GeoCoordinate geoCoordinate) {
        return client.retrieveWeatherForecast(geoCoordinate);
    }

    public Day forecastHottestDay(GeoCoordinate geoCoordinate) {
        return client.retrieveWeatherForecast(geoCoordinate).hottestDay().get();
    }
    
}
