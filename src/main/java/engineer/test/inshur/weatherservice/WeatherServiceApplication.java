package engineer.test.inshur.weatherservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import engineer.test.inshur.weatherservice.controller.WeatherController;
import engineer.test.inshur.weatherservice.controller.WeatherControllerAdvice;
import engineer.test.inshur.weatherservice.openweather.OpenWeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@SpringBootApplication
public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

	@Bean
	public WeatherService weatherService() {
		return new WeatherService(weatherClient());
	}

	@Value("${openweather.baseUri}") String apiKey;
	@Value("${openweather.baseUri}") String baseUri;
	@Bean
	public WeatherClient weatherClient() {
		return new OpenWeatherClient(apiKey, baseUri, restTemplate());
	}

	@Autowired RestTemplateBuilder restTemplateBuilder;
	@Bean
	public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper  = new ObjectMapper();
		mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY) );
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new JavaTimeModule());
		return mapper;
	}

}
