package engineer.test.inshur.weatherservice.openweather;

import static org.assertj.core.api.Assertions.assertThat;
import engineer.test.inshur.weatherservice.WeatherClient;
import engineer.test.inshur.weatherservice.domain.GeoCoordinate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringBootTest
public class OpenWeatherClientIntegrationTest {

    @Value("${openweather.baseUri}")
    String baseUri;

    @Value("${openweather.apiKey}")
    String apiKey;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void ensureWeCanMarshallValuesFromTheOpenWeatherForecastService() {
        WeatherClient client = new OpenWeatherClient(apiKey, baseUri, restTemplateBuilder.build());
        var londonBridge = new GeoCoordinate(51.507879, -0.087732);
        var forecast = client.retrieveWeatherForecast(londonBridge);
        int periodCount = forecast.getDays()
                .stream()
                .mapToInt(d -> d.getPeriods().size())
                .sum();
        assertThat(periodCount).isEqualTo(40);
    }
  
}