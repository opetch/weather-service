package engineer.test.inshur.weatherservice.infrastructure.openweather;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

class ForecastResponse {

    static class Step {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty("dt_txt")
        LocalDateTime date_time;
        Main main;
    }

    static class Main {
       double temp_min;
       double temp_max;
       double humidity;
    }

    List<Step> list;
}
