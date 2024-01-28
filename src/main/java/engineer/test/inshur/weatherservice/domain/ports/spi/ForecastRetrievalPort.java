package engineer.test.inshur.weatherservice.domain.ports.spi;

import engineer.test.inshur.weatherservice.domain.model.Forecast;
import engineer.test.inshur.weatherservice.domain.model.GeoCoordinate;

public interface ForecastRetrievalPort {

    Forecast retrieveWeatherForecast(GeoCoordinate geoCoordinate);

}
