package engineer.test.inshur.weatherservice.domain.model;

import engineer.test.inshur.weatherservice.domain.model.Day;
import engineer.test.inshur.weatherservice.domain.model.Forecast;
import engineer.test.inshur.weatherservice.domain.model.Period;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static engineer.test.inshur.weatherservice.domain.model.Temperature.kelvin;
import static org.assertj.core.api.Assertions.assertThat;

class ForecastTest {

    private static final int DEFAULT_MIN_TEMP = 280;
    private static final int DEFAULT_MAX_TEMP = 281;
    private static final int DEFAULT_HUMIDITY = 80;

    @Test
    public void hottestDay_onePeriodInOneDayHasHigherMinMaxTempThanOthers() {
        final var startDate = LocalDate.of(2024, 1, 1);
        var forecast = defaultForecast(startDate, 5);

        // replace midday period on third day
        var newDay = new Day(startDate.plusDays(2), defaultDay());
        newDay.getPeriods().set(4, new Period(LocalTime.of(12, 0), kelvin(DEFAULT_MIN_TEMP + 2), kelvin(DEFAULT_MAX_TEMP + 2), DEFAULT_HUMIDITY));
        forecast.getDays().set(2, newDay);

        final var hottestDay = forecast.hottestDay();
        assertThat(hottestDay.isPresent()).isTrue();
        assertThat(hottestDay.get().getDate()).isEqualTo(startDate.plusDays(2));
    }

    @Test
    public void hottestDay_onePeriodInOneDayHasHigherMinTempThanOthers() {
        final var startDate = LocalDate.of(2024, 1, 1);
        var forecast = defaultForecast(startDate, 5);

        // replace midday period on third day
        var newDay = new Day(startDate.plusDays(2), defaultDay());
        newDay.getPeriods().set(4, new Period(LocalTime.of(12, 0), kelvin(DEFAULT_MIN_TEMP + 2), kelvin(DEFAULT_MAX_TEMP), DEFAULT_HUMIDITY));
        forecast.getDays().set(2, newDay);

        final var hottestDay = forecast.hottestDay();
        assertThat(hottestDay.isPresent()).isTrue();
        // min temp is not used to calculate hottest day therefore all days are equal and the hottest day should be the first in the forecast
        assertThat(hottestDay.get().getDate()).isEqualTo(startDate);
    }

    @Test
    public void hottestDay_twoDaysHaveHigherMaxTempThanOthers() {
        final var startDate = LocalDate.of(2024, 1, 1);
        var forecast = defaultForecast(startDate, 5);

        // replace midday period on second and third days
        var newSecondDay = new Day(startDate.plusDays(1), defaultDay());
        var newThirdDay = new Day(startDate.plusDays(2), defaultDay());
        newSecondDay.getPeriods().set(4, new Period(LocalTime.of(12, 0), kelvin(DEFAULT_MIN_TEMP), kelvin(DEFAULT_MAX_TEMP + 2), DEFAULT_HUMIDITY));
        newThirdDay.getPeriods().set(4, new Period(LocalTime.of(12, 0), kelvin(DEFAULT_MIN_TEMP), kelvin(DEFAULT_MAX_TEMP + 2), DEFAULT_HUMIDITY));
        forecast.getDays().set(1, newSecondDay);
        forecast.getDays().set(2, newThirdDay);

        final var hottestDay = forecast.hottestDay();
        assertThat(hottestDay.isPresent()).isTrue();
        // two days have both the highest but equal temperature, therefore the first in the forecast is the hottest
        assertThat(hottestDay.get().getDate()).isEqualTo(startDate.plusDays(1));
    }

    @Test
    public void hottestDay_twoDaysHaveHigherMaxTempThanOthersOneHasLowerHumidity() {
        final var startDate = LocalDate.of(2024, 1, 1);
        var forecast = defaultForecast(startDate, 5);

        var newSecondDay = new Day(startDate.plusDays(1), defaultDay());
        var newThirdDay = new Day(startDate.plusDays(2), defaultDay());
        newSecondDay.getPeriods().set(4, new Period(LocalTime.of(12, 0), kelvin(DEFAULT_MIN_TEMP), kelvin(DEFAULT_MAX_TEMP + 2), DEFAULT_HUMIDITY));
        // the highest humidity for the day will be used unless we overwrite the other periods
        for (int i=0, hour=0; i < newThirdDay.getPeriods().size(); i++, hour=hour+3) {
            newThirdDay.getPeriods().set(i, new Period(LocalTime.of(hour, 0), kelvin(DEFAULT_MIN_TEMP), kelvin(DEFAULT_MAX_TEMP + 2), DEFAULT_HUMIDITY - 5));
        }
        forecast.getDays().set(1, newSecondDay);
        forecast.getDays().set(2, newThirdDay);

        final var hottestDay = forecast.hottestDay();
        assertThat(hottestDay.isPresent()).isTrue();
        assertThat(hottestDay.get().getDate()).isEqualTo(startDate.plusDays(2));
    }

    /*
     * A forecast with each day having the same weather
     */
    private Forecast defaultForecast(LocalDate startDate, int days) {
        var daysList = new ArrayList<Day>();
        for (int d=0; d<days; d++) {
            daysList.add(new Day(startDate.plusDays(d), defaultDay()));
        }
        return new Forecast(daysList);
    }

    /*
     * Day with each period having the same weather
     */
    private List<Period> defaultDay() {
        var periods = new ArrayList<Period>();
        for (int h=0; h<24; h=h+3) {
            periods.add(new Period(LocalTime.of(h, 0), kelvin(DEFAULT_MIN_TEMP), kelvin(DEFAULT_MAX_TEMP), DEFAULT_HUMIDITY));
        }
        return periods;
    }

}