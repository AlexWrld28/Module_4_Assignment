package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/// Stateless service operations for loading and analyzing weather data.
public interface WeatherService {

    /// Loads immutable weather readings from a CSV file.
    ///
    /// The header row is skipped automatically.
    ///
    /// @param csvPath CSV source path
    /// @return parsed weather readings
    /// @throws IOException if the file cannot be read
    static List<WeatherReading> load(Path csvPath) throws IOException {
        try (var lines = Files.lines(csvPath)) {
            return lines.skip(1)
                    .filter(line -> !line.isBlank())
                    .map(String::strip)
                    .map(WeatherReading::fromCsv)
                    .toList();
        }
    }

    /// Resolves command-line overrides into application settings.
    ///
    /// Defaults are `weatherdata.csv`, `Month.AUGUST`, and `30.0`.
    ///
    /// @param args raw command-line arguments
    /// @return normalized settings
    static AppSettings resolveSettings(String[] args) {
        return Arrays.stream(args)
                .map(SettingOverride::parse)
                .reduce(AppSettings.defaults(), WeatherService::applyOverride, WeatherService::keepLatestSettings);
    }

    /// Applies a typed override using pattern matching for `switch`.
    ///
    /// @param settings current settings
    /// @param override typed override
    /// @return updated settings
    static AppSettings applyOverride(AppSettings settings, SettingOverride override) {
        return switch (override) {
            case SettingOverride.MonthOverride(var month) -> settings.withMonth(month);
            case SettingOverride.ThresholdOverride(var threshold) -> settings.withThreshold(threshold);
            case SettingOverride.SourceOverride(var source) -> settings.withSource(source);
        };
    }

    /// Computes the report requested by the assignment.
    ///
    /// ## Example
    /// {@snippet :
    /// var summary = WeatherService.analyze(
    ///         WeatherService.load(Path.of("weatherdata.csv")),
    ///         AppSettings.defaults()
    /// );
    /// System.out.println(summary.summary());
    /// }
    ///
    /// @param readings all weather readings
    /// @param settings source, month, and threshold settings
    /// @return immutable summary record
    static WeatherSummary analyze(List<WeatherReading> readings, AppSettings settings) {
        var monthlyReadings = readings.stream()
                .filter(reading -> reading.isIn(settings.month()))
                .toList();

        if (monthlyReadings.isEmpty()) {
            throw new IllegalArgumentException("No weather data found for month " + settings.month().getValue());
        }

        var averageTemperature = monthlyReadings.stream()
                .mapToDouble(WeatherReading::temperature)
                .average()
                .orElseThrow();

        var thresholdDays = readings.stream()
                .filter(reading -> reading.temperature() > settings.threshold())
                .sorted(Comparator.comparing(WeatherReading::date))
                .toList();

        var rainyDayCount = readings.stream()
                .filter(WeatherReading::isRainy)
                .count();

        var categoryBreakdown = readings.stream()
                .collect(Collectors.groupingBy(
                        WeatherReading::category,
                        LinkedHashMap::new,
                        Collectors.counting()));

        return new WeatherSummary(
                settings.source(),
                settings.month(),
                settings.threshold(),
                averageTemperature,
                thresholdDays,
                rainyDayCount,
                categoryBreakdown);
    }

    /// Keeps the latest reduced settings during stream reduction.
    ///
    /// @param ignored earlier reduction result
    /// @param latest latest reduction result
    /// @return latest settings
    private static AppSettings keepLatestSettings(AppSettings ignored, AppSettings latest) {
        return latest;
    }
}
