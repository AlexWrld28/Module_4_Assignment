package org.example;

import java.time.LocalDate;
import java.time.Month;

/// Immutable weather reading represented as a Java record.
public record WeatherReading(LocalDate date, double temperature, int humidity, double precipitation) {

    /// Parses one CSV row into a weather reading.
    ///
    /// @param line CSV row in `date,temperature,humidity,precipitation` format
    /// @return parsed weather reading
    public static WeatherReading fromCsv(String line) {
        var parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid CSV row: " + line);
        }

        return new WeatherReading(
                LocalDate.parse(parts[0].strip()),
                Double.parseDouble(parts[1].strip()),
                Integer.parseInt(parts[2].strip()),
                Double.parseDouble(parts[3].strip()));
    }

    /// Checks whether the reading belongs to the requested month.
    ///
    /// @param month month filter
    /// @return `true` when the reading is in the requested month
    public boolean isIn(Month month) {
        return date.getMonth() == month;
    }

    /// Checks whether the reading recorded precipitation.
    ///
    /// @return `true` when precipitation is greater than zero
    public boolean isRainy() {
        return precipitation > 0.0;
    }

    /// Categorizes the temperature with an enhanced `switch` expression.
    ///
    /// @return `"Hot"`, `"Warm"`, or `"Cold"`
    public String category() {
        return switch ((int) temperature / 5) {
            case 7, 8, 9 -> "Hot";
            case 5, 6 -> "Warm";
            default -> "Cold";
        };
    }
}
