package org.example;

import java.io.IOException;

/// # Weather Data Analyzer
///
/// Functional entry point for the assignment implementation.
///
/// ## Example
/// {@snippet :
/// Main.main(new String[] {
///         "month=8",
///         "threshold=30",
///         "source=weatherdata.csv"
/// });
/// }
public record Main() {

    /// Starts the analyzer, loads the CSV file, and prints the report.
    ///
    /// @param args optional overrides in `key=value` form
    /// @throws IOException if the CSV file cannot be read
    public static void main(String[] args) throws IOException {
        var settings = WeatherService.resolveSettings(args);
        var readings = WeatherService.load(settings.source());
        var summary = WeatherService.analyze(readings, settings);
        System.out.println(summary.summary());
    }
}
