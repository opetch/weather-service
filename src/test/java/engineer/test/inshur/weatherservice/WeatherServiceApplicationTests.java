package engineer.test.inshur.weatherservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

//	@Autowired
//	WebTestClient webTestClient;
//
//	@Test
//	public void callHottestDayEndpoint() {
//		webTestClient.get()
//				.uri("location/51.507879,-0.087732/forecast/hottestDay")
//				.exchange()
//				.expectStatus().is2xxSuccessful();
//	}

}
