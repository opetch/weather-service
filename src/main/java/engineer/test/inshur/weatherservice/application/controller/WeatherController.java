package engineer.test.inshur.weatherservice.application.controller;

import engineer.test.inshur.weatherservice.domain.service.WeatherService;
import engineer.test.inshur.weatherservice.domain.model.Day;
import engineer.test.inshur.weatherservice.domain.model.Forecast;
import engineer.test.inshur.weatherservice.domain.model.GeoCoordinate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping(value = "location/{latitude}/{longitude}/forecast")
    public Forecast getForecast(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude) {
        return service.getForecast(new GeoCoordinate(latitude, longitude));
    }

    @GetMapping(value = "location/{latitude}/{longitude}/forecast/hottestDay")
    public Day getForecastHottestDay(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude) {
        return service.forecastHottestDay(new GeoCoordinate(latitude, longitude));
    }

}
