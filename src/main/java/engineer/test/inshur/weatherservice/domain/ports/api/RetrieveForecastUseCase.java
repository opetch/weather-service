package engineer.test.inshur.weatherservice.domain.ports.api;

import engineer.test.inshur.weatherservice.domain.model.Forecast;
import engineer.test.inshur.weatherservice.domain.model.GeoCoordinate;

public interface RetrieveForecastUseCase {

    Forecast getForecast(GeoCoordinate geoCoordinate);

}
