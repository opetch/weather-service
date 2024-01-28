package engineer.test.inshur.weatherservice.infrastructure.openweather;

import engineer.test.inshur.weatherservice.domain.ports.spi.ForecastRetrievalPort;
import engineer.test.inshur.weatherservice.domain.model.Forecast;
import engineer.test.inshur.weatherservice.domain.model.GeoCoordinate;
import engineer.test.inshur.weatherservice.domain.model.Period;
import engineer.test.inshur.weatherservice.domain.model.Temperature;
import org.springframework.web.client.RestTemplate;

public class OpenWeatherClient implements ForecastRetrievalPort {

    private final String apiKey;

    private final String baseUri;

    private final RestTemplate restTemplate;

    public OpenWeatherClient(String apiKey, String baseUri, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.baseUri = baseUri;
        this.restTemplate = restTemplate;
    }

    @Override
    public Forecast retrieveWeatherForecast(GeoCoordinate geoCoordinate) {
        var uri = String.format("%s/forecast?lat=%s&lon=%s&appid=%s", baseUri, geoCoordinate.getLatitude(), geoCoordinate.getLongitude(), apiKey);
        var response = restTemplate.getForObject(uri, ForecastResponse.class);
        return responseToForecast(response);
    }

    private Forecast responseToForecast(ForecastResponse response) {
        var builder = new Forecast.Builder();
        response.list.forEach(s -> builder.add(s.date_time.toLocalDate(), stepToPeriod(s)));
        return builder.build();
    }

    private static Period stepToPeriod(ForecastResponse.Step step) {
        return new Period(
                step.date_time.toLocalTime(),
                new Temperature(Temperature.Unit.KELVIN, step.main.temp_min),
                new Temperature(Temperature.Unit.KELVIN, step.main.temp_max),
                step.main.humidity);
    }
}
