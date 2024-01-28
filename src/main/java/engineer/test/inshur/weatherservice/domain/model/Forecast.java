package engineer.test.inshur.weatherservice.domain.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Forecast {

    private final List<Day> days;

    public Forecast(List<Day> days) {
        this.days = days;
    }

    public List<Day> getDays() {
        return days;
    }

    public Optional<Day> hottestDay() {
        final Comparator<Day> tempComparator = Comparator.comparingDouble(day -> day.maximumTemperature().get().getDegrees());
        final Comparator<Day> reverseHumidityComparator = Comparator.comparingDouble(day -> -day.humidity().getAsDouble());

        return days.stream()
                .filter(d -> d.maximumTemperature().isPresent())
                .filter(d -> d.humidity().isPresent())
                .max(tempComparator.thenComparing(reverseHumidityComparator));
    }

    public static class Builder {
        private final Map<LocalDate, List<Period>> periods = new HashMap<>();
        public Builder add(LocalDate date, Period period) {
            if (periods.containsKey(date)) {
                periods.get(date).add(period);
            } else {
                periods.put(date, newEntry(period));
            }
            return this;
        }

        public Forecast build() {
            var days = periods.entrySet().stream()
                    .map(d -> new Day(d.getKey(), d.getValue()))
                    .collect(Collectors.toList());
            return new Forecast(days);
        }

        private List<Period> newEntry(Period period) {
            var list = new ArrayList<Period>();
            list.add(period);
            return list;
        }
    }
}
