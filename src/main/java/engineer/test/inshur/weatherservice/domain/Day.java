package engineer.test.inshur.weatherservice.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class Day {

    private final LocalDate date;

    private final List<Period> periods;

    public Day(LocalDate date, List<Period> periods) {
        this.date = date;
        this.periods = periods;
    }

    public Optional<Temperature> maximumTemperature() {
        return periods.stream()
                .map(Period::getMaximumTemperature)
                .max(Comparator.comparingDouble(Temperature::getDegrees));
    }

    public OptionalDouble humidity() {
        return periods.stream()
                .mapToDouble(Period::getHumidity)
                .max();
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Period> getPeriods() {
        return periods;
    }

}
