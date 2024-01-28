package engineer.test.inshur.weatherservice;

import engineer.test.inshur.weatherservice.domain.Forecast;
import engineer.test.inshur.weatherservice.domain.GeoCoordinate;

public interface WeatherClient {

    Forecast retrieveWeatherForecast(GeoCoordinate geoCoordinate);

}
