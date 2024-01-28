package engineer.test.inshur.weatherservice.domain.ports.api;

import engineer.test.inshur.weatherservice.domain.model.Day;
import engineer.test.inshur.weatherservice.domain.model.GeoCoordinate;

public interface FindHottestDayUseCase {

    Day forecastHottestDay(GeoCoordinate geoCoordinate);

}
