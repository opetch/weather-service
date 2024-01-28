package engineer.test.inshur.weatherservice.domain.service;

import engineer.test.inshur.weatherservice.domain.ports.api.FindHottestDayUseCase;
import engineer.test.inshur.weatherservice.domain.ports.api.RetrieveForecastUseCase;
import engineer.test.inshur.weatherservice.domain.ports.spi.ForecastRetrievalPort;
import engineer.test.inshur.weatherservice.domain.model.Day;
import engineer.test.inshur.weatherservice.domain.model.Forecast;
import engineer.test.inshur.weatherservice.domain.model.GeoCoordinate;

public class WeatherService implements FindHottestDayUseCase, RetrieveForecastUseCase {
    
    private final ForecastRetrievalPort client;

    public WeatherService(ForecastRetrievalPort client) {
        this.client = client;
    }

    public Forecast getForecast(GeoCoordinate geoCoordinate) {
        return client.retrieveWeatherForecast(geoCoordinate);
    }

    public Day forecastHottestDay(GeoCoordinate geoCoordinate) {
        return client.retrieveWeatherForecast(geoCoordinate).hottestDay().get();
    }
    
}
