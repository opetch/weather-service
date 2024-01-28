package engineer.test.inshur.weatherservice;

public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }
}
