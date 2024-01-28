package engineer.test.inshur.weatherservice;

public class WeatherService {
    
    private final WeatherClient client;

    public WeatherService(WeatherClient client) {
        this.client = client;
    }
    
}
