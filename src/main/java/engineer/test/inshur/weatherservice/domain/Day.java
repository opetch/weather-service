package engineer.test.inshur.weatherservice.domain;

import java.time.LocalDate;
import java.util.List;

public class Day {

    private final LocalDate date;

    private final List<Period> periods;

    public Day(LocalDate date, List<Period> periods) {
        this.date = date;
        this.periods = periods;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Period> getPeriods() {
        return periods;
    }

}
